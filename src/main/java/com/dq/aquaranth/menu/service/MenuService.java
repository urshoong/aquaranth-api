package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {

    List<MenuResponse> findAllMenus();

    List<MenuResponse> findByGnbMenus();


}
