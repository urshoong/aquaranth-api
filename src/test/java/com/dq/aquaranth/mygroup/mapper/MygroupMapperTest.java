package com.dq.aquaranth.mygroup.mapper;

import com.dq.aquaranth.mygroup.dto.mygroup.MygroupListDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class MygroupMapperTest {

    @Autowired(required = false)
    MygroupMapper mygroupMapper;

    @Test
    @DisplayName("로그인한 사원의 마이그룹 전체 조회")
    void findAllMygroup() {
        String username = "admin";

        List<MygroupListDTO> mygroupListDTO
                = mygroupMapper.findAllMygroup(username);
        mygroupListDTO.forEach(log::info);
    }

    @Test
    @DisplayName("로그인한 사원의 마이그룹 생성")
    void insert() {
        String username = "emp10";

        Long insertResult = mygroupMapper.insert(username);
        log.info("마이그룹 생성 결과 : " + insertResult);
    }
}
