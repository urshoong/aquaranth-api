package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    List<MenuResponseDTO> findAll();

    Optional<MenuResponseDTO> findByMenuNo(Long menuNo);

    Optional<MenuResponseDTO> findByMenuCode(String menuCode);

    Integer update(MenuUpdateDTO menuUpdateDTO);

    List<MenuResponseDTO> findAllMenuInformation();

}
