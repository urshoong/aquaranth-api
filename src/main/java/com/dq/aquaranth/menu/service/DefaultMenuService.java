package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.login.dto.LoginUserInfo;
import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.UserDTO;
import com.dq.aquaranth.menu.dto.response.MenuImportResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dq.aquaranth.menu.constant.RedisKeys.MENU_KEYS;
import static com.dq.aquaranth.menu.constant.RedisKeys.ROLES_KEYS;
import static com.dq.aquaranth.menu.util.ObjectStorageUtil.getObjectGetRequestDTO;

@Log4j2
@Service
@RequiredArgsConstructor
public class DefaultMenuService implements MenuService {

    // TODO : 프록시 패턴으로 구현해보기

    private final MenuMapper menuMapper;
    private final ObjectMapper objectMapper;
    private final ObjectStorageService objectStorageService;
    private final RedisService redisService;

    @Override
    public MenuResponseDTO findBy(MenuQueryDTO menuQueryDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);

        MenuResponseDTO menuResponseDTO = menuMapper.findBy(menuQueryDTO, UserDTO.builder()
                .companyNo(loginUserInfo.getCompany().getCompanyNo())
                .deptNo(loginUserInfo.getDept().getDeptNo())
                .username(loginUserInfo.getEmp().getUsername())
                .build())
                .orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));

        setMenuIconUrl(menuResponseDTO);
        return menuResponseDTO;
    }

    @Override
    public List<MenuResponseDTO> findAllBy(MenuQueryDTO menuQueryDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findAllBy(menuQueryDTO, UserDTO.builder()
                .companyNo(loginUserInfo.getCompany().getCompanyNo())
                .deptNo(loginUserInfo.getDept().getDeptNo())
                .username(loginUserInfo.getEmp().getUsername())
                .build());

        if (menuResponseDTOList.isEmpty()) {
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(this::setMenuIconUrl);
        return menuResponseDTOList;
    }

    /**
     * Redis에 캐싱된 메뉴를 조회합니다.
     *
     */
    @Override
    public List<MenuResponseDTO> findAllInCache() {

        Collection<String> redisKeyList = redisService.keys(MENU_KEYS.getKeys() + "*");
        List<MenuResponseDTO> menuResponseDTOList = new ArrayList<>();

        for (String redisMenu : redisKeyList) {
            try {
                menuResponseDTOList.add(objectMapper.readValue(redisService.getCacheObject(redisMenu).toString(), MenuResponseDTO.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return menuResponseDTOList;
    }

    @Override
    public List<MenuImportResponseDTO> initializeAppImport(){
        return menuMapper.initializeAppImport();
    }

    @Override
    public List<MenuResponseDTO> findInRedis(MenuQueryDTO menuQueryDTO, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        LoginUserInfo loginUserInfo;
        try {
            loginUserInfo = objectMapper.readValue(redisService.getCacheObject(username).toString(),LoginUserInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Long> roleGroupNo = loginUserInfo
                .getRoleGroups()
                .stream().map(RoleGroup::getRoleGroupNo)
                .collect(Collectors.toList());
        return menuMapper.findInRedis(menuQueryDTO, roleGroupNo);
    }


    private void setMenuIconUrl(MenuResponseDTO menuResponseDTO) {
        ObjectGetRequestDTO objectRequestDTO = getObjectGetRequestDTO(menuResponseDTO, (int) TimeUnit.DAYS.toHours(7));
        try {
            menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private LoginUserInfo getLoginUserInfo(String username) {
        LoginUserInfo loginUserInfo;
        try {
            loginUserInfo = objectMapper.readValue(redisService.getCacheObject(username).toString(), LoginUserInfo.class);
        } catch (NullPointerException nullPointerException) {
            throw new MenuException(ErrorCodes.REDIS_USER_NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return loginUserInfo;
    }
}
