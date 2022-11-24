package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.request.MenuIconUpdateDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    List<MenuResponseDTO> findAll();
    List<MenuResponseDTO> findBy(MenuRequestDTO menuRequestDTO);
    MenuInsertDTO insert(MenuInsertDTO menuUpdateDTO);
    MenuResponseDTO update(MenuUpdateDTO menuUpdateDTO);
    Integer updateIcon(MenuIconUpdateDTO menuIconUpdateDTO);

    /**
     * Username을 이용하여
     * 해당하는 사용자가 접근할 수 있는
     * 모든 메뉴 목록을 가져옵니다.
     * @return
     */
    List<MenuResponseDTO> findMenusByLoginUsername(String username);

}
