package com.dq.aquaranth.menu.service;

import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMenuService implements MenuService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final MenuMapper menuMapper;

    @Override
    public List<MenuResponseDTO> findAllMenus() {
        return menuMapper.findAllMenus();
    }

    @Override
    public List<MenuResponseDTO> findByGnbMenus() {
        return null;
    }

    @Override
    public Integer update(MenuUpdateDTO menuUpdateDTO) {
        return menuMapper.update(menuUpdateDTO);
    }

    @Override
    public List<MenuResponseDTO> findByMenu() {
        return null;
    }


}
