package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    Optional<MenuResponseDTO> findBy(MenuRequestDTO menuRequestDTO);

    List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO);

    Optional<MenuResponseDTO> findUpperMenuBy(MenuRequestDTO menuRequestDTO);

    MenuInsertDTO insert(MenuInsertDTO menuUpdateDTO);

    MenuResponseDTO update(MenuUpdateDTO menuUpdateDTO);

    Integer updateIcon(MenuIconUpdateDTO menuIconUpdateDTO);

    List<MenuResponseDTO> findMenusByLoginUsername(String username);

}
