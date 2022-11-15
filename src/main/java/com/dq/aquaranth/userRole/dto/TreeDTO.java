package com.dq.aquaranth.userRole.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    private Long deptNo;
    private Long upperDeptNo;
    private String deptName, deptDesc;
    private boolean delFlag, mainFlag;
    private int gno, ord, depth;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate, modDate;
    private Long lastDno;

}
