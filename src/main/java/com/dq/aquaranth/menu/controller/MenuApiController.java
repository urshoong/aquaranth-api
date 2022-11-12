package com.dq.aquaranth.menu.controller;


import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuApiController {

    private final MenuService menuService;

    @Operation(summary = "전체 메뉴 조회",
            description = "URL 정보를 포함한 모든 메뉴를 조회합니다.",
            responses = @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json")))
    @GetMapping
    public List<MenuResponseDTO> findAllMenus(){
        return menuService.findAllMenuInformation();
    }

    @Operation(summary = "메뉴코드 단건 메뉴 조회",
            description = "메뉴코드를 이용하여 단건 메뉴를 조회합니다.",
            responses = @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json")))
    @GetMapping("/find")
    public Optional<MenuResponseDTO> findByMenuCode(
            @RequestParam(value = "menuCode") String menuCode)
//            , @RequestParam(value = "menuNo") Long menuNo)
    {
        return menuService.findByMenuCode(menuCode);

    }


    @Operation(summary = "GNB 메뉴 조회",
            description = "상위메뉴번호가 없는 메뉴(GNB)를 조회합니다.",
            responses = @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json")))
    @GetMapping("/gnb")
    public List<MenuResponseDTO> findGnbMenus(){
        return menuService.findByUpperMenuNoIsNull();
    }

    @Operation(summary = "하위 메뉴 조회",
            description = "하위 메뉴코드를 이용하여 상위메뉴번호가 null일때 까지 조회합니다.",
            responses = @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json")))
    @GetMapping("/findundermenu/{menuCode}")
    public List<MenuResponseDTO> findUnderMenus(@PathVariable(value = "menuCode") String menuCode){
        return menuService.findByMenuCodeUnderRecursive(menuCode);
    }
}
