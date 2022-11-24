package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    List<MenuResponseDTO> findAll();

    Optional<MenuResponseDTO> findByMenuNo(Long menuNo);

    Optional<MenuResponseDTO> findByMenuCode(String menuCode);

    Optional<MenuResponseDTO> insert(MenuInsertDTO menuInsertDTO, MultipartFile multipartFile) throws Exception;

    Optional<MenuResponseDTO> update(MenuUpdateDTO menuUpdateDTO);
    Optional<MenuResponseDTO> updateIcon(MultipartFileDTO fileDto) throws Exception;

    List<MenuResponseDTO> findByUpperMenuNoIsNull();

    List<MenuResponseDTO> findByMenuCodeUpperRecursive(String menuCode);

    List<MenuResponseDTO> findByMenuCodeUnderRecursive(String menuCode);

    List<MenuResponseDTO> findMenusByLoginUsername(String username);

}
