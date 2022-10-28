package com.dq.aqaranth.domain.tests.controller;

import com.dq.aqaranth.domain.tests.dto.TestDTO;
import com.dq.aqaranth.domain.tests.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public TestDTO test() {
        return TestDTO.builder()
                .id(1L)
                .title("Test")
                .content("Title")
                .build();
    }

    @GetMapping("/time")
    public String getTime() {
        return testService.getTime();
    }
}
