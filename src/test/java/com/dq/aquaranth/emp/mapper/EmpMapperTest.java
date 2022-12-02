package com.dq.aquaranth.emp.mapper;

import com.dq.aquaranth.emp.dto.EmpFileDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class EmpMapperTest {

    @Autowired(required = false)
    private EmpMapper empMapper;

    @Test
    void updateProfile() {
        EmpFileDTO empFileDTO =
                EmpFileDTO.builder()
                        .uuid(null)
                        .filename(null)
                        .empNo(2L)
                        .build();
        log.info(empMapper.updateProfile(empFileDTO));
    }
}
