package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.response.MenuResponse;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMenuService implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<MenuResponse> findAllMenus() {
        return menuMapper.findAllMenus();
    }

    @Override
    public List<MenuResponse> findByGnbMenus() {
        return menuMapper.findByGnBMenus();
    }
}
