package com.dq.aquaranth.mygroup.mapper;

import com.dq.aquaranth.mygroup.dto.*;

import java.util.List;

public interface MygroupMapper {

    /**
     * 로그인한 사원의 마이그룹 생성
     */
    Long insertMygroup(String username);

    /**
     * 로그인한 사원의 마이그룹 수정
     */
    Long updateMygroup(MygroupModifyDTO mygroupModifyDTO);

    /**
     * 로그인한 사원의 마이그룹 삭제
     */
    Long deleteMygroup(MygroupRemoveDTO mygroupRemoveDTO);


    /**
     * 해당 마이그룹의 즐겨찾기에 사원 추가
     */
    Long insertFavorite(FavoriteRegisterDTO favoriteRegisterDTO);

    /**
     * 해당 마이그룹 안 즐겨찾기의 모든 사원 삭제
     */
    Long deleteFavorite(Long mygroupNo);

    /**
     * 즐겨찾기에 등록된 모든 사원 정보 출력
     */
    List<FavoriteEmpListDTO> findAllEmp(Long mygroupNo);

    /**
     * 즐겨찾기에 등록된 사원 중 해당 사원 정보 출력
     */
    FavoriteEmpInformationDTO findByEmpNo(FavoriteFindEmpInfoDTO favoriteFindEmpInfoDTO);
}
