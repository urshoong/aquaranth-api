package com.dq.aquaranth.dept.service;

import com.dq.aquaranth.dept.dto.DepartmentDTO;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class DeptService {

    private final DeptMapper deptMapper;

    //추가
    public DeptDTO register(DeptDTO deptDTO) {
        deptMapper.insert(deptDTO);

        Long newDNO = deptDTO.getDeptNo();

        return deptMapper.select(newDNO);
    }

    //1개 조회
    public DeptDTO getOne(Long deptNo) {
        return deptMapper.select(deptNo);
    }

    //삭제
    public int remove(Long deptNo) {
        return deptMapper.delete(deptNo);
    }

    //수정
    public DeptDTO modify(DeptDTO deptDTO) {
        deptMapper.update(deptDTO);

        Long newDNO = deptDTO.getDeptNo();
        DeptDTO result = deptMapper.select(newDNO);

        return result;
    }

    // 회사 번호로 부서 목록 조회
    public List<DepartmentDTO> findByCompanyNo(Long companyNo)
    {
        return deptMapper.findByCompanyNo(companyNo);
    }
}
