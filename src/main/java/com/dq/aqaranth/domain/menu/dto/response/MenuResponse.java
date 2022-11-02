package com.dq.aqaranth.domain.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private Long menuNo;
    private Long upperMenuNo;
    private String menuName;
    private String menuIcon;
    private boolean menuUse;
}
