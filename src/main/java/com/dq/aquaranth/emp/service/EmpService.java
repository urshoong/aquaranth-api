package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.menu.objectstorage.dto.request.MultipartFileDTO;

import java.util.List;

public interface EmpService {
    List<EmpDTO> findAll();

    EmpDTO findById(Long empNo);

    Long update(EmpUpdateDTO empUpdateDTO);

    Long orgaUpdate(EmpOrgaUpdateDTO empOrgaUpdateDTO);


    EmpDTO insert(EmpOrgaDTO orgaReqDTO, EmpDTO reqDTO, EmpMappingDTO mapperReqDTO) throws IllegalAccessException;

    // 사원의 조직 정보 추가
    Long empOrgaInsert(EmpOrgaDTO orgaReqDTO, EmpMappingDTO mapperReqDTO, Long empNo);

    List<EmpSelectOrga> findAllOrga(Long empNo);

    //List<EmpLoginDTO> findByLoginUsername(String username);

    Long updateFile(MultipartFileDTO fileDto) throws Exception;


    List<EmpLoginEmpDTO> findLoginUser(String username);

    // 레디스 녀석들 가져오기
    EmpLoggingDTO findLoggingInformation(String username);

    // 먼데?
//    Long updateRecentAccessInfo();

    // 회원 프로필 삭제
    Long deleteProfile(Long empNo);


}
