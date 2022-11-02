package com.dq.aquaranth.dept.controller;

import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    //부서 리스트 출력
    @GetMapping("/deptlist")
    public List<DeptDTO> deptList() { return deptService.deptList(); }


}
