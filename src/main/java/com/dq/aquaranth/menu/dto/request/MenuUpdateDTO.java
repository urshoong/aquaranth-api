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
    private Long menuNo;

    @NotBlank(message = "메뉴 이름은 빈 값이 될 수 없습니다.")
    private String menuName;

    @Builder.Default
    @NotBlank(message = "사용 여부는 반드시 체크되어야 합니다.")
    private boolean menuUse = false;

    @Builder.Default
    private Long menuOrder = 99L;
}
