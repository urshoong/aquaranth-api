package com.dq.aquaranth.dept.service;

import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.dept.mapper.DeptMapper2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class DeptService2 {

    private final DeptMapper2 deptMapper2;

    //조회
    public DeptDTO2 getOne(Long deptNo) {
        DeptDTO2 deptDTO2 = deptMapper2.select(deptNo);

        log.info(deptDTO2);

        return deptDTO2;
    } //No로 조회해서 DTO타입의 변수에 초기화


    //등록
    public Map<Object, Object> register(DeptDTO2 deptDTO2) {
        deptMapper2.insert(deptDTO2);

        Long newNo = deptDTO2.getDeptNo();

        log.info(newNo);

        return Map.of("result", newNo);
    }

    public DeptDTO2 modify(DeptDTO2 deptDTO2) {
        int result = deptMapper2.update(deptDTO2);
        Long no = deptDTO2.getDeptNo();

        log.info("no : " + no);

        DeptDTO2 modiftDTO = deptMapper2.select(no);

        log.info("modify : " + modiftDTO);

        return modiftDTO;
    }

    public Long remove(Long deptNo) {
        deptMapper2.delete(deptNo);
        return deptNo;
    }

    public List<DeptDTO2> list(int skip, int size) {
        List<DeptDTO2> result = deptMapper2.getList(skip,size);
        return result;
    }


}
