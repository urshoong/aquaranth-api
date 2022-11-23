package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
        return menuMapper.findAll();
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


        if (!multipartFile.isEmpty()){
            String uuid = UUID.randomUUID().toString();
            String filename = multipartFile.getOriginalFilename();
            menuInsertDTO.setUuid(uuid);
            menuInsertDTO.setFilename(filename);
            objectStorageService.postObject(multipartFile, uuid + filename);
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
    public Optional<MenuResponseDTO> updateByMenuIcon(MenuUpdateDTO menuUpdateDTO) {
        return Optional.empty();
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
