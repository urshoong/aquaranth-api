package com.dq.aquaranth.emp.controller;
// TODO Controller에 register, 조직 read 작성
import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/readOrga/{empNo}")
    public List<EmpSelectOrga> empOrgaList(@PathVariable("empNo") Long empNo) {
        return service.empOrgaList(empNo);
    }


    ////////////////////////////////////////////
    public static String getRemoteIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
        //사용할 곳에서 , HttpServletRequest request 쓰고 getRemoteIp(request)
        //0:0:0:0:0:0:0:1 로, 이는 IPv6 형식의 값. 추후 로그아웃 때 사용하기로 한다.
    }
    ////////////////////////////////////////////
    @PostMapping("/register")
    public EmpDTO registerEmp (@Valid @RequestBody  EmpInsertInformationDTO reqDTO) throws IllegalAccessException{


        //조직 DTO에 받은 값 넣기
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(reqDTO.getDeptNo())
                .build();

        //조직 테이블의 last_insert_id 저장
        Long orgaId = orgaDTO.getOrgaNo();

        EmpDTO empDTO = EmpDTO.builder()
                .empName(reqDTO.getEmpName())
                .username(reqDTO.getUsername())
                .password(reqDTO.getPassword())
                .gender(reqDTO.getGender())
                .empPhone(reqDTO.getEmpPhone())
                .empAddress(reqDTO.getEmpAddress())
                .empProfile(reqDTO.getEmpProfile())
                .email(reqDTO.getEmail())
                .lastLoginIp(null)

                .build();


        //사원 테이블의 last_insert_id 저장
        Long empId = empDTO.getEmpNo();

        //사원 매핑 테이블
        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .empNo(empId)
                .orgaNo(orgaId)
                .empRank(reqDTO.getEmpRank())
                .build();

        service.insert(orgaDTO, empDTO, empMappingDTO);

        return empDTO;
    }

    @PutMapping(value = "/modify/{empNo}")
    public Long modifyEmp(@Valid @RequestBody EmpUpdateDTO empUpdateDTO) {
        return service.empModify(empUpdateDTO);
    }

    @DeleteMapping(value = "/remove/{empNo}")
    public Long removeEmp(@PathVariable("empNo") Long empNo) {
        return service.empRemove(empNo);
    }
}
