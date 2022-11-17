package com.dq.aquaranth.menu.controller;


import com.dq.aquaranth.menu.dto.response.AllMenuResponse;
import com.dq.aquaranth.menu.dto.response.MenuResponse;
import com.dq.aquaranth.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "전체 메뉴 조회",
            description = "전체 메뉴를 가져옵니다.",
            responses = @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json")))
    @GetMapping
    public List<AllMenuResponse> findAllMenus(){
        return menuService.findAllMenus();
    }

}
