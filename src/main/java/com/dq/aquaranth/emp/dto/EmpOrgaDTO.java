package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 사원을 넣을 때,
 * 사원에게 매핑될 조직 부서와
 * 상위 조직 번호 (부서의 조직 번호)를 저장할 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpOrgaDTO {
    private Long orgaNo, upperOrgaNo;
    private Long deptNo;
    private String orgaType, regUser, modUser;
    private LocalDate regDate, modDate;
}
