package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.response.MenuResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuResponse> findAllMenus();

//    Optional<List<MenuResponse>> findByEnableMenus();

    List<MenuResponse> findByGnBMenus();


}
