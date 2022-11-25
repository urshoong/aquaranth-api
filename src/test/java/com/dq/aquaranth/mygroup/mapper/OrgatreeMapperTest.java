package com.dq.aquaranth.mygroup.mapper;

import com.dq.aquaranth.mygroup.dto.orgatree.OrgaTreeDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Log4j2
class OrgatreeMapperTest {

    @Autowired(required = false)
    OrgatreeMapper orgatreeMapper;

    @Test
    @DisplayName("조직도 트리 출력")
    void findAllOrga() {
        Long companyNo = 1L;
        Long upperDeptNo = 0L;
        Long depth = 0L;

        List<OrgaTreeDTO> orgaTreeDTO
                = orgatreeMapper.findAllOrga(upperDeptNo, depth, companyNo);
        orgaTreeDTO.forEach(log::info);
    }
}
