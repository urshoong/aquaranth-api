package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.request.tree.MenuTreeQueryDTO;
import com.dq.aquaranth.menu.dto.response.MenuDetailResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.dto.response.tree.MenuTreeResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AuthorizationMenuMapper {
    Optional<MenuResponseDTO> findBy(MenuQueryDTO menuQueryDTO);

    List<MenuResponseDTO> findAllBy(MenuQueryDTO menuQueryDTO);

    Optional<MenuResponseDTO> findUpperMenuBy(MenuQueryDTO menuQueryDTO);

    MenuInsertDTO insert(MenuInsertDTO menuUpdateDTO);

    Integer update(MenuUpdateDTO menuUpdateDTO);

    Integer updateIcon(MenuIconUpdateDTO menuIconUpdateDTO);

    List<MenuResponseDTO> findMenusByLoginUsername(String username);

    List<MenuResponseDTO> findUnderMenuBy(String menuCode);

    List<MenuTreeResponseDTO> findUnderMenuByTwo(MenuTreeQueryDTO menuTreeQueryDTO);

    Optional<MenuDetailResponseDTO> findMenuDetailsBy(MenuQueryDTO menuQueryDTO);

    Integer delete(MenuQueryDTO menuQueryDTO);
}
