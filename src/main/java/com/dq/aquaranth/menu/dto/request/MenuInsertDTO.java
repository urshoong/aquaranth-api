package com.dq.aquaranth.menu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 메뉴 추가용 DTO입니다.
 *
 * @author 김민준
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuInsertDTO {
    private Long menuNo;
    private Long upperMenuNo;
    private String menuName;
    @Builder.Default
    private boolean mainFlag = true;
    private String menuCode;
    private String menuPath;
    @Builder.Default
    private Long menuOrder = 99L;
    private String menuDefault;
    private String uuid;
    private String filename;
    private Long depth;
    private String regUser;
}
