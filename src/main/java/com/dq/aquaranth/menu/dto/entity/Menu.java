package com.dq.aquaranth.menu.dto.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Long menuNo;
    private Long upperMenuNo;
    private String menuName;
    private boolean mainFlag;
    private String menuCode;
    private boolean deleteFlag;
    private String menuPath;
    private boolean menuRequired;
    private boolean menuAdmin;
    private Long menuOrder;
    private String menuDefault;
    private String uuid;
    private String filename;
    private String regUser;
    private LocalDateTime regDate;
    private String modUser;
    private LocalDateTime modDate;
    private Long depth;
}
