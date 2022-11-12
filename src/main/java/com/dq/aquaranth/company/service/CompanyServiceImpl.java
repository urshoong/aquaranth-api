package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.*;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        log.info("회사 기본 정보 출력");
        return companyMapper.findById(companyNo);
    }

    @Override
    @Transactional
    public Long insert(CompanyDTO companyDTO) {
        log.info("orga_no와 upper_orga_no 추가");
        CompanyOrgaDTO companyOrgaDTO = CompanyOrgaDTO
                .builder()
                .orgaType("company")
                .regUser("admin")
                .build();
        companyMapper.insertOrga(companyOrgaDTO);
        log.info(companyOrgaDTO.getOrgaNo());
        log.info("회사 기본 정보 추가");
        companyDTO.setOrgaNo(companyOrgaDTO.getOrgaNo());
        return companyMapper.insert(companyDTO);
    }

    @Override
    public Long update(CompanyModifyDTO companyModifyDTO) {
        log.info("회사 기본 정보 수정");
        return companyMapper.update(companyModifyDTO);
    }

    @Override
    @Transactional
    public Long deleteById(CompanyDeleteDTO companyDeleteDTO) {
        log.info("회사 정보 삭제");
        companyMapper.deleteById(companyDeleteDTO);
        log.info("조직에서 회사 정보 삭제");
        return companyMapper.deleteOrga(companyDeleteDTO.getOrgaNo());
    }

    @Override
    public List<CompanyListDTO> search(Boolean companyUse, String companySearch) {
        log.info("회사코드, 회사명, 사용여부로 검색");
        return companyMapper.search(companyUse, companySearch);
    }

    @Override
    public List<OrgaTreeDTO> orgaTree() {
<<<<<<< HEAD
        log.info("조직토 트리 정보 출력");
=======
        log.info("조직토 트리");
>>>>>>> 84bb86c71e7a88886d241c2bd5851f4b223f3e59
        return companyMapper.orgaTree();
    }
}
