package com.dq.aqaranth.domain.menu.mapper;

import com.dq.aqaranth.domain.menu.dto.response.MenuResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MenuMapper {
    List<MenuResponse> findAllMenus();

//    Optional<List<MenuResponse>> findByEnableMenus();

    List<MenuResponse> findByGnBMenus();


}
