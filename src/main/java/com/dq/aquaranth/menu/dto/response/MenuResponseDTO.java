package com.dq.aquaranth.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDTO {
    private Long menuNo;
    private Long upperMenuNo;
    private String menuName;
    private String menuIcon;
    private boolean menuUse;
    private String menuCode;
    private boolean menuRequired;
    private boolean menuAdmin;
    private String regUser;
    private LocalDateTime regDate;
    private String modUser;
    private LocalDateTime modDate;
    private Long menuOrder;
    private String menuDefault;
    private Long depth;
    private String noPath;
    private String url;
}
