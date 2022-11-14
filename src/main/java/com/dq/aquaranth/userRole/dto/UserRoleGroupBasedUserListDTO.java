package com.dq.aquaranth.userRole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleGroupBasedUserListDTO {
    Long orgaNo;
    String orgaInfo, empRank, empName, username;
}
