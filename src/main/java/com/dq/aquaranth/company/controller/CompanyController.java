package com.dq.aquaranth.company.controller;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;
import com.dq.aquaranth.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    //회사코드, 회사명, 대표자명, 사용여부 리스트 출력
    @GetMapping("/list")
    public List<CompanyListDTO> companyList() {
        return companyService.companyList();
    }

    //회사 기본정보 출력
    @GetMapping("/information/{companyNo}")
    public CompanyDTO companyInformation(@PathVariable Long companyNo) {
        return companyService.companyInformation(companyNo);
    }

    //회사 기본정보 출력
    @PostMapping("/add")
    public Integer companyAdd(@RequestBody CompanyDTO companyDTO) {
        return companyService.companyAdd(companyDTO);
    }

    //회사 기본정보 수정
    @PutMapping("/modify/{companyNo}")
    public Integer companyModify(@RequestBody CompanyModifyDTO companyModifyDTO) {
        return companyService.companyModify(companyModifyDTO);
    }

    //회사 정보 삭제
    @DeleteMapping("/delete/{companyNo}")
    public Integer companyDelete(@PathVariable Long companyNo) {
        return companyService.companyDelete(companyNo);
    }
}
