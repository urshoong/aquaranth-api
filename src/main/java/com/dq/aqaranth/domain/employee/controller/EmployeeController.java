package com.dq.aqaranth.domain.employee.controller;

import com.dq.aqaranth.domain.employee.dto.EmployeeDTO;
import com.dq.aqaranth.domain.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/list")
    public List<EmployeeDTO> employeeList() {
        List<EmployeeDTO> list = service.employeeList();
        return list;
    }

}
