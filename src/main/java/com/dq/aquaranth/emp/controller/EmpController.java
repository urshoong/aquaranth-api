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
    public List<EmpDTO> empList() {
        List<EmpDTO> list = service.empList();
        return list;
    }

    @GetMapping("/read/{empNo}")
    public EmpDTO empRead(@PathVariable("empNo") Long empNo) {
        EmpDTO read = service.empRead(empNo);
        return read;
    }

    @PostMapping("/insert")
    public Integer empInsert(@RequestBody EmpDTO empDTO){
        return service.empInsert(empDTO);
    }

    @PutMapping(value = "/modify/{empNo}")
    public Integer empModify(@RequestBody EmpUpdateDTO empUpdateDTO){
        int modify = service.empModify(empUpdateDTO);
        return modify;
    }

    @DeleteMapping(value = "/delete/{empNo}")
    public Integer empDelete(@PathVariable("empNo") Long empNo){
        int delete = service.empDelete(empNo);
        return delete;
    }

}
