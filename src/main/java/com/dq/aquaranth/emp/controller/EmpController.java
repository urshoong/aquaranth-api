package com.dq.aquaranth.emp.controller;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.emp.service.EmpService;
import com.dq.aquaranth.login.domain.LoginUser;
import com.dq.aquaranth.login.service.UserSessionService;
import com.dq.aquaranth.menu.annotation.MenuCode;
import com.dq.aquaranth.menu.constant.MenuCodes;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import com.dq.aquaranth.objectstorage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/emp")
@MenuCode(MenuCodes.ORGA0030)
public class EmpController {
    private final EmpService empService;
    private final UserSessionService userSessionService;

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
        String ip = null;
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
            //TODO IP는 Test용으로 수정에 넣어놓음. 나중에 로그인 성공 시, 받아오기로 변경
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }


//        String ip  = getRemoteIp(request);
        log.info("IP----------------"+ip);
        empUpdateDTO.setLastLoginIp(ip);
        return empService.update(empUpdateDTO);
    }

    @PutMapping(value = "/modifyOrga")
    public Long modifyOrga(@RequestBody ListDTO listDTO) {

        log.info("-----------------------modifyOrga 확인");

        log.info(listDTO);
        Long result = 0L;

//        String registrant = "종현";
////      list.setModUser(registrant);

        for (int i = 0; i < listDTO.getList().size(); i++) {
            result += empService.orgaUpdate(listDTO.getList().get(i));
        }
        log.info("result: "+result);
        return result;
    }

    /**
     * 사원 프로필 업데이트
     */
    private final ObjectStorageService objectStorageService;
//    public Long modifyProfile(@RequestBody EmpFileDTO empFileDTO){
//        return null;
//    }

@PutMapping(value = "/updateprofile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public Long updateEmpProfile(MultipartFileDTO fileDto) throws Exception {
    return empService.updateFile(fileDto);
}


    /**
     * 로그인한 회원 정보
     */
    @GetMapping("/loginlist")
    public List<EmpLoginEmpDTO> findLoginUser(Authentication authentication){
        String username = authentication.getName();
        return empService.findLoginUser(username);
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

        userSessionService.loadUserInfoByLoginUser(loginUser);
        return null;
    }



//    @PostMapping("/upload")
//    public String upload(@RequestBody MultipartFile[] uploadfile, Model model) throws IllegalAccessException, IOException{
//        List<EmpFileDTO> list = new ArrayList<>();
//
//        for (MultipartFile file : uploadfile){
//            if(!file.isEmpty()){
//                EmpFileDTO fileDTO = new EmpFileDTO(UUID.randomUUID().toString(),
//                                                    file.getOriginalFilename(),
//                                                    file.getContentType());
//                list.add(fileDTO);
//
//                File newFileName = new File(fileDTO.getUuid()+"_"+fileDTO.getFileName());
//                file.transferTo(newFileName);
//            }
//        }
//        model.addAttribute("files", list);
//        return "result";
//    }
}
