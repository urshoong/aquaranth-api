package com.dq.aqaranth.domain.tests.service;

import com.dq.aqaranth.domain.tests.dto.TestDTO;
import com.dq.aqaranth.domain.tests.mapper.TimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
