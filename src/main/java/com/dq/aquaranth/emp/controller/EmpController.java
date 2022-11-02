package com.dq.aquaranth.emp.controller;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/emp")
public class EmpController {
    private final EmpService service;

    @GetMapping("/info")
    public List<EmpDTO> empList() {
        List<EmpDTO> list = service.empList();
        return list;
    }

    @GetMapping("/read/{emp_no}")
    public EmpDTO empRead(@PathVariable("emp_no") Long emp_no) {
        EmpDTO read = service.empRead(emp_no);
        return read;
    }

}
