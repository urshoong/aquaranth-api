package com.dq.aqaranth.domain.menu.service;

import com.dq.aqaranth.domain.menu.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {

    List<MenuResponse> findAllMenus();

    List<MenuResponse> findByGnbMenus();


}
