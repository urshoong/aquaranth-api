package com.dq.aquaranth.emp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpFileDTO {
    private String uuid; // 이름 충돌을 피하기 위한 랜덤 uuid, 실제 저장되는 파일 이름 : uuid_fileName
    private String fileName;
    private Integer empNo;
}
