package com.dq.aquaranth.menu.dto.request.tree;

import lombok.Builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeQueryDTO {

    private Long upperMenuNo;

    private String menuNo;

    @Builder.Default
    private Long depth = 1L;

    @Builder.Default
    private boolean gnb = false;

    private Long children;
}
