package com.dq.aquaranth.menu.dto.response;

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
    private Integer menuSort;
    private String menuCode;
    private String defaultMenuCode;
    private String menuName;
    private String iconUrl;
    private boolean menuUse;
}
