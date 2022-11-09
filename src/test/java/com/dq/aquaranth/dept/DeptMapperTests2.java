package com.dq.aquaranth.dept;


import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import com.dq.aquaranth.dept.mapper.DeptMapper2;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class DeptMapperTests2 {

    @Autowired(required = false)
    private DeptMapper2 deptMapper2;

    @Test
    public void testSelect() {
        DeptDTO2 selectOne = deptMapper2.select(5L);
        log.info(selectOne);
    }

    @Test
    public void testInsert() {
        DeptDTO2 deptDTO2 = DeptDTO2
                .builder()
                .upperDeptNo(2L)
                .dname("개발 5팀")
                .ddesc("개발 5팀")
                .mainflag(false)
                .delflag(true)
                .build();

        int result = deptMapper2.insert(deptDTO2);
        log.info("result : " + result);
    }


    @Test
    public void testDelete() {
        Long no = 30L;
        int result = deptMapper2.delete(no);
        log.info("delete : " + result);
    }

    @Test
    public void testUpdate() {
        DeptDTO2 deptDTO2 = DeptDTO2
                .builder()
                .deptNo(23L)
                .upperDeptNo(2L)
                .dname("철강 1팀 업데이트")
                .delflag(false)
                .mainflag(false)
                .deptSort(1)
                .ddesc("철강 1팀 업데이트")
                .build();

        int result = deptMapper2.update(deptDTO2);
        log.info("update : " + result);
    }

    @Test
    public void testList() {
        int skip = 0;
        int size = 10;

        List<DeptDTO2> list = deptMapper2.getList(skip, size);

        list.forEach(deptDTO2 -> log.info(deptDTO2));
    }


}
