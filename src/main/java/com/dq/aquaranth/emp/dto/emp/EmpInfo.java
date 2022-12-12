package com.dq.aquaranth.emp.dto.emp;

import com.dq.aquaranth.emp.dto.login.EmpLoggingDTO;
import com.dq.aquaranth.emp.dto.login.EmpLoginEmpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EmpInfo {
    List<EmpLoginEmpDTO> empDTOList;
    EmpLoggingDTO empLoggingDTO;
}
