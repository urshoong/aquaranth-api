package com.dq.aquaranth.login.service;

import com.dq.aquaranth.company.dto.CompanyDTO;
import com.dq.aquaranth.login.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserSessionServiceImpl implements UserSessionService {
    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 로그인한 사용자의 회사정보
     *
     * @param username
     * @return
     */
    @Override
    public CompanyDTO findCompanyByUsername(String username) {
        ValueOperations<String, Object> test = redisTemplate.opsForValue();
        Object obj = test.get(username);
        log.info(obj);

        return null;
    }

}
