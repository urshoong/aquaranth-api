package com.dq.aquaranth.emp.controller;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/emp")
public class EmpController {
    private final EmpService service;

    @GetMapping("/information")
    public List<EmpDTO> getEmpList() {
        List<EmpDTO> list = service.empList();
        return list;
    }

    @GetMapping("/read/{empNo}")
    public EmpDTO getEmp(@PathVariable("empNo") Long empNo) {
        EmpDTO read = service.empRead(empNo);
        return read;
    }

    @PostMapping("/insert")
    public Long registerEmp(@RequestBody EmpDTO empInsertDTO){
        return service.empRegister(empInsertDTO);
    }

    @PutMapping(value = "/modify/{empNo}")
    public Long modifyEmp(@RequestBody EmpUpdateDTO empUpdateDTO){
        return service.empModify(empUpdateDTO);
    }

    @DeleteMapping(value = "/delete/{empNo}")
    public Long removeEmp(@PathVariable("empNo") Long empNo){
        return service.empRemove(empNo);
    }
}
