package com.dq.aquaranth.menu.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuIconUpdateDTO {
    private String menuCode;
    private String uuid;
    private String filename;

}
