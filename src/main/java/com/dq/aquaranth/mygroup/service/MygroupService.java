package com.dq.aquaranth.mygroup.service;

import com.dq.aquaranth.mygroup.dto.*;

import java.util.List;

public interface MygroupService {

    /**
     * 마이그룹
     */
    Long insertMygroup(String username);
    Long updateMygroup(MygroupModifyDTO mygroupModifyDTO);
    Long deleteMygroup(MygroupRemoveDTO mygroupRemoveDTO);

    /**
     * 즐겨찾기
     */
    Long insertFavorite(FavoriteRegisterDTO favoriteRegisterDTO);
    List<FavoriteEmpListDTO> findAllEmp(Long mygroupNo);
    FavoriteEmpInformationDTO findByEmpNo(FavoriteFindEmpInfoDTO favoriteFindEmpInfoDTO);
}
