package com.demo.consumer.feign.client.fallback;

import org.springframework.stereotype.Component;

import com.demo.consumer.feign.client.DemoFeignClient;

@Component
public class DemoClientFallback implements DemoFeignClient{

	@Override
	public String getUser(Integer id) {
		 return "Hystrix fallback ...";
	}
}
