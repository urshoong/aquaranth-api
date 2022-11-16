package com.dq.aquaranth.mygroup.controller;

import com.dq.aquaranth.mygroup.dto.FavoriteEmpInformationDTO;
import com.dq.aquaranth.mygroup.dto.FavoriteEmpListDTO;
import com.dq.aquaranth.mygroup.dto.FavoriteFindEmpInfoDTO;
import com.dq.aquaranth.mygroup.dto.FavoriteRegisterDTO;
import com.dq.aquaranth.mygroup.service.MygroupService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final MygroupService mygroupService;

    /**
     * 해당 마이그룹의 즐겨찾기에 사원 추가
     */
    @PostMapping("/register")
    public Long registerFavorite(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO) {
        return mygroupService.insertFavorite(favoriteRegisterDTO);
    }

    /**
     * 즐겨찾기에 등록된 사원 중 해당 사원 정보 출력
     */
    @GetMapping("/list")
    public List<FavoriteEmpListDTO> getFavoriteEmplist(Long mygroupNo) {
        return mygroupService.findAllEmp(mygroupNo);
    }

    /**
     * 즐겨찾기에 등록된 사원 중 해당 사원 정보 출력
     */
    @GetMapping("/information/{empNo}")
    public FavoriteEmpInformationDTO getFavoriteEmpInformation(@RequestBody FavoriteFindEmpInfoDTO favoriteFindEmpInfoDTO) {
        return mygroupService.findByEmpNo(favoriteFindEmpInfoDTO);
    }
}
