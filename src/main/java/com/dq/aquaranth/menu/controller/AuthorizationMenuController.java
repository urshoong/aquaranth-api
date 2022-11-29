package com.dq.aquaranth.menu.controller;

import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.MenuCodes;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuRequestDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.dq.aquaranth.menu.service.MenuService;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 권한이 부여된 유저만 사용할 수 있는
 * 메뉴 컨트롤러 입니다.
 * 권한 여부와 관계없이 모두 조회합니다.
 */
@Log4j2
@RestController
@RequestMapping("/api/auth/menu")
@RequiredArgsConstructor
@MenuCode(MenuCodes.ROLE0030)
public class AuthorizationMenuController {

    private final MenuService menuService;
    private final MenuMapper menuMapper;

    @GetMapping
    public MenuResponseDTO findBy(MenuRequestDTO menuRequestDTO) {
        return menuService.findBy(menuRequestDTO);
    }

    @GetMapping("/list")
    public List<MenuResponseDTO> findAllBy(MenuRequestDTO menuRequestDTO) {
        return menuService.findAllBy(menuRequestDTO);
    }

    @GetMapping("/findusermenu/{username}")
    public List<MenuResponseDTO> findMenusByLoginUsername(@PathVariable(value = "username") String username) {
        return menuService.findMenusByLoginUsername(username);
    }

    @PutMapping("/update")
    public MenuResponseDTO update(@RequestBody MenuUpdateDTO menuUpdateDTO) {
        return menuService.update(menuUpdateDTO);
    }

    @PutMapping("/insert")
    public MenuResponseDTO insert(MenuInsertDTO menuInsertDTO, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        return menuService.insert(menuInsertDTO, multipartFile);
    }

    @PutMapping(value = "/updateicon", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public MenuResponseDTO updateByMenuIcon(MultipartFileDTO multipartFileDTO) throws Exception {
        return menuService.updateIcon(multipartFileDTO);
    }

    @GetMapping("/lnb")
    public List<MenuResponseDTO> getLnbList(String menuCode) {
        return menuMapper.findByMenuCodeUnderRecursive(menuCode);
    }
}
