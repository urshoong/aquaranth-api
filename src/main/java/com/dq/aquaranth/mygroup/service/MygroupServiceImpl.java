package com.dq.aquaranth.mygroup.service;

import com.dq.aquaranth.mygroup.dto.MygroupModifyDTO;
import com.dq.aquaranth.mygroup.mapper.MygroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MygroupServiceImpl implements MygroupService {

    private final MygroupMapper mygroupMapper;

    @Override
    public Long insert(String username) {
        log.info("로그인한 사원의 마이그룹 생성");
        return mygroupMapper.insert(username);
    }

    @Override
    public Long update(MygroupModifyDTO mygroupModifyDTO) {
        log.info("로그인한 사원의 마이그룹 수정");
        return mygroupMapper.update(mygroupModifyDTO);
    }

    @Override
    public Long delete(String username) {
        log.info("로그인한 사원의 마이그룹 삭제");
        return mygroupMapper.delete(username);
    }
}
