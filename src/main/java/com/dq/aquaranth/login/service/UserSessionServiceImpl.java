package com.dq.aquaranth.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dq.aquaranth.commons.utils.JWTUtil;
import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.company.mapper.CompanyMapper;
import com.dq.aquaranth.dept.dto.DeptDTO;
import com.dq.aquaranth.dept.mapper.DeptMapper;
import com.dq.aquaranth.emp.dto.EmpDTO;
import com.dq.aquaranth.login.domain.CustomUser;
import com.dq.aquaranth.login.dto.RedisDTO;
import com.dq.aquaranth.rolegroup.domain.RoleGroup;
import com.dq.aquaranth.rolegroup.mapper.RoleGroupMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.dq.aquaranth.login.jwt.JwtProperties.SECRET;
import static com.dq.aquaranth.login.jwt.JwtProperties.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserSessionServiceImpl implements UserSessionService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final JWTUtil jwtUtil;

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

    @Override
    public Map<String, String> checkRefresh(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.info("refresh token 을 검증합니다.");
            String refreshToken = authorizationHeader.substring(TOKEN_PREFIX.length());
            Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes()); // 토큰 생성할 때와 같은 알고리즘으로 풀어야함.
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);

            // 토큰이 유효한지 확인되면, 사용자의 이름을 가져올 수 있습니다.
            String username = decodedJWT.getSubject(); // token 과 함께 제공되는 사용자 이름을 줍니다.

            log.info("refresh token 검증이 완료되었습니다.");

            return jwtUtil.generateToken(username);
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
