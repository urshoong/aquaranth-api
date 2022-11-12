package com.dq.aquaranth.company.mapper;

import com.dq.aquaranth.company.dto.*;

import java.util.List;

public interface CompanyMapper {

    /**
     * 회사코드, 회사명, 대표자명, 사용여부 리스트 출력
     */
    List<CompanyListDTO> findAll();

    /**
     * 회사 기본 정보 출력
     */
    CompanyDTO findById(Long companyNo);

    /**
     * 조직에 회사 정보 추가
     */
    Long insertOrga(CompanyOrgaDTO companyOrgaDTO);

    /**
     * 회사 기본 정보 추가
     */
    Long insert(CompanyDTO companyDTO);

    /**
     * 회사 기본 정보 수정
     */
    Long update(CompanyModifyDTO companyModifyDTO);

    /**
     * 조직에서 회사 정보 삭제
     */
    Long deleteOrga(Long orgaNo);

    /**
     * 회사 정보 삭제
     */
    Long deleteById(CompanyDeleteDTO companyDeleteDTO);

    /**
     * 회사 코드, 회사명, 사용여부로 검색
     */
    List<CompanyListDTO> search(Boolean companyUse, String companySearch);

<<<<<<< HEAD
    /**
     * 사용자 아이디에 맞는 회사 정보 출력
     */
    CompanyDTO findByUsername(String username);

    /**
     * 조직도 트리 정보 출력
     */
=======
    Long insertOrga(CompanyOrgaDTO companyOrgaDTO);

>>>>>>> 84bb86c71e7a88886d241c2bd5851f4b223f3e59
    List<OrgaTreeDTO> orgaTree();
}
