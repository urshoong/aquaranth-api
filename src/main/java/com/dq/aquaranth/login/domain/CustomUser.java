package com.dq.aquaranth.login.domain;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@ToString
public class CustomUser extends User {
    EmpDTO empDTO;

    public CustomUser(EmpDTO empDTO) {
        super(empDTO.getUsername(), empDTO.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.empDTO = empDTO;
    }

}
