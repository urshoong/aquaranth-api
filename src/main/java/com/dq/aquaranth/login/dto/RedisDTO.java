package com.dq.aquaranth.login.dto;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RedisDTO {
    private CompanyDTO company;
    private DeptDTO2 dept;
    private EmpDTO emp;
    private List<MenuResponseDTO> menuList;
}
