package com.dq.aquaranth.company.service;

import com.dq.aquaranth.company.dto.CompanyInformationDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyUpdateDTO;
import com.dq.aquaranth.company.dto.CompanyOrgaDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyListDTO> findAllCompany() {
        log.info("회사 일부정보(회사번호, 회사명, 대표자명, 사용여부만)가 포함된 전체 리스트 출력");
        return companyMapper.findAllCompany();
    }

    @Override
    public CompanyInformationDTO findByCompanyNo(Long companyNo) {
        log.info("해당 회사에 대한 기본정보 출력");
        return companyMapper.findByCompanyNo(companyNo);
    }

    @Override
    @Transactional
    public Long insert(CompanyInformationDTO companyInformationDTO, String username) {
        log.info("상위 조직번호가 없고 'company' 타입인 조직을 먼저 추가");
        log.info(username);
        CompanyOrgaDTO companyOrgaDTO = CompanyOrgaDTO
                .builder()
                .orgaType("company")
                .username(username)
                .build();
        companyMapper.insertOrga(companyOrgaDTO);
        log.info(companyOrgaDTO.getOrgaNo());

        log.info("회사 기본정보 추가");
        companyInformationDTO.setOrgaNo(companyOrgaDTO.getOrgaNo());
        companyInformationDTO.setUsername(username);

        return companyMapper.insert(companyInformationDTO);
    }

    @Override
    public Long update(CompanyUpdateDTO companyUpdateDTO, String username) {
        log.info("회사 기본정보 수정");
        companyUpdateDTO.setUsername(username);
        return companyMapper.update(companyUpdateDTO);
    }

    @Override
    public Long deleteByCompanyNo(Long companyNo) {
        log.info("회사 기본정보 삭제(즉, 사용 여부가 '사용'인 회사를 '미사용'으로 변경)");
        return companyMapper.deleteByCompanyNo(companyNo);
    }

    @Override
    public List<CompanyListDTO> search(Boolean companyUse, String companySearch) {
        log.info("회사코드, 회사명, 사용여부로 회사 기본정보 검색");
        return companyMapper.search(companyUse, companySearch);
    }

    @Override
    public List<CompanyListDTO> sort(String sortValue) {
        log.info("회사코드, 회사명, 대표자명, 사용여부로 정렬 후 회사 일부정보 리스트 출력");
        return companyMapper.sort(sortValue);
    }
}
