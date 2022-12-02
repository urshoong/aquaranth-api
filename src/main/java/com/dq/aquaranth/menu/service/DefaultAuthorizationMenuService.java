package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.menu.mapper.AuthorizationMenuMapper;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.dq.aquaranth.menu.service.util.ObjectStorageUtil.getObjectGetRequestDTO;
import static com.dq.aquaranth.menu.service.util.ObjectStorageUtil.getObjectPostRequestDTO;

@Log4j2
@RequiredArgsConstructor
@Service
public class DefaultAuthorizationMenuService implements AuthorizationMenuService {

    private final AuthorizationMenuMapper authorizationMenuMapper;
    private final ObjectStorageService objectStorageService;

    @Override
    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO) {
        MenuResponseDTO menuResponseDTO = authorizationMenuMapper.findBy(menuRequestDTO).orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIcon(menuResponseDTO);
        return menuResponseDTO;
    }

    @Override
    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO) {
        List<MenuResponseDTO> menuResponseDTOList = authorizationMenuMapper.findAllBy(menuRequestDTO);
        if (menuResponseDTOList.isEmpty()) {
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(this::setMenuIcon);
        return menuResponseDTOList;
    }

    @Override
    public MenuResponseDTO findUpperMenuBy(MenuRequestDTO menuRequestDTO) {
        MenuResponseDTO upperMenuResponseDTO = authorizationMenuMapper.findUpperMenuBy(menuRequestDTO).orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIcon(upperMenuResponseDTO);
        return upperMenuResponseDTO;
    }

    @Override
    @Transactional
    public MenuResponseDTO insert(MenuInsertDTO menuInsertDTO, MultipartFile multipartFile) throws Exception {
        Optional<MenuResponseDTO> findByUpperMenu = authorizationMenuMapper.findBy(MenuRequestDTO.builder().menuNo(menuInsertDTO.getUpperMenuNo()).build());
        MenuResponseDTO upperMenu = findByUpperMenu.orElseGet(() -> MenuResponseDTO.builder().menuPath("/").depth(0L).build());
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
        return findBy(MenuRequestDTO.builder().menuCode(menuInsertDTO.getMenuCode()).build());
    }

    @Override
    @Transactional
    public MenuResponseDTO update(MenuUpdateDTO menuUpdateDTO) {
        authorizationMenuMapper.update(menuUpdateDTO);
        return findBy(MenuRequestDTO.builder().menuCode(menuUpdateDTO.getMenuCode()).build());
    }

    @Override
    @Transactional
    public MenuResponseDTO updateIcon(MultipartFileDTO multipartFileDTO) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String filename = multipartFileDTO.getMultipartFile().getOriginalFilename();
        ObjectPostRequestDTO objectPostRequestDTO = getObjectPostRequestDTO(multipartFileDTO.getMultipartFile(), uuid, filename);
        MenuIconUpdateDTO menuIconUpdateDTO = MenuIconUpdateDTO.builder().menuCode(multipartFileDTO.getKey()).uuid(uuid).filename(filename).build();
        authorizationMenuMapper.updateIcon(menuIconUpdateDTO);
        objectStorageService.postObject(objectPostRequestDTO);
        return findBy(MenuRequestDTO.builder().menuCode(multipartFileDTO.getKey()).build());
    }

    private void setMenuIcon(MenuResponseDTO menuResponseDTO) {
        ObjectGetRequestDTO objectRequestDTO = getObjectGetRequestDTO(menuResponseDTO);
        try {
            menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MenuResponseDTO> findMenusByLoginUsername(String username) {
        return authorizationMenuMapper.findMenusByLoginUsername(username);
    }
}
