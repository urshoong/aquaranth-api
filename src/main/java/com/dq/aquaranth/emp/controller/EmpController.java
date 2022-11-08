package com.dq.aquaranth.emp.controller;

import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.dto.EmpUpdateDTO;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/emp")
public class EmpController {
    private final EmpService service;

    @GetMapping("/information")
    public List<EmpDTO> getEmpList() {
        return service.empList();
    }

    @GetMapping("/read/{empNo}")
    public EmpDTO getEmp(@PathVariable("empNo") Long empNo) {
        return service.empRead(empNo);
    }

//    @PostMapping("/register")
//    public Long registerEmp(@Valid @RequestBody EmpDTO empInsertDTO) {
//        return service.empRegister(empInsertDTO);
//    }
    // TODO Controller에 register 작성
    

    @PutMapping(value = "/modify/{empNo}")
    public Long modifyEmp(@Valid @RequestBody EmpUpdateDTO empUpdateDTO) {
        return service.empModify(empUpdateDTO);
    }

    @DeleteMapping(value = "/remove/{empNo}")
    public Long removeEmp(@PathVariable("empNo") Long empNo) {
        return service.empRemove(empNo);
    }
}
