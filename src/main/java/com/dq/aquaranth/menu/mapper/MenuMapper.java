package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.response.MenuImportResponseDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.UserDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    Optional<MenuResponseDTO> findBy(@Param("menu") MenuQueryDTO menuQueryDTO, @Param("user") UserDTO userDTO);

    List<MenuResponseDTO> findAllBy(@Param("menu") MenuQueryDTO menuQueryDTO, @Param("user") UserDTO userDTO);

    List<MenuImportResponseDTO> initializeAppImport();
    List<MenuResponseDTO> findInRedis(@Param("menu") MenuQueryDTO menuQueryDTO, @Param("rolegroup") List<Long> roleGroupNo);

}
