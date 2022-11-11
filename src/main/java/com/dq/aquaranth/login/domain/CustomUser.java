package com.dq.aquaranth.login.domain;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.emp.dto.EmpDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@ToString
public class CustomUser extends User {
    CompanyDTO companyDTO;
    DeptDTO2 deptDTO2;
    EmpDTO empDTO;
    List<String> menuList;

    public CustomUser(CompanyDTO companyDTO, DeptDTO2 deptDTO2, EmpDTO empDTO, List<String> menuList) {
        super(empDTO.getUsername(), empDTO.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.companyDTO = companyDTO;
        this.deptDTO2 = deptDTO2;
        this.empDTO = empDTO;
        this.menuList = menuList;
    }
}
