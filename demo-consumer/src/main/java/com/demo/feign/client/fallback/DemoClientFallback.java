package com.demo.feign.client.fallback;

import org.springframework.stereotype.Component;

import com.demo.feign.client.DemoFeignClient;

@Component
public class DemoClientFallback implements DemoFeignClient{

	@Override
	public String getUser(Integer id) {
		 return "Hystrix fallback ...";
	}
}
