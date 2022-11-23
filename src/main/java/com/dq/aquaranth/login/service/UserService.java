package com.dq.aquaranth.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dq.aquaranth.commons.utils.JWTUtil;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import com.dq.aquaranth.login.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.dq.aquaranth.login.jwt.JwtProperties.*;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserService implements UserDetailsService {
    private final EmpMapper empMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("사용자가 로그인을 시도합니다. username => {}", username);
        EmpDTO empDTO = empMapper.findByUsername(username);

        if (empDTO == null) {
            log.error("사원 정보가 db에 존재하지 않습니다.");
            throw new UsernameNotFoundException("User not found in the database");
        }

        return new CustomUser(empDTO);
    }

}
