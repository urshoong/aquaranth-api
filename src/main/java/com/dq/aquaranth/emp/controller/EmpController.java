package com.dq.aquaranth.emp.controller;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.MenuCodes;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/emp")
@MenuCode(MenuCodes.ORGA0030)
public class EmpController {
    private final EmpService empService;
    private final UserSessionService userSessionService;

    /**
     * 사원테이블에 있는 모든 정보를 요청합니다.
     * @return 사원리스트
     */
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

    @PostMapping("/register")
    public EmpDTO registerEmp (@Valid @RequestBody  EmpInsertInformationDTO reqDTO, Authentication authentication) throws IllegalAccessException{
        log.info(reqDTO);

        String registrant = authentication.getName();

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
    public Long registerEmpOrga (@RequestBody EmpOrgaInsertDTO reqDTO, Authentication authentication){
        String registrant = authentication.getName();
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
    public Long modifyEmp(@Valid @RequestBody EmpUpdateDTO empUpdateDTO, Authentication authentication) {
        String username = authentication.getName();

        empUpdateDTO.setModUser(username);
        empUpdateDTO.setModDate(LocalDateTime.now());

        return empService.update(empUpdateDTO);
    }

    @PutMapping(value = "/modifyOrga")
    public Long modifyOrga(@RequestBody ListDTO listDTO, Authentication authentication) {

        // 로그인한 사용자의 아이디 가져오기
        String modUser = authentication.getName();

        log.info(listDTO);
        Long result = 0L;

//        String registrant = "종현";
////      list.setModUser(registrant);

        listDTO.getList().forEach(item -> item.setModUser(modUser));

        for (int i = 0; i < listDTO.getList().size(); i++) {
            result += empService.orgaUpdate(listDTO.getList().get(i));
        }
        log.info("result: "+result);
        return result;
    }

    /**
     * 사원 프로필 업데이트
     */
@PutMapping(value = "/updateprofile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public Long updateEmpProfile(MultipartFileDTO fileDto) throws Exception {
    return empService.updateFile(fileDto);
}

@PutMapping(value = "/removeProfile/{empNo}")
public long removeProfile(@PathVariable("empNo") Long empNo){
    return empService.deleteProfile(empNo);
}

    /**
     * 로그인한 회원 정보
     */
    @GetMapping("/loginlist")
    public List<EmpLoginEmpDTO> findLoginUser(Authentication authentication){
        String username = authentication.getName();
        return empService.findLoginUser(username);
    }

    /* 로그인한 회원의 deptno, companyno, empRank 받기 */
    @GetMapping("/loginRedisValue")
    public EmpLoggingDTO findLoginRedisValue(Authentication authentication){
        String username = authentication.getName();
        return empService.findLoggingInformation(username);
    }

    /**
     * 로그인한 회원 정보 보내서 redis에 올리기
     */
    @PostMapping("/registerLoginUser")
    public LoginUser registerLoginUser(@RequestBody LoginUser loginUser, Authentication authentication) {
        log.error(loginUser);
        String username = authentication.getName();
        loginUser.setUsername(username);

        log.info(loginUser);

        //레디스에 올린다.
        userSessionService.loadUserInfoByLoginUser(loginUser);

        return loginUser;
    }
}
