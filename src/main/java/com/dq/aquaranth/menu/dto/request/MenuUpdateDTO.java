package com.dq.aquaranth.menu.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateDTO {

    private Long upperMenuNo;

    private Long menuNo;

    private String menuName;

    private String menuCode;

    @Builder.Default
    private boolean mainFlag = true;

    @Builder.Default
    private Long menuOrder = 99L;
}
