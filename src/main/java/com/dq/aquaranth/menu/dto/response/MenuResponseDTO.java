package com.dq.aquaranth.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    private Long menuOrder;
    private String menuCode;
    private String defaultMenuCode;
    private boolean menuUse;
    private boolean menuRequired;
    private boolean menuAdmin;
    private String regUser;
    private String modUser;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
