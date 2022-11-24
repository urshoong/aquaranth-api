package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {

    MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO);
    MenuResponseDTO findUpperMenuBy(MenuRequestDTO menuRequestDTO);
    List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO);

    MenuResponseDTO insert(MenuInsertDTO menuInsertDTO, MultipartFile multipartFile) throws Exception;

    MenuResponseDTO update(MenuUpdateDTO menuUpdateDTO);

    MenuResponseDTO updateIcon(MultipartFileDTO fileDto) throws Exception;

    List<MenuResponseDTO> findMenusByLoginUsername(String username);

}
