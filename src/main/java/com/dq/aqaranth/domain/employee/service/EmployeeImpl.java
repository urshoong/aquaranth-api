package com.dq.aqaranth.domain.employee.service;

import com.dq.aqaranth.domain.employee.dto.EmployeeDTO;
import com.dq.aqaranth.domain.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@ToString
public class EmployeeImpl implements EmployeeService{

    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeDTO> employeeList() {
        return mapper.employeeList();
    }
}
