package com.dq.aquaranth.company.mapper;
import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;

import java.util.List;

public interface CompanyMapper {

    /**
     * 회사코드, 회사명, 대표자명, 사용여부 리스트 출력
     */
    List<CompanyListDTO> findAll();

    /**
     * 회사 기본정보 출력
     */
    CompanyDTO findById(Long companyNo);

    /**
     * 회사 기본정보 추가
     */
    Long register(CompanyDTO companyDTO);

    /**
     * 회사 기본정보 수정
     */
    Long modify(CompanyModifyDTO companyModifyDTO);

    /**
     * 회사 정보 삭제
     */
    Long removeById(Long companyNo);
}
