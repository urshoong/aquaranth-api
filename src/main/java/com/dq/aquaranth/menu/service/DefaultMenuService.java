package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.login.dto.LoginUserInfo;
import com.dq.aquaranth.login.service.RedisService;
import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.UserDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.dq.aquaranth.menu.service.util.ObjectStorageUtil.getObjectGetRequestDTO;

@Log4j2
@Service
@RequiredArgsConstructor
public class DefaultMenuService implements MenuService {

    // TODO : 프록시 패턴으로 구현해보기

    private final MenuMapper menuMapper;
    private final ObjectMapper objectMapper;
    private final ObjectStorageService objectStorageService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);

        MenuResponseDTO menuResponseDTO = menuMapper.findBy(menuRequestDTO, UserDTO.builder()
                .companyNo(loginUserInfo.getCompany().getCompanyNo())
                .deptNo(loginUserInfo.getDept().getDeptNo())
                .username(loginUserInfo.getEmp().getUsername())
                .build())
                .orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIcon(menuResponseDTO);
        return menuResponseDTO;
    }

    @Override
    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);
        List<MenuResponseDTO> menuResponseDTOList =
                menuMapper.findAllBy(menuRequestDTO,UserDTO.builder()
                        .companyNo(loginUserInfo.getCompany().getCompanyNo())
                        .deptNo(loginUserInfo.getDept().getDeptNo())
                        .username(loginUserInfo.getEmp().getUsername())
                        .build());
        if (menuResponseDTOList.isEmpty()){
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(this::setMenuIcon);
        return menuResponseDTOList;
    }

    private void setMenuIcon(MenuResponseDTO menuResponseDTO) {
        ObjectGetRequestDTO objectRequestDTO = getObjectGetRequestDTO(menuResponseDTO);
        try {
            menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private LoginUserInfo getLoginUserInfo(String username) {
        LoginUserInfo loginUserInfo;
        try {
            loginUserInfo = objectMapper.readValue(redisTemplate.opsForValue().get(username).toString(), LoginUserInfo.class);
        } catch (NullPointerException nullPointerException) {
            throw new MenuException(ErrorCodes.REDIS_USER_NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return loginUserInfo;
    }
}
