package com.dq.aquaranth.login.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.dept.mapper.DeptMapper2;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;
<<<<<<< HEAD
=======
import com.dq.aquaranth.login.dto.RedisDTO;
>>>>>>> b9e52d2d9449404d74e094de8f94a13dfb7e9d97
import com.dq.aquaranth.menu.dto.response.MenuResponseDTO;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserSessionServiceImpl implements UserSessionService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final CompanyMapper companyMapper;
    private final DeptMapper2 deptMapper2;
    private final MenuMapper menuMapper;

    @Override
    public CustomUser findUserInfoInDatabase(EmpDTO empDTO) {
        String username = empDTO.getUsername();

        CompanyDTO companyDTO = companyMapper.findByUsername(username);
        DeptDTO2 deptDTO2 = deptMapper2.findByUsername(username);
        List<MenuResponseDTO> menuList = menuMapper.findMenusByLoginUsername(username);
        return new CustomUser(companyDTO, deptDTO2, empDTO, menuList);
    }

    @Override
    public RedisDTO findUserInfoInRedis(String username) {
        String value = (String) redisTemplate.opsForValue().get(username);
        if (Objects.isNull(value)) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        RedisDTO userInfo;

        try {
            userInfo = mapper.readValue(value, RedisDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return userInfo;
    }
}