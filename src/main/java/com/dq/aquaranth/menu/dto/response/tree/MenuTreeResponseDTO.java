
package com.dq.aquaranth.menu.dto.response.tree;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeResponseDTO {
    private Long menuNo;
    @Builder.Default
    private Long upperMenuNo = 0L;
    private Long depth;
    private String menuName;
    private String menuCode;
    private String menuPath;
    private String uuid;
    private String filename;
    private String iconUrl;
    private Long subMenuCount;
}
