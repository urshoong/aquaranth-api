package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import java.util.List;

public interface MenuService {

    MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO, String username);
    List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO, String username);
}




