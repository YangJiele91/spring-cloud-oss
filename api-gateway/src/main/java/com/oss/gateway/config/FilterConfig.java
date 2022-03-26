package com.oss.gateway.config;

import com.oss.gateway.filter.AccessFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FilterConfig {
    @Bean
    @Order(-1)
    public GlobalFilter accessFilter() {
        return new AccessFilter();
    }
}
