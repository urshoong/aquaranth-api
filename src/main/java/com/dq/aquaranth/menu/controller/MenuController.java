package com.dq.aquaranth.menu.controller;


import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.response.MenuImportResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public MenuResponseDTO findBy(MenuQueryDTO menuQueryDTO, HttpServletRequest httpServletRequest) {
        return menuService.findBy(menuQueryDTO,httpServletRequest.getUserPrincipal().getName());
    }

    @GetMapping("/list")
    public List<MenuResponseDTO> findAllBy(MenuQueryDTO menuQueryDTO, HttpServletRequest httpServletRequest) {
        return menuService.findAllBy(menuQueryDTO, httpServletRequest.getUserPrincipal().getName());
    }

    @GetMapping("/cache/list")
    public List<MenuResponseDTO> findAllInCache() {
        return menuService.findAllInCache();
    }

    @GetMapping("/init")
    public List<MenuImportResponseDTO> initializeAppImport() {
        return menuService.initializeAppImport();
    }

    @GetMapping("/findinredis")
    public List<MenuResponseDTO> findInRedis(MenuQueryDTO menuQueryDTO, HttpServletRequest httpServletRequest) {
        return menuService.findInRedis(menuQueryDTO, httpServletRequest);
    }
}
