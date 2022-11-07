package com.dq.aquaranth.tests.service;

import com.dq.aquaranth.tests.mapper.TimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TestService {
    private final TimeMapper timeMapper;

    public String getTime() {
        return timeMapper.getTime();
    }
}
