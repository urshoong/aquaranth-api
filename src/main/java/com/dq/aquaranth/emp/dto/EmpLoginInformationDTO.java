package com.dq.aquaranth.emp.dto;

import java.time.LocalDateTime;

/**
 * 사원이 로그인 했을 때,
 * 최근 로그인 시간과 최근 로그인 IP를 저장하기 위한 DTO
 */
public class EmpLoginInformationDTO {
    private Long empNo;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
}
