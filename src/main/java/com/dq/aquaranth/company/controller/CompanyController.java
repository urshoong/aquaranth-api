package com.dq.aquaranth.company.controller;

import com.dq.aquaranth.company.dto.*;
import com.dq.aquaranth.company.service.CompanyService;
import com.dq.aquaranth.login.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    /**
     * 회사 일부정보(회사번호, 회사명, 대표자명, 사용여부만)가 포함된 전체 리스트 출력
     */
    @GetMapping("/list")
    public List<CompanyListDTO> getCompanyList(Authentication authentication) {
        log.info("authentication : {}", authentication.getPrincipal());
        return companyService.findAllCompany();
    }

    /**
     * 해당 회사에 대한 기본정보 출력
     */
    @GetMapping("/information/{companyNo}")
    public CompanyInformationDTO getCompanyInformation(@PathVariable Long companyNo) {
        return companyService.findByCompanyNo(companyNo);
    }

    /**
     * 회사 기본정보 추가
     */
    @PostMapping("/register")
    public Long registerCompany(@RequestBody CompanyInformationDTO companyInformationDTO, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        String username = customUser.getUsername();
        return companyService.insert(companyInformationDTO, username);
    }

    /**
     * 회사 기본정보 수정
     */
    @PutMapping("/modify/{caompanyNo}")
    public Long modifyCompany(@RequestBody CompanyUpdateDTO companyUpdateDTO, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        String username = customUser.getUsername();
        return companyService.update(companyUpdateDTO, username);
    }

    /**
     * 회사 기본정보 삭제(즉, 사용 여부가 '사용'인 회사를 '미사용'으로 변경)
     */
    @DeleteMapping("/remove/{companyNo}")
    public Long removeCompany(@PathVariable Long companyNo) {
        return companyService.deleteByCompanyNo(companyNo);
    }

    /**
     * 회사코드, 회사명, 사용여부로 회사 기본정보 검색
     */
    @GetMapping("/search")
    public List<CompanyListDTO> searchCompany(@RequestParam Boolean companyUse, String companySearch) {
        return companyService.search(companyUse, companySearch);
    }

    // TODO: 2022-11-13 조직도 트리와 사원 정보 출력은 다른 디렉토리로 빼기

}
