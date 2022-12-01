package com.dq.aquaranth.menu.controller;

import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.MenuCodes;
import com.dq.aquaranth.menu.dto.request.MenuInsertDTO;
import com.dq.aquaranth.menu.dto.request.MenuQueryDTO;
import com.dq.aquaranth.menu.dto.request.MenuUpdateDTO;
import com.dq.aquaranth.menu.dto.request.tree.MenuTreeQueryDTO;
import com.dq.aquaranth.menu.dto.response.MenuDetailResponseDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.dto.response.tree.MenuTreeResponseDTO;
import com.dq.aquaranth.menu.service.AuthorizationMenuService;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework .http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/menu/auth")
@RequiredArgsConstructor
@MenuCode(MenuCodes.ROLE0030)
public class AuthorizationMenuController {

    private final AuthorizationMenuService authorizationMenuService;

    @GetMapping
    public MenuResponseDTO findBy(MenuQueryDTO menuQueryDTO) {
        return authorizationMenuService.findBy(menuQueryDTO);
    }

    @GetMapping("/list")
    public List<MenuResponseDTO> findAllBy(MenuQueryDTO menuQueryDTO) {
        return authorizationMenuService.findAllBy(menuQueryDTO);
    }

    @GetMapping("/findusermenu/{username}")
    public List<MenuResponseDTO> findMenusByLoginUsername(@PathVariable(value = "username") String username) {
        return authorizationMenuService.findMenusByLoginUsername(username);
    }




    @GetMapping("/tree/list")
    public List<MenuTreeResponseDTO> findByTree(MenuTreeQueryDTO menuTreeQueryDTO) {
        return authorizationMenuService.findUnderMenuByTwo(menuTreeQueryDTO);
    }
    /**
     * 트리에서 받은 메뉴번호를 이용하여 메뉴를 상세조회 합니다.
     */
    @GetMapping("/detail")
    public MenuDetailResponseDTO findMenuDetailsBy(MenuQueryDTO menuQueryDTO) {
        return authorizationMenuService.findMenuDetailsBy(menuQueryDTO);
    }
    /**
     * 메뉴 정보를 업데이트 합니다.
     */
    @PutMapping("/update")
    public MenuResponseDTO update(@RequestBody MenuUpdateDTO menuUpdateDTO) {
        return authorizationMenuService.update(menuUpdateDTO);
    }
    /**
     * 메뉴 아이콘을 업데이트 합니다.
     */
    @PutMapping(value = "/updateicon", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public MenuResponseDTO updateByMenuIcon(MultipartFileDTO multipartFileDTO) throws Exception {
        return authorizationMenuService.updateIcon(multipartFileDTO);
    }
    @PostMapping("/insert")
    public MenuResponseDTO insert(MenuInsertDTO menuInsertDTO, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        return authorizationMenuService.insert(menuInsertDTO, multipartFile);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(MenuQueryDTO menuQueryDTO){
        authorizationMenuService.delete(menuQueryDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
