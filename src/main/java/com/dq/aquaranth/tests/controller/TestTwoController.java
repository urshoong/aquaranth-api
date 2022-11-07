package com.dq.aquaranth.tests.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test2")
public class TestTwoController {

    @GetMapping
    public ResponseEntity<Map<String, String>> testTwo() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("test", "1234");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(stringMap);
    }
}
