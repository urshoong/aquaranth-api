package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.constant.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.menu.mapper.MenuMapper;
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

@Log4j2
@RequiredArgsConstructor
@Service
public class DefaultAuthorizationMenuService implements MenuService {

    private final MenuMapper menuMapper;
    private final ObjectStorageService objectStorageService;

    @Override
    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO) {
        MenuResponseDTO menuResponseDTO = menuMapper.findBy(menuRequestDTO).orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));
        ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder().filename(menuResponseDTO.getUuid() + menuResponseDTO.getFilename()).build();

        try {
            menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return menuResponseDTO;
    }

    @Override
    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO) {
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findAllBy(menuRequestDTO);
        if (menuResponseDTOList.isEmpty()) {
            throw new MenuException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(menuResponseDTO -> {
            ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder().filename(menuResponseDTO.getUuid() + menuResponseDTO.getFilename()).build();
            try {
                menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return menuResponseDTOList;
    }

    @Override
    public MenuResponseDTO findUpperMenuBy(MenuRequestDTO menuRequestDTO) {
        MenuResponseDTO upperMenuResponseDTO = menuMapper.findUpperMenuBy(menuRequestDTO).orElseThrow(() -> new MenuException(ErrorCodes.MENU_NOT_FOUND));

        ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder().filename(upperMenuResponseDTO.getUuid() + upperMenuResponseDTO.getFilename()).build();

        try {
            upperMenuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return upperMenuResponseDTO;
    }

    @Override
    @Transactional()
    public MenuResponseDTO insert(MenuInsertDTO menuInsertDTO, MultipartFile multipartFile) throws Exception {

        Optional<MenuResponseDTO> findByUpperMenu = menuMapper.findBy(MenuRequestDTO.builder().menuNo(menuInsertDTO.getUpperMenuNo()).build());

        MenuResponseDTO upperMenu = findByUpperMenu.orElseGet(() -> MenuResponseDTO.builder().menuPath("/").depth(0L).build());

        menuInsertDTO.setMenuPath(upperMenu.getMenuPath() + "/" + menuInsertDTO.getMenuPath());
        menuInsertDTO.setDepth(upperMenu.getDepth() + 1L);

        if (!multipartFile.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String filename = multipartFile.getOriginalFilename();
            ObjectPostRequestDTO objectPostRequestDTO = ObjectPostRequestDTO.builder().filename(uuid + filename).multipartFile(multipartFile).build();
            menuInsertDTO.setUuid(uuid);
            menuInsertDTO.setFilename(filename);
            objectStorageService.postObject(objectPostRequestDTO);
        }

        menuMapper.insert(menuInsertDTO);
        return findBy(MenuRequestDTO.builder().menuCode(menuInsertDTO.getMenuCode()).build());
    }

    @Override
    @Transactional
    public MenuResponseDTO update(MenuUpdateDTO menuUpdateDTO) {
        menuMapper.update(menuUpdateDTO);
        return findBy(MenuRequestDTO.builder().menuCode(menuUpdateDTO.getMenuCode()).build());
    }

    @Override
    @Transactional
    public MenuResponseDTO updateIcon(MultipartFileDTO multipartFileDTO) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String filename = multipartFileDTO.getMultipartFile().getOriginalFilename();
        ObjectPostRequestDTO objectPostRequestDTO
                = ObjectPostRequestDTO.builder().filename(uuid + filename).multipartFile(multipartFileDTO.getMultipartFile()).build();
        MenuIconUpdateDTO menuIconUpdateDTO
                = MenuIconUpdateDTO.builder().menuCode(multipartFileDTO.getKey()).uuid(uuid).filename(filename).build();
        menuMapper.updateIcon(menuIconUpdateDTO);
        objectStorageService.postObject(objectPostRequestDTO);
        return findBy(MenuRequestDTO.builder().menuCode(multipartFileDTO.getKey()).build());
    }

    @Override
    public List<MenuResponseDTO> findMenusByLoginUsername(String username) {
        return menuMapper.findMenusByLoginUsername(username);
    }
}
