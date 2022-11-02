package com.dq.aquaranth.commons.config;


import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
@MapperScan(basePackages = {"com.dq.aqaranth.domain.**.mapper"})
public class RootConfig {
}
