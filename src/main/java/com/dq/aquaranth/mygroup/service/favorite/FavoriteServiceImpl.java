package com.dq.aquaranth.mygroup.service.favorite;

import com.dq.aquaranth.mygroup.dto.favorite.FavoriteEmpListDTO;
import com.dq.aquaranth.mygroup.dto.favorite.FavoriteInsertDTO;
import com.dq.aquaranth.mygroup.mapper.FavoriteeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteeMapper favoriteeMapper;

    /**
     * 즐겨찾기
     */
    @Override
    public Long insert(FavoriteInsertDTO favoriteInsertDTO) {
        log.info("해당 마이그룹에 사원 즐겨찾기");
        return favoriteeMapper.insert(favoriteInsertDTO);
    }

    @Override
    public List<FavoriteEmpListDTO> findAll(Long mygroupNo) {
        log.info("해당 마이그룹에 즐겨찾기 된 모든 사원 정보 출력");
        return favoriteeMapper.findAll(mygroupNo);
    }

    @Override
    public Long delete(Long mygroupNo, Long orgaNo) {
        log.info("해당 마이그룹에 즐겨찾기 된 사원 삭제");
        return favoriteeMapper.delete(mygroupNo, orgaNo);
    }
}
