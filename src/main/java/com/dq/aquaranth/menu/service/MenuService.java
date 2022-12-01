package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.response.MenuImportResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;

import java.util.List;

public interface MenuService {

    MenuResponseDTO findBy(MenuQueryDTO menuQueryDTO, String username);
    List<MenuResponseDTO> findAllBy(MenuQueryDTO menuQueryDTO, String username);
    List<MenuResponseDTO> findAllInCache();

    List<MenuImportResponseDTO> initializeAppImport();
}




