package com.dq.aquaranth.employee.service;

import com.dq.aquaranth.employee.dto.EmployeeDTO;
import com.dq.aquaranth.employee.mapper.EmployeeMapper;
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
