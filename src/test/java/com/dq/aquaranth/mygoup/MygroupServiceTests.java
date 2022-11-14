package com.dq.aquaranth.mygoup;

import com.dq.aquaranth.mygroup.dto.MygroupModifyDTO;
import com.dq.aquaranth.mygroup.service.MygroupService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MygroupServiceTests {

    @Autowired(required = false)
    MygroupService mygroupService;

    @Test
    @DisplayName("로그인한 사원의 마이그룹 생성")
    void registerMygroupTest() {
        String username = "admin";
        log.info(mygroupService.insert(username));
    }

    @Test
    @DisplayName("로그인한 사원의 마이그룹 수정")
    void modifyMygroupTest() {
        String mygroupName = "수정그룹";
        String username = "admin";
        MygroupModifyDTO mygroupModifyDTO = MygroupModifyDTO
                .builder()
                .mygroupName(mygroupName)
                .username(username)
                .modUser(username)
                .build();
        log.info(mygroupService.update(mygroupModifyDTO));
    }

    @Test
    @DisplayName("로그인한 사원의 마이그룹 삭제")
    void removeMygroupTest() {
        String username = "admin";
        log.info(mygroupService.delete(username));
    }
}
