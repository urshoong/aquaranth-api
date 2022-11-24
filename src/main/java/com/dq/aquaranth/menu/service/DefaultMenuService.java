package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
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

@Service
@Log4j2
@RequiredArgsConstructor
public class DefaultMenuService implements MenuService {

    private final MenuMapper menuMapper;
    private final ObjectStorageService objectStorageService;

    /**
     * 권한 여부와 상관없이 모든 메뉴를 조회합니다.
     *
     * @return
     */
    @Override
    public List<MenuResponseDTO> findAll() {
        List<MenuResponseDTO> menuResponseDTOList = menuMapper.findAll();
        menuResponseDTOList.
                forEach(menuResponseDTO -> {
                    ObjectGetRequestDTO objectRequestDTO = ObjectGetRequestDTO.builder()
                            .filename(menuResponseDTO.getUuid() + menuResponseDTO.getFilename())
                            .build();
                    try {
                        menuResponseDTO.setIconUrl(objectStorageService.getObject(objectRequestDTO).getUrl());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return menuResponseDTOList;
    }

    /**
     * 메뉴코드를 이용하여 단건 메뉴를 조회합니다.
     *
     * @param menuCode
     * @return
     */
    @Override
    public Optional<MenuResponseDTO> findByMenuCode(String menuCode) {
        return menuMapper.findByMenuCode(menuCode);
    }

    /**
     * 메뉴번호를 이용하여 단건 메뉴를 조회합니다.
     *
     * @param menuNo
     * @return
     */
    @Override
    public Optional<MenuResponseDTO> findByMenuNo(Long menuNo) {
        return menuMapper.findByMenuNo(menuNo);
    }

    /**
     * 메뉴를 추가합니다. 반환되는 정보는 추가된 메뉴의 정보입니다.
     *
     * @param menuInsertDTO
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Optional<MenuResponseDTO> insert(MenuInsertDTO menuInsertDTO, MultipartFile multipartFile) throws Exception {

        Optional<MenuResponseDTO> findByUpperMenu =
                menuMapper.findByMenuNo(menuInsertDTO.getUpperMenuNo());

        MenuResponseDTO upperMenu = findByUpperMenu.orElseGet(() -> MenuResponseDTO.builder()
                .menuPath("/")
                .depth(0L)
                .build());

        menuInsertDTO.setMenuPath(upperMenu.getMenuPath() + "/" + menuInsertDTO.getMenuPath());
        menuInsertDTO.setDepth(upperMenu.getDepth() + 1L);


        if (!multipartFile.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String filename = multipartFile.getOriginalFilename();
            ObjectPostRequestDTO objectPostRequestDTO = ObjectPostRequestDTO.builder()
                            .filename(uuid + filename)
                                    .multipartFile(multipartFile)
                    .build();
            menuInsertDTO.setUuid(uuid);
            menuInsertDTO.setFilename(filename);
            objectStorageService.postObject(objectPostRequestDTO);
        }

        menuMapper.insert(menuInsertDTO);

        return menuMapper.findByMenuCode(menuInsertDTO.getMenuCode());
    }

    /**
     * 메뉴 상태를 업데이트 합니다. 반환되는 정보는 업데이트된 메뉴의 정보입니다.
     *
     * @param menuUpdateDTO
     * @return
     */
    @Override
    @Transactional
    public Optional<MenuResponseDTO> update(MenuUpdateDTO menuUpdateDTO) {
        menuMapper.update(menuUpdateDTO);
        return menuMapper.findByMenuNo(menuUpdateDTO.getMenuNo());
    }

    @Override
    @Transactional
    public Optional<MenuResponseDTO> updateIcon(MultipartFileDTO multipartFileDTO) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String filename = multipartFileDTO.getMultipartFile().getOriginalFilename();

        ObjectPostRequestDTO objectPostRequestDTO = ObjectPostRequestDTO
                .builder()
                .filename(uuid + filename)
                .multipartFile(multipartFileDTO.getMultipartFile())
                .build();

        MenuIconUpdateDTO menuIconUpdateDTO = MenuIconUpdateDTO.builder()
                .menuCode(multipartFileDTO.getKey())
                .uuid(uuid)
                .filename(filename)
                .build();

        menuMapper.updateIcon(menuIconUpdateDTO);
        objectStorageService.postObject(objectPostRequestDTO);
        return menuMapper.findByMenuCode(multipartFileDTO.getKey());
    }

    @Override
    public List<MenuResponseDTO> findByUpperMenuNoIsNull() {
        return menuMapper.findByUpperMenuNoIsNull();
    }

    /**
     * 상위 메뉴코드를 이용하여 하위메뉴들을 조회합니다.
     *
     * @param menuCode
     * @return
     */
    @Override
    public List<MenuResponseDTO> findByMenuCodeUpperRecursive(String menuCode) {
        return menuMapper.findByMenuCodeUpperRecursive(menuCode);
    }

    /**
     * 하위 메뉴코드를 이용하여 상위메뉴들를 조회합니다.
     *
     * @return
     */
    @Override
    public List<MenuResponseDTO> findByMenuCodeUnderRecursive(String menuCode) {
        return menuMapper.findByMenuCodeUnderRecursive(menuCode);
    }

    /**
     * 유저의 아이디를 이용하여 유저가 접근할 수 있는 메뉴를 조회합니다.
     *
     * @param username
     * @return
     */
    @Override
    public List<MenuResponseDTO> findMenusByLoginUsername(String username) {
        return menuMapper.findMenusByLoginUsername(username);
    }
}
