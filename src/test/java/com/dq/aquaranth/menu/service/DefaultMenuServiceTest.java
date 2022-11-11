package com.dq.aquaranth.menu.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Log4j2
@SpringBootTest
class DefaultMenuServiceTest {



    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void findAllMenus() {
        menuService.findAllMenus().forEach(log::info);
    }

    @Test
    void redisTest(){
        String key = "redistest";
        //when
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        List<String> stringList = new ArrayList<>();
        stringList.add("Test");
        stringList.add("Test");
        stringList.add("Test");
        stringList.add("Test");
        stringList.add("Test");
        //given
        stringObjectValueOperations.set(key, stringList);
        //then
        String data = (String) stringObjectValueOperations.get(key);
        log.info(data);
    }

    @Test
    void redisTest1() {
        //when
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        //given
        setOperations.add("user98", "h", "e", "l", "l", "o");

        Set<Object> stringSet = setOperations.members("user98");

        //then

        log.info(stringSet);
    }

}
