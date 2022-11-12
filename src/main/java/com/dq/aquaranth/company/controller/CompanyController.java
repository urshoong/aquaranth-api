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
     * 회사코드, 회사명, 대표자명, 사용여부 리스트 출력
     */
    @GetMapping("/list")
    public List<CompanyListDTO> getCompanyList() {
        return companyService.findAll();
    }

    /**
     * 회사 기본정보 출력
     */
    @GetMapping("/information/{companyNo}")
    public CompanyDTO getCompanyInformation(@PathVariable Long companyNo) {
        return companyService.findById(companyNo);
    }

    /**
     * 회사 기본정보 추가
     */
    @PostMapping("/register")
    public Long registerCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.insert(companyDTO);
    }

    /**
     * 회사 기본정보 수정
     */
    @PutMapping("/modify/{companyNo}")
    public Long modifyCompany(@RequestBody CompanyModifyDTO companyModifyDTO, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        companyModifyDTO.setModUser(customUser.getEmpDTO().getEmpName());
        return companyService.update(companyModifyDTO);
    }

    /**
     * 회사 정보 삭제
     */
    @DeleteMapping("/remove/{companyNo}")
    public Long removeCompany(@PathVariable Long companyNo) {
        return companyService.deleteById(companyNo);
    }

    /**
     * 회사코드, 회사명, 사용여부로 검색
     */
    @GetMapping("/search")
    public List<CompanyListDTO> searchCompany(@RequestParam Boolean companyUse, String companySearch) {
        return companyService.search(companyUse, companySearch);
    }

    @GetMapping("/tree")
    public List<OrgaTreeDTO> orgaTree() {
        return companyService.orgaTree();
    }
}
