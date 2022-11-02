package com.dq.aqaranth.domain.menu.controller;


import com.dq.aqaranth.domain.menu.dto.response.MenuResponse;
import com.dq.aqaranth.domain.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<MenuResponse>> getAllMenus(){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(menuService.findAllMenus());
    }
}
