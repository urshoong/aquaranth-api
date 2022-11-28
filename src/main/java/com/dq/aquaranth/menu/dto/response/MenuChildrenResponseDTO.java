package com.dq.aquaranth.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuChildrenResponseDTO {
    private Long menuNo;
    private Long upperMenuNo;
    private String menuName;
    private boolean mainFlag;
    private String menuCode;
    private String menuPath;
    private Long menuOrder;
    private String uuid;
    private String filename;
    private Long depth;
    private String iconUrl;
    private List<MenuChildrenResponseDTO> children;
}
