package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.request.tree.MenuTreeQueryDTO;
import com.dq.aquaranth.menu.dto.response.MenuDetailResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.dto.response.tree.MenuTreeResponseDTO;
import com.dq.aquaranth.menu.eventlistener.event.MenuEventDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.menu.mapper.AuthorizationMenuMapper;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.dq.aquaranth.menu.util.ObjectStorageUtil.getObjectGetRequestDTO;
import static com.dq.aquaranth.menu.util.ObjectStorageUtil.getObjectPostRequestDTO;

@Log4j2
@RequiredArgsConstructor
@Service
public class DefaultAuthorizationMenuService implements AuthorizationMenuService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final AuthorizationMenuMapper authorizationMenuMapper;
    private final ObjectStorageService objectStorageService;

    @Override
    public MenuResponseDTO findBy(final MenuQueryDTO menuQueryDTO) {
        MenuResponseDTO menuResponseDTO = authorizationMenuMapper.findBy(menuQueryDTO)
                .orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIcon(menuResponseDTO);
        return menuResponseDTO;
    }

    @Override
    public List<MenuResponseDTO> findAllBy(final MenuQueryDTO menuQueryDTO) {
        List<MenuResponseDTO> menuResponseDTOList = authorizationMenuMapper.findAllBy(menuQueryDTO);
        if (menuResponseDTOList.isEmpty()) {
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(this::setMenuIcon);
        return menuResponseDTOList;
    }

    @Override
    public MenuResponseDTO findUpperMenuBy(final MenuQueryDTO menuQueryDTO) {
        MenuResponseDTO upperMenuResponseDTO = authorizationMenuMapper.findUpperMenuBy(menuQueryDTO)
                .orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIcon(upperMenuResponseDTO);
        return upperMenuResponseDTO;
    }

    @Override
    @Transactional
    public MenuResponseDTO insert(final MenuInsertDTO menuInsertDTO, final MultipartFile multipartFile) throws Exception {
        Optional<MenuResponseDTO> findByUpperMenu = authorizationMenuMapper
                .findBy(MenuQueryDTO.builder().menuNo(menuInsertDTO.getUpperMenuNo()).build());
        MenuResponseDTO upperMenu = findByUpperMenu
                .orElseGet(() -> MenuResponseDTO.builder().menuPath("/").depth(0L).build());
        menuInsertDTO.setMenuPath(upperMenu.getMenuPath() + "/" + menuInsertDTO.getMenuPath());
        menuInsertDTO.setDepth(upperMenu.getDepth() + 1L);
        if (!multipartFile.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String filename = multipartFile.getOriginalFilename();
            ObjectPostRequestDTO objectPostRequestDTO = getObjectPostRequestDTO(multipartFile, uuid, filename);
            menuInsertDTO.setUuid(uuid);
            menuInsertDTO.setFilename(filename);
            objectStorageService.postObject(objectPostRequestDTO);
        }

        authorizationMenuMapper.insert(menuInsertDTO);
        MenuResponseDTO menuResponseDTO = findBy(MenuQueryDTO.builder().menuCode(menuInsertDTO.getMenuCode()).build());
        applicationEventPublisher.publishEvent(new MenuEventDTO(menuResponseDTO));
        return menuResponseDTO;
    }

    @Override
    @Transactional
    public MenuResponseDTO update(final MenuUpdateDTO menuUpdateDTO) {
        authorizationMenuMapper.update(menuUpdateDTO);
        MenuResponseDTO
                menuResponseDTO
                = findBy(MenuQueryDTO.builder().menuCode(menuUpdateDTO.getMenuCode()).build());
        applicationEventPublisher.publishEvent(new MenuEventDTO(menuResponseDTO));
        return menuResponseDTO;
    }

    @Override
    @Transactional
    public MenuResponseDTO updateIcon(final MultipartFileDTO multipartFileDTO) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String filename = multipartFileDTO.getMultipartFile().getOriginalFilename();

        ObjectPostRequestDTO objectPostRequestDTO
                = getObjectPostRequestDTO(multipartFileDTO.getMultipartFile(), uuid, filename);

        MenuIconUpdateDTO menuIconUpdateDTO
                = MenuIconUpdateDTO.builder().menuCode(multipartFileDTO.getKey()).uuid(uuid).filename(filename).build();

        authorizationMenuMapper.updateIcon(menuIconUpdateDTO);

        objectStorageService.postObject(objectPostRequestDTO);

        MenuResponseDTO menuResponseDTO
                = findBy(MenuQueryDTO.builder().menuCode(multipartFileDTO.getKey()).build());

        applicationEventPublisher.publishEvent(new MenuEventDTO(menuResponseDTO));
        return menuResponseDTO;
    }

    @Override
    public Integer delete(MenuQueryDTO menuQueryDTO) {
        return authorizationMenuMapper.delete(menuQueryDTO);
    }

    @Override
    public List<MenuResponseDTO> findMenusByLoginUsername(String username) {
        return authorizationMenuMapper.findMenusByLoginUsername(username);
    }

    @Override
    public List<MenuTreeResponseDTO> findUnderMenuByTwo(MenuTreeQueryDTO menuTreeQueryDTO){
        List<MenuTreeResponseDTO> menuTreeResponseDTOS = authorizationMenuMapper.findUnderMenuByTwo(menuTreeQueryDTO);

        menuTreeResponseDTOS.forEach(menuTreeResponseDTO -> {
            try {menuTreeResponseDTO.setIconUrl(objectStorageService.getObject(ObjectGetRequestDTO.builder()
                                                    .filename(menuTreeResponseDTO.getUuid() + menuTreeResponseDTO.getFilename())
                                                    .build())
                                                    .getUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return menuTreeResponseDTOS;
    }

    @Override
    public MenuDetailResponseDTO findMenuDetailsBy(MenuQueryDTO menuQueryDTO){
        MenuDetailResponseDTO menuDetailResponseDTO = authorizationMenuMapper.findMenuDetailsBy(menuQueryDTO).orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        try {menuDetailResponseDTO.setIconUrl(objectStorageService.getObject(ObjectGetRequestDTO.builder()
                                            .filename(menuDetailResponseDTO.getUuid() + menuDetailResponseDTO.getFilename())
                                            .build())
                                            .getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return menuDetailResponseDTO;
    }

    private void setMenuIcon(MenuResponseDTO menuResponseDTO) {
        ObjectGetRequestDTO objectRequestDTO = getObjectGetRequestDTO(menuResponseDTO);
        try {
            menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
