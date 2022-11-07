package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.response.AllMenuResponse;
import com.dq.aquaranth.menu.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {

    List<AllMenuResponse> findAllMenus();

    List<MenuResponse> findByGnbMenus();


}
