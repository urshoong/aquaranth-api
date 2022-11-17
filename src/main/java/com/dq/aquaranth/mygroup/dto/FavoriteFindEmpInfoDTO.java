package com.dq.aquaranth.mygroup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFindEmpInfoDTO {

    /**
     * 즐겨찾기에 등록된 사원 중 해당 사원 정보 출력 시 필요한 DTO
     * 사원번호, 마이그룹번호
     */
    
    private Long empNo, mygroupNo;
}
