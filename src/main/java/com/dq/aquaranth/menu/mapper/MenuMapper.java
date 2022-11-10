package com.dq.aquaranth.menu.mapper;

import com.dq.aquaranth.menu.dto.response.AllMenuResponse;
import com.dq.aquaranth.menu.dto.response.MenuResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MenuMapper {
    List<AllMenuResponse> findAllMenus();

    List<MenuResponse> findByGnBMenus();

    ArrayList<String> findMenusByLoginUsername(String username);
}
