package com.oss.user;

import com.oss.common.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@Import(RedisUtil.class)
@EnableCaching
@EnableHystrix
public class UserApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		SpringApplication.run(UserApplication.class, args);
	}
}