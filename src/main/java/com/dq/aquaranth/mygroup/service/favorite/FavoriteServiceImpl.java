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
        Long insertResult = 0L; //즐겨찾기 성공 시 "1", 실패 시 "0"

        //1. 해당 마이그룹 리스트 가져오기
        List<FavoriteEmpListDTO> favoriteEmpListDTO
                = favoriteeMapper.findAll(favoriteInsertDTO.getMygroupNo());

        //2. 해당 마이그룹에 즐겨찾기 할 사원의 조직번호가 존재하는지 확인하기
        Long favoriteEmpOrgaNo = favoriteInsertDTO.getOrgaNo();
        for (FavoriteEmpListDTO empListDTO : favoriteEmpListDTO) {
            if (empListDTO.getOrgaNo().equals(favoriteEmpOrgaNo)) {
                //3-1. 해당 마이그룹에 즐겨찾기 실패
                log.info("해당 사원은 이미 즐겨찾기 되어있습니다.");
                log.info("즐겨찾기 실패결과: " + insertResult);
                return insertResult;
            }
        }

        //3. 해당 마이그룹에 즐겨찾기 성공
        insertResult = favoriteeMapper.insert(favoriteInsertDTO);
        log.info("즐겨찾기 성공결과: " + insertResult);
        return insertResult;
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
