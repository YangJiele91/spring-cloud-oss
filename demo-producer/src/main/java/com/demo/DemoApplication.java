package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import tk.mybatis.spring.annotation.MapperScan;

// @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
@SpringBootApplication
// @EnableDiscoveryClient 使Spring Boot应用具备服务发现的能力（Spring Cloud）
@EnableDiscoveryClient
// @EnableDiscoveryClient 使Spring Boot应用具备Feign声明式调用及负载均衡的能力 （Spring Cloud）
@EnableFeignClients
// @MapperScan 整合MyBatis框架用到的配置
@MapperScan("com.demo.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
