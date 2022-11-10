package com.dq.aquaranth.login.domain;

import com.dq.aquaranth.emp.dto.EmpDTO;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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
