package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;
import com.dq.aquaranth.company.dto.CompanySearchDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyServiceImpl implements CompanyService{

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyListDTO> findAll() {
        log.info("회사코드, 회사명, 대표자명, 사용여부 리스트 출력");
        return companyMapper.findAll();
    }

    @Override
    public CompanyDTO findById(Long companyNo) {
        log.info("회사 기본정보 출력");
        return companyMapper.findById(companyNo);
    }

    @Override
    public Long insert(CompanyDTO companyDTO) {
        log.info("회사 기본정보 추가");
        return companyMapper.insert(companyDTO);
    }

    @Override
    public Long update(CompanyModifyDTO companyModifyDTO) {
        log.info("회사 기본정보 수정");
        return companyMapper.update(companyModifyDTO);
    }

    @Override
    public Long deleteById(Long companyNo) {
        log.info("회사 정보 삭제");
        return companyMapper.deleteById(companyNo);
    }

    @Override
    public List<CompanyListDTO> search(Boolean companyUse, String companySearch) {
        log.info("회사코드, 회사명, 사용여부로 검색");
        return companyMapper.search(companyUse, companySearch);
    }
}
