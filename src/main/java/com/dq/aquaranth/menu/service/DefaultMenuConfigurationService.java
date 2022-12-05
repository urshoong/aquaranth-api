package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.exception.ErrorCodes;
import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuDetailResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuTreeResponseDTO;
import com.dq.aquaranth.menu.exception.CommonException;
import com.dq.aquaranth.menu.mapper.MenuConfigurationMapper;
import com.dq.aquaranth.menu.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.menu.objectstorage.dto.request.ObjectGetRequestDTO;
import com.dq.aquaranth.menu.objectstorage.dto.request.ObjectPostRequestDTO;
import com.dq.aquaranth.menu.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
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
public class DefaultMenuConfigurationService implements MenuConfigurationService {

    private final MenuConfigurationMapper menuConfigurationMapper;
    private final ObjectStorageService objectStorageService;

    /**
     * 메뉴를 단건 조회합니다.
     * UUID와 파일 이름을 이용하여 ObjectStorage에서 아이콘을 조회한 후
     * 아이콘 URL과 함께 반환합니다.
     * @param menuQueryDTO
     * @return
     */
    @Override
    public MenuResponseDTO findBy(final MenuQueryDTO menuQueryDTO) {
        MenuResponseDTO menuResponseDTO = menuConfigurationMapper.findBy(menuQueryDTO).orElseThrow(() -> new CommonException(ErrorCodes.MENU_NOT_FOUND));
        setMenuIconUrl(menuResponseDTO);

        return menuResponseDTO;
    }

    /**
     * 메뉴를 복수건 조회합니다.
     * UUID와 파일 이름을 이용하여 ObjectStorage에서 아이콘을 조회한 후
     * 아이콘 URL과 함께 반환합니다.
     * @param menuQueryDTO
     * @return
     */
    @Override
    public List<MenuResponseDTO> findAllBy(final MenuQueryDTO menuQueryDTO) {
        List<MenuResponseDTO> menuResponseDTOList = menuConfigurationMapper.findAllBy(menuQueryDTO);
        if (menuResponseDTOList.isEmpty()) {
            throw new CommonException(ErrorCodes.MENU_NOT_FOUND);
        }
        menuResponseDTOList.forEach(this::setMenuIconUrl);

        return menuResponseDTOList;
    }

    /**
     * 메뉴를 상세조회 합니다.
     * @param menuQueryDTO
     * @return
     */
    @Override
    public MenuDetailResponseDTO findMenuDetailsBy(final MenuQueryDTO menuQueryDTO) {
        MenuDetailResponseDTO menuDetailResponseDTO = menuConfigurationMapper.findMenuDetailsBy(menuQueryDTO).orElseThrow(() -> new CommonException(ErrorCodes.MENU_NOT_FOUND));
        try {
            menuDetailResponseDTO.setIconUrl(objectStorageService.getObject(ObjectGetRequestDTO.builder().filename(menuDetailResponseDTO.getUuid() + menuDetailResponseDTO.getFilename()).build()).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return menuDetailResponseDTO;
    }

    /**
     * 메뉴를 추가합니다.
     * @return
     */
    @Override
    @Transactional
    public MenuInsertDTO insert(MenuInsertDTO menuInsertDTO, final MultipartFile multipartFile) throws Exception {
        Optional<MenuResponseDTO> findByUpperMenu = menuConfigurationMapper.findBy(MenuQueryDTO.builder().menuNo(menuInsertDTO.getUpperMenuNo()).build());
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

        return menuConfigurationMapper.insert(menuInsertDTO);
    }

    /**
     * 메뉴를 정보를 업데이트 합니다.
     * @return
     */
    @Override
    @Transactional
    public Integer update(final MenuUpdateDTO menuUpdateDTO) {

        return menuConfigurationMapper.update(menuUpdateDTO);
    }

    /**
     * 메뉴 아이콘을 업데이트 합니다.
     * @param multipartFileDTO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Integer updateIcon(final MultipartFileDTO multipartFileDTO) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String filename = multipartFileDTO.getMultipartFile().getOriginalFilename();

        ObjectPostRequestDTO objectPostRequestDTO = getObjectPostRequestDTO(multipartFileDTO.getMultipartFile(), uuid, filename);

        MenuIconUpdateDTO menuIconUpdateDTO = MenuIconUpdateDTO.builder().menuCode(multipartFileDTO.getKey()).uuid(uuid).filename(filename).build();

        Integer result = menuConfigurationMapper.updateIcon(menuIconUpdateDTO);

        objectStorageService.postObject(objectPostRequestDTO);

        return result;
    }

    /**
     * 메뉴를 삭제합니다.
     * @param menuQueryDTO
     * @return
     */
    @Override
    public Integer delete(MenuQueryDTO menuQueryDTO) {
        Integer result = null;
        try {
            result = menuConfigurationMapper.delete(menuQueryDTO);
        } catch (DataIntegrityViolationException e) {
            throw new CommonException(ErrorCodes.FOREIGN_KEY_ERROR);
        }

        return result;
    }

    @Override
    public List<MenuTreeResponseDTO> findUnderMenusBy(MenuQueryDTO menuQueryDTO) {
        List<MenuTreeResponseDTO> menuTreeResponseDTOS = menuConfigurationMapper.findUnderMenusBy(menuQueryDTO);

        menuTreeResponseDTOS.forEach(menuTreeResponseDTO -> {
            try {
                menuTreeResponseDTO.setIconUrl(objectStorageService.getObject(ObjectGetRequestDTO.builder().filename(menuTreeResponseDTO.getUuid() + menuTreeResponseDTO.getFilename()).build()).getUrl());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return menuTreeResponseDTOS;
    }

    /**
     * UUID와 파일 이름을 이용하여 메뉴 아이콘을 등록합니다.
     * @param menuResponseDTO
     */
    private void setMenuIconUrl(MenuResponseDTO menuResponseDTO) {
        ObjectGetRequestDTO objectRequestDTO = getObjectGetRequestDTO(menuResponseDTO);
        try {
            menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
