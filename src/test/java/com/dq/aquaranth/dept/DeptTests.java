package com.dq.aquaranth.dept;


import com.dq.aquaranth.dept.controller.DeptController;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class DeptTests {

    @Autowired(required = false)
    DeptMapper deptMapper;

    @Test
    void deptListTest() {
        List<DeptDTO> deptList = deptMapper.deptList();
        log.info(deptList);
    }

}
