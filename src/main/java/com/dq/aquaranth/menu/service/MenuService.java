package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import java.util.List;

public interface MenuService {

    List<MenuResponseDTO> findAllMenus();

    List<MenuResponseDTO> findByGnbMenus();

    Integer update(MenuUpdateDTO menuUpdateDTO);

    List<MenuResponseDTO> findByMenu();

}
