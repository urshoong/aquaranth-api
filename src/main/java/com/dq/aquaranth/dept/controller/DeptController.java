package com.dq.aquaranth.dept.controller;

import com.dq.aquaranth.dept.dto.DeptCriteria;
import com.dq.aquaranth.dept.dto.DepartmentDTO;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.dto.DeptTreeDTO;
import com.dq.aquaranth.dept.service.DeptService;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.MenuCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/dept2")
@RequiredArgsConstructor
@MenuCode(MenuCodes.ORGA0020)
public class DeptController {

    private final DeptService deptService;

    //조회
    @GetMapping("/{dept_no}")
    public DeptDTO getOne(@PathVariable("dept_no") Long dept_no){

        DeptDTO result = deptService.getOne(dept_no);

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
    @DeleteMapping("/{dept_no}")
    public Map<Object, Object> remove(@PathVariable("dept_no") Long dept_no) {
        Long result =  deptService.remove(dept_no);

        return Map.of("result", "success");
    }

    //수정
    @PutMapping("/{dept_no}")
    public DeptDTO modify(@PathVariable("dept_no") Long dept_no , @RequestBody DeptDTO deptDTO) {
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
    //에로사항 : /api/dept2/{gno} 는 /api/dept2/{dept_no}와 곂쳐서 에러 뜸 => 앞에 list붙여서 출력함

    @GetMapping("/list/{upper_dept_no}/{depth}")
    public List<DeptDTO> listDept2 (@PathVariable("upper_dept_no") Long upper_dept_no, @PathVariable("depth") int depth) {

        return deptService.listDepth(upper_dept_no, depth);
    }

    @GetMapping("/tree/{companyCode}")
    public List<DeptTreeDTO> listTree(@PathVariable("companyCode") Long companyCode ){

        return deptService.getTree(companyCode);
      }

    // 해당 회사의 부서 목록 출력
    @GetMapping("/readName/{companyNo}")
    public List<DepartmentDTO> getDeptName(@PathVariable("companyNo") Long companyNo) {
        return deptService.findByCompanyNo(companyNo);
    }

}
