package com.dq.aquaranth.emp.controller;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/emp")
public class EmpController {
    private final EmpService empService;

    @GetMapping("/information")
    public List<EmpDTO> getEmpList() {
        return empService.findAll();
    }

    @GetMapping("/read/{empNo}")
    public EmpDTO getEmp(@PathVariable("empNo") Long empNo) {
        return empService.findById(empNo);
    }

    @GetMapping("/readOrga/{empNo}")
    public List<EmpSelectOrga> empOrgaList(@PathVariable("empNo") Long empNo) {
        return empService.findAllOrga(empNo);
    }


    ////////////////////////////////////////////
    public String getRemoteIp(HttpServletRequest request) {

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

        //사용 : String ip  = getRemoteIp(request);

        //사용할 곳에서 , HttpServletRequest request 쓰고 getRemoteIp(request)
        //0:0:0:0:0:0:0:1 로, 이는 IPv6 형식의 값. 추후 로그아웃 때 사용하기로 한다.
    }
    ////////////////////////////////////////////
    @PostMapping("/register")
    public EmpDTO registerEmp (@Valid @RequestBody  EmpInsertInformationDTO reqDTO) throws IllegalAccessException{

        log.info(reqDTO);

        String registrant = "종현"; //TODO regUser들 로그인 id로 바꾸기

        //조직 DTO에 받은 값 넣기
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(reqDTO.getDeptNo())
                .regUser(registrant)
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
                .email(reqDTO.getEmail())
                .firstHiredDate(LocalDate.now())
                .regUser(registrant)
                .build();


        //사원 테이블의 last_insert_id 저장
        Long empNo = empDTO.getEmpNo();

        //사원 매핑 테이블
        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .empNo(empNo)
                .orgaNo(orgaId)
                .empRank(reqDTO.getEmpRank())
                .empRole(reqDTO.getEmpRole())
                .regUser(registrant)
                .build();

        empService.insert(orgaDTO, empDTO, empMappingDTO);

        return empDTO;
    }

    @PostMapping("/registerOrga")
    public Long registerEmpOrga (@RequestBody EmpOrgaInsertDTO reqDTO){
        String registrant = "종현"; //TODO regUser들 로그인 id로 바꾸기
        Long empNo = reqDTO.getEmpNo();

        //조직 DTO에 받은 값 넣기
        EmpOrgaDTO orgaDTO = EmpOrgaDTO.builder()
                .deptNo(reqDTO.getDeptNo())
                .regUser(registrant)
                .build();

        //조직 테이블의 last_insert_id 저장
        Long orgaNo = orgaDTO.getOrgaNo();

        //사원 매핑 테이블
        EmpMappingDTO empMappingDTO = EmpMappingDTO.builder()
                .empNo(empNo)
                .orgaNo(orgaNo)
                .empRank(reqDTO.getEmpRank())
                .empRole(reqDTO.getEmpRole())
                .regUser(registrant)
                .build();

        return empService.empOrgaInsert(orgaDTO, empMappingDTO, empNo);

    }

    @PutMapping(value = "/modify/{empNo}")
    public Long modifyEmp(@Valid @RequestBody EmpUpdateDTO empUpdateDTO, HttpServletRequest request) {
        String ip  = getRemoteIp(request);
        log.info("IPIPPPPPPPPP----------------"+ip);
        empUpdateDTO.setLastLoginIp(ip);
        return empService.update(empUpdateDTO);
    }

    @PutMapping(value = "/modifyOrga")
    public Long modifyOrga(@RequestBody ListDTO listDTO) {

        log.info("-----------------------악!");

        log.info(listDTO);
        Long result = 0L;

//        String registrant = "종현";
////      list.setModUser(registrant);

        for (int i = 0; i < listDTO.getList().size(); i++) {
            result += empService.orgaUpdate(listDTO.getList().get(i));
        }
//
        log.info("리절트: "+result);
        return result;
    }


}
