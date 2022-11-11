package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    List<MenuResponseDTO> findAll();
    Optional<MenuResponseDTO> findByMenuNo(Long menuNo);
    Optional<MenuResponseDTO> findByMenuCode(String menuCode);
    Integer update(MenuUpdateDTO menuUpdateDTO);


    List<MenuResponseDTO> findAllMenus();
    List<MenuResponseDTO> findByUpperMenuNoIsNull();
    List<MenuResponseDTO> findMyUnderMenu();
    ArrayList<String> findMenusByLoginUsername(String username);



}
