package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    List<MenuResponseDTO> findAll();

    Optional<MenuResponseDTO> findByMenuNo(Long menuNo);

    Optional<MenuResponseDTO> findByMenuCode(String menuCode);

    Optional<MenuResponseDTO> update(MenuUpdateDTO menuUpdateDTO);

    List<MenuResponseDTO> findAllMenuInformation();

    List<MenuResponseDTO> findByUpperMenuNoIsNull();

    List<MenuResponseDTO> findByMenuCodeUpperRecursive(String menuCode);

    List<MenuResponseDTO> findByMenuCodeUnderRecursive(String menuCode);

    List<MenuResponseDTO> findMenusByLoginUsername(String username);

}
