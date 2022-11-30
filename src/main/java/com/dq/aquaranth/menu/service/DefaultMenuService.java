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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.dq.aquaranth.menu.constant.Menu.REDIS_KEYS;
import static com.dq.aquaranth.menu.util.ObjectStorageUtil.getObjectGetRequestDTO;

@Log4j2
@Service
@RequiredArgsConstructor
public class DefaultMenuService implements MenuService {

    // TODO : 프록시 패턴으로 구현해보기

    private final MenuMapper menuMapper;
    private final ObjectMapper objectMapper;
    private final ObjectStorageService objectStorageService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisService redisService;

    @Override
    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);
        MenuResponseDTO menuResponseDTO = menuMapper
                .findBy(menuRequestDTO, UserDTO.builder()
                        .companyNo(loginUserInfo.getCompany().getCompanyNo())
                        .deptNo(loginUserInfo.getDept().getDeptNo())
                        .username(loginUserInfo.getEmp().getUsername()).build())
                .orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIconUrl(menuResponseDTO);
        return menuResponseDTO;
    }

    @Override
    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findAllBy(menuRequestDTO, UserDTO.builder()
                .companyNo(loginUserInfo.getCompany().getCompanyNo())
                .deptNo(loginUserInfo.getDept().getDeptNo())
                .username(loginUserInfo.getEmp().getUsername()).build());

        if (menuResponseDTOList.isEmpty()) {
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(this::setMenuIconUrl);
        return menuResponseDTOList;
    }

    /**
     * Redis에 캐싱된 메뉴를 조회합니다.
     * @param menuRequestDTO
     * @param username
     * @return
     */
    @Override
    public List<MenuResponseDTO> findAllInCache(MenuRequestDTO menuRequestDTO, String username) {
        LoginUserInfo loginUserInfo = getLoginUserInfo(username);
        List<MenuResponseDTO> menuResponseDTOList = new ArrayList<>();

        redisService.keys(REDIS_KEYS.getCode() + "*").forEach(menu -> {
            try {
                menuResponseDTOList.add(objectMapper.readValue(redisTemplate.opsForValue().get(menu).toString(), MenuResponseDTO.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        if (menuResponseDTOList.isEmpty()) {
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        /**
         * FIXME : Stream -> For Loop
         */
        return menuResponseDTOList.stream()
                .filter(menuResponseDTO ->
                        menuResponseDTO.getMenuCode().equals(menuRequestDTO.getMenuCode()))

                .collect(Collectors.toList());


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
            loginUserInfo = objectMapper.readValue(redisTemplate.opsForValue().get(username).toString(), LoginUserInfo.class);
        } catch (NullPointerException nullPointerException) {
            throw new MenuException(ErrorCodes.REDIS_USER_NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return loginUserInfo;
    }
}
