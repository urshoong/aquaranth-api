package com.dq.aquaranth.dept.controller;

import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.dept.service.DeptService2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/dept2")
@RequiredArgsConstructor
public class DeptController2 {

    private final DeptService2 deptService2;

    //조회
    @GetMapping("/{deptNo}")
    public DeptDTO2 getOne(@PathVariable("deptNo") Long deptNo){

        DeptDTO2 result = deptService2.getOne(deptNo);

        return result;

    }

    //등록
    @PostMapping("/")
    public Map<Object, Object> register(@RequestBody DeptDTO2 deptDTO2) {

        log.info(deptDTO2);


        Map<Object, Object> result = deptService2.register(deptDTO2);

        return result;
    }

    @DeleteMapping("/{deptNo}")
    public Map<Object, Object> remove(@PathVariable("deptNo") Long deptNo) {
        Long result =  deptService2.remove(deptNo);

        return Map.of("result", "success");

    }

    @PutMapping("/{deptNo}")
    public DeptDTO2 modify(@PathVariable("deptNo") Long deptNo ,@RequestBody DeptDTO2 deptDTO) {
        DeptDTO2 modifyDTO = deptService2.modify(deptDTO);

        log.info("modifyDTO : " + modifyDTO);

        return modifyDTO;
    }

    @GetMapping("/list")
    public List<DeptDTO2> list( DeptCriteria deptCriteria) {
        return deptService2.list(deptCriteria.getSkip(), deptCriteria.getSize());
    }










}
