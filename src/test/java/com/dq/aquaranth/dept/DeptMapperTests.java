package com.dq.aquaranth.dept;

import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class DeptMapperTests {

    @Autowired(required = false)
    DeptMapper deptMapper;

    @Test
    void insertTest() {
        DeptDTO deptDTO = DeptDTO
                .builder()
                .upperDeptNo(3L)
                .dname("철강3팀")
                .mainflag(true)
                .delflag(true)
                .ddesc("철강3팀")
                .build();

        deptMapper.insert(deptDTO);

//        log.info("result : " + deptDTO.getDeptNo() );
    }

    @Test
    void select() {
        DeptDTO result = deptMapper.select(20L);
        log.info(result);
    }


    @Test
    void update() {
        DeptDTO deptDTO = DeptDTO
                .builder()
                .deptNo(24L)
                .upperDeptNo(4L)
                .dname("철강66팀")
                .delflag(true)
                .mainflag(true)
                .deptSort(1)
                .ddesc("철강66팀")
                .build();

        log.info(deptMapper.update(deptDTO));
    }


    @Test
    void delete() {
        int result = deptMapper.delete(24L);

        log.info(result);
    }

    @Test
    public void list() {
        DeptCriteria deptCriteria = DeptCriteria.builder().build();
    }

    @Test
    public void findByCompanyNoTest(){
        log.info(deptMapper.findByCompanyNo(1L));
    }

}
