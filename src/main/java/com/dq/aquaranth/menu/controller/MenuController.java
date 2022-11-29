package com.dq.aquaranth.menu.controller;


import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일반 메뉴 컨트롤러 입니다.
 * 권한에 해당하는 내용만 조회됩니다.
 */
@Log4j2
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@MenuCode
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO) {
        return menuService.findBy(menuRequestDTO);
    }

    @GetMapping("/list")
    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO) {
        return menuService.findAllBy(menuRequestDTO);
    }
}
