package com.demo.zuul.configuration;

import com.demo.zuul.filter.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Filters {

    /**
     * 前置鉴权过滤器
     * @return
     */
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
