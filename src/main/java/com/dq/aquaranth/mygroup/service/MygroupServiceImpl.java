package com.dq.aquaranth.mygroup.service;

import com.dq.aquaranth.mygroup.dto.*;
import com.dq.aquaranth.mygroup.mapper.MygroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MygroupServiceImpl implements MygroupService {

    private final MygroupMapper mygroupMapper;

    /**
     * 마이그룹
     */
    @Override
    public Long insertMygroup(String username) {
        log.info("로그인한 사원의 마이그룹 생성");
        return mygroupMapper.insertMygroup(username);
    }

    @Override
    public Long updateMygroup(MygroupModifyDTO mygroupModifyDTO) {
        log.info("로그인한 사원의 마이그룹 수정");
        return mygroupMapper.updateMygroup(mygroupModifyDTO);
    }

    @Override
    public Long deleteMygroup(MygroupRemoveDTO mygroupRemoveDTO) {
        log.info("해당 마이그룹 안 즐겨찾기의 모든 사원 삭제");
        mygroupMapper.deleteFavorite(mygroupRemoveDTO.getMygroupNo());
        log.info("로그인한 사원의 마이그룹 삭제");
        return mygroupMapper.deleteMygroup(mygroupRemoveDTO);
    }


    /**
     * 즐겨찾기
     */
    @Override
    public Long insertFavorite(FavoriteRegisterDTO favoriteRegisterDTO) {
        log.info("해당 마이그룹의 즐겨찾기에 사원 추가");
        return mygroupMapper.insertFavorite(favoriteRegisterDTO);
    }

    @Override
    public List<FavoriteEmpListDTO> findAllEmp(Long mygroupNo) {
        log.info("즐겨찾기에 등록된 모든 사원 정보 출력");
        return mygroupMapper.findAllEmp(mygroupNo);
    }

    @Override
    public FavoriteEmpInformationDTO findByEmpNo(FavoriteFindEmpInfoDTO favoriteFindEmpInfoDTO) {
        log.info("즐겨찾기에 등록된 사원 중 해당 사원 정보 출력");
        return mygroupMapper.findByEmpNo(favoriteFindEmpInfoDTO);
    }

}
