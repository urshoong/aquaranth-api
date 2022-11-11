package com.dq.aquaranth.login.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import com.dq.aquaranth.dept.dto.DeptDTO2;
import com.dq.aquaranth.dept.mapper.DeptMapper2;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.menu.mapper.MenuMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<String> menuList = menuMapper.findMenusByLoginUsername(username);

        return new CustomUser(companyDTO, deptDTO2, empDTO, menuList);
    }

    @Override
    public CustomUser findUserInfoInRedis(String username) {
        ValueOperations<String, Object> test = redisTemplate.opsForValue();
        log.info(test.get(username));
        ObjectMapper objectMapper = new ObjectMapper();
        CustomUser customUser;

        try {
            customUser = objectMapper.readValue((String) test.get(username), CustomUser.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.error("@@@@@@333333333333333333333@@@@@@@@@2" + customUser);

        return customUser;
    }
}
