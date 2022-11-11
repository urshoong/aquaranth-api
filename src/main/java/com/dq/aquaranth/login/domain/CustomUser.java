package com.dq.aquaranth.login.domain;

import com.dq.aquaranth.emp.dto.EmpDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@ToString
public class CustomUser extends User {
    EmpDTO empDTO;
    List<String> menuList;

    public CustomUser(EmpDTO empDTO, List<String> menuList) {
        super(empDTO.getUsername(), empDTO.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.empDTO = empDTO;
        this.menuList = menuList;
    }
}
