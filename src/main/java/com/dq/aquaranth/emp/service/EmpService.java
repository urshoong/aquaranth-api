package com.dq.aquaranth.emp.service;

import com.dq.aquaranth.emp.dto.*;
import com.dq.aquaranth.objectstorage.dto.request.MultipartFileDTO;

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

}
