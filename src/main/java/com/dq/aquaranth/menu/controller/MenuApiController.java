package com.dq.aquaranth.menu.controller;


import com.dq.aquaranth.menu.constant.ErrorCode;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.exception.MenuException;
import com.dq.aquaranth.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
//@Menu("ROLE0010")
public class MenuApiController {

    private final MenuService menuService;

    @Operation(summary = "전체 메뉴 조회", description = "URL 정보를 포함한 모든 메뉴를 조회합니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @GetMapping
    public List<MenuResponseDTO> findAllMenuInformation() {
        return menuService.findAll();
    }

    @Operation(summary = "메뉴코드 단건 메뉴 조회", description = "메뉴코드를 이용하여 단건 메뉴를 조회합니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @GetMapping("/findcode")
    public Optional<MenuResponseDTO> findByMenuCode(@RequestParam(value = "menuCode") String menuCode) {
        return Optional.ofNullable(menuService.findByMenuCode(menuCode)
                .orElseThrow(() -> new MenuException(ErrorCode.MENU_NOT_FOUND)));
    }

    @Operation(summary = "메뉴번호 단건 메뉴 조회", description = "메뉴번호를 이용하여 단건 메뉴를 조회합니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @GetMapping("/findno")
    public Optional<MenuResponseDTO> findByMenuNo(@RequestParam(value = "menuNo") Long menuNo) {
        return Optional.ofNullable(menuService.findByMenuNo(menuNo)
                .orElseThrow(() -> new MenuException(ErrorCode.MENU_NOT_FOUND)));
    }

    @Operation(summary = "상위 메뉴 조회", description = "하위 메뉴코드를 이용하여 상위메뉴들를 조회합니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @GetMapping("/finduppermenu/{menuCode}")
    public List<MenuResponseDTO> findUpperMenus(@PathVariable(value = "menuCode") String menuCode) {
        return menuService.findByMenuCodeUpperRecursive(menuCode);
    }

    @Operation(summary = "하위 메뉴 조회", description = "상위 메뉴코드를 이용하여 하위메뉴들을 조회합니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @GetMapping("/findundermenu/{menuCode}")
    public List<MenuResponseDTO> findUnderMenus(@PathVariable(value = "menuCode") String menuCode) {
        return menuService.findByMenuCodeUnderRecursive(menuCode);
    }

    @Operation(summary = "유저가 접근할 수 있는 메뉴 조회", description = "유저의 아이디를 이용하여 유저가 접근할 수 있는 메뉴를 조회합니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @GetMapping("/findusermenu/{username}")
    public List<MenuResponseDTO> findMenusByLoginUsername(@PathVariable(value = "username") String username) {
        return menuService.findMenusByLoginUsername(username);
    }

    @Operation(summary = "메뉴 상태 업데이트", description = "메뉴 상태를 업데이트 합니다. 반환되는 정보는 업데이트된 메뉴의 정보입니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @PutMapping("/update")
    public Optional<MenuResponseDTO> update(@RequestBody MenuUpdateDTO menuUpdateDTO) {
        return menuService.update(menuUpdateDTO);
    }
    @Operation(summary = "메뉴 추가", description = "메뉴를 추가합니다. 반환되는 정보는 추가된 메뉴의 정보입니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @PutMapping("/insert")
    public Optional<MenuResponseDTO> insert(MenuInsertDTO menuInsertDTO, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        return menuService.insert(menuInsertDTO, multipartFile);
    }
//    @Operation(summary = "메뉴 상태 업데이트", description = "메뉴 상태를 업데이트 합니다. 반환되는 정보는 업데이트된 메뉴의 정보입니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
//    @PostMapping("/updateicon")
//    public Optional<MenuResponseDTO> updateByMenuIcon(@RequestPart(value = "file") MultipartFile multipartFile, String menuCode) throws Exception {
//        log.info(menuCode);
//        return menuService.updateByMenuIcon(multipartFile);
    @Operation(summary = "메뉴 상태 업데이트", description = "메뉴 상태를 업데이트 합니다. 반환되는 정보는 업데이트된 메뉴의 정보입니다.", responses = @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")))
    @PutMapping(value = "/updateicon", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Optional<MenuResponseDTO> updateByMenuIcon(MultipartFileDTO fileDto) throws Exception {
        return menuService.updateIcon(fileDto);
    }
}
