package com.dq.aquaranth.login.service;

import com.dq.aquaranth.commons.utils.SendResponseUtils;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.emp.mapper.EmpMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final EmpMapper empMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("사용자가 로그인을 시도합니다. username => {}", username);

        // find user
        EmpDTO user = empMapper.findByUsername(username);

        if (user == null) {
            log.error("user 정보가 db에 존재하지 않습니다.");
            throw new UsernameNotFoundException("User not found in the database");
        }

        log.info("user 정보를 찾았습니다 username => {}", username);

//        Fixme : user 는 여러개의 권한을 가질 수 있어야 한다.
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public EmpDTO create(EmpDTO emp, HttpServletResponse response) throws IllegalAccessException, IOException {
        log.info("계정을 생성합니다. username => {}", emp.getUsername());

        // 이미 가입된 유저라면
        if (Objects.nonNull(empMapper.findByUsername(emp.getUsername()))) {
            log.error("이미 가입된 유저입니다. username -> " + emp.getUsername());

            // error 전송
            SendResponseUtils.sendBody(CONFLICT.value(), "이미 가입된 유저입니다.", response);

            // 회원가입 시 아이디 중복은 흔한 일인데 이걸 굳이 exception 을 던져서 프로그램을 뻗다이 시켜야하는가?
//            return new User();
            throw new KeyAlreadyExistsException("이미 가입된 유저입니다. username -> " + emp.getUsername());
        }

        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        Long insertEmp = empMapper.empInsert(emp);
        return empMapper.empFindById(insertEmp);
    }

}
