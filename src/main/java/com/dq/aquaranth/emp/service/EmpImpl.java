package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@ToString
public class EmpImpl implements EmpService {

    private final EmpMapper mapper;

    @Override
    public List<EmpDTO> empList() {
        return mapper.empList();
    }

    @Override
    public EmpDTO empRead(Long emp_no) {
        return mapper.empSelectOne(emp_no);
    }
}
