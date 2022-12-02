package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.request.tree.MenuTreeQueryDTO;
import com.dq.aquaranth.menu.dto.response.MenuDetailResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.dto.response.tree.MenuTreeResponseDTO;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuthorizationMenuService {

    MenuResponseDTO findBy(MenuQueryDTO menuQueryDTO);
    MenuResponseDTO findUpperMenuBy(MenuQueryDTO menuQueryDTO);
    List<MenuResponseDTO> findAllBy(MenuQueryDTO menuQueryDTO);

    MenuResponseDTO insert(MenuInsertDTO menuInsertDTO, MultipartFile multipartFile) throws Exception;

    MenuResponseDTO update(MenuUpdateDTO menuUpdateDTO);

    MenuResponseDTO updateIcon(MultipartFileDTO multipartFileDTO) throws Exception;
    Integer delete(MenuQueryDTO menuQueryDTO);

    List<MenuResponseDTO> findMenusByLoginUsername(String username);

    List<MenuTreeResponseDTO> findUnderMenuByTwo(MenuTreeQueryDTO menuTreeQueryDTO);

    MenuDetailResponseDTO findMenuDetailsBy(MenuQueryDTO menuQueryDTO);
}
