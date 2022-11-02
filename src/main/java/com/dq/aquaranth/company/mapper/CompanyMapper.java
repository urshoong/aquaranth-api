package com.dq.aquaranth.company.mapper;
import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.dto.CompanyListDTO;
import com.dq.aquaranth.company.dto.CompanyModifyDTO;

import java.util.List;

public interface CompanyMapper {

    //회사코드, 회사명, 대표자명, 사용여부 리스트 출력
    List<CompanyListDTO> companyList();

    //회사 기본정보 출력
    CompanyDTO companyInformation(Long companyNo);

    //회사 기본정보 등록
    Integer companyAdd(CompanyDTO companyDTO);

    //회사 기본정보 수정
    Integer companyModify(CompanyModifyDTO companyModifyDTO);

    //회사 정보 삭제
    Integer companyDelete(Long companyNo);
}
