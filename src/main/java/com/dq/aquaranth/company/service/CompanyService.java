package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyService {

    private final CompanyMapper companyMapper;

    //회사코드, 회사명, 대표자명, 사용여부 리스트 출력
    public List<CompanyListDTO> companyList() {
        return companyMapper.companyList();
    }

    //회사 기본정보 출력
    public CompanyDTO companyInformation(Long companyNo) {
        return companyMapper.companyInformation(companyNo);
    }

    //회사 기본정보 등록
    public Integer companyAdd(CompanyDTO companyDTO) {
        return companyMapper.companyAdd(companyDTO);
    }

    //회사 기본정보 수정
    public Integer companyModify(CompanyModifyDTO companyModifyDTO) {
        return companyMapper.companyModify(companyModifyDTO);
    }

    //회사 정보 삭제
    public Integer companyDelete(Long companyNo) {
        return companyMapper.companyDelete(companyNo);
    }
}
