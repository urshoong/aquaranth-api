package com.dq.aquaranth.dept.service;

import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeptService{

    private final DeptMapper deptMapper;

    //부서 목록 출력
    public List<DeptDTO> deptList() {
        return deptMapper.deptList();
    }
}
