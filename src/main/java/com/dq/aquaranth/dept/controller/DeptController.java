package com.dq.aquaranth.dept.controller;

import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/dept2")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    //조회
    @GetMapping("/{deptNo}")
    public DeptDTO getOne(@PathVariable("deptNo") Long deptNo){

        DeptDTO result = deptService.getOne(deptNo);

        return result;

    }

    //등록
    @PostMapping
    public Map<Object, Object> register(@RequestBody DeptDTO deptDTO2) {

        log.info(deptDTO2);

        Map<Object, Object> result = deptService.register(deptDTO2);

        return result;
    }

//    @PostMapping("/")
//    public Map<Object, Object> register(@RequestBody DeptDTO2 deptDTO2) {
//
//        log.info(deptDTO2);
//
//
//        Map<Object, Object> result = deptService2.register(deptDTO2);
//
//        return result;
//    }

    //삭제
    @DeleteMapping("/{deptNo}")
    public Map<Object, Object> remove(@PathVariable("deptNo") Long deptNo) {
        Long result =  deptService.remove(deptNo);

        return Map.of("result", "success");
    }

    //수정
    @PutMapping("/{deptNo}")
    public DeptDTO modify(@PathVariable("deptNo") Long deptNo , @RequestBody DeptDTO deptDTO) {
        DeptDTO modifyDTO = deptService.modify(deptDTO);

        log.info("modifyDTO : " + modifyDTO);

        return modifyDTO;

    }

    @GetMapping("/list")
    public List<DeptDTO> list(DeptCriteria deptCriteria) {
        return deptService.list(deptCriteria.getSkip(), deptCriteria.getSize());
    }

    @GetMapping("/list/{gno}")
    public List<DeptDTO> listDept (@PathVariable int gno) {

        return deptService.listDept(gno);
    }
    //에로사항 : /api/dept2/{gno} 는 /api/dept2/{deptNo}와 곂쳐서 에러 뜸 => 앞에 list붙여서 출력함

    @GetMapping("/list/{uppperDeptNo}/{depth}")
    public List<DeptDTO> listDept2 (@PathVariable("uppperDeptNo") Long uppperDeptNo, @PathVariable("depth") int depth) {

        return deptService.listDepth(uppperDeptNo, depth);
    }
}
