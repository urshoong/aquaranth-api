package com.dq.aquaranth.login.dto;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.menu.dto.response.AllMenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisDTO {
    private CompanyDTO company;
    private DeptDTO2 dept;
    private CustomUser userinfo;
}
