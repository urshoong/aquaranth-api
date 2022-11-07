package com.dq.aquaranth.dept.controller;

import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    //조회
    @GetMapping("/{deptNo}")
    public DeptDTO getOne(@PathVariable("deptNo") Long deptNo) {
        return deptService.getOne(deptNo);
    }

    //등록
    @PostMapping("/")
    public DeptDTO register(@RequestBody DeptDTO deptDTO){
        return deptService.register(deptDTO);
    }

    //삭제
    @DeleteMapping("{deptNo}")
    public Map<String, String> remove(@PathVariable("deptNo") Long deptNo ){
        deptService.remove(deptNo);

        return Map.of("result", "success");
    }

    //수정
    @PutMapping("{deptNo}")
    public DeptDTO modify(@PathVariable("deptNo") Long deptNo, @RequestBody DeptDTO deptDTO) {

        deptService.modify(deptDTO);

        Long newDNO = deptDTO.getDeptNo();

        DeptDTO result = deptService.getOne(newDNO);
        return result;

    }


}
