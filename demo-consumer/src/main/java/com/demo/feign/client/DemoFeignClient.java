package com.demo.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.feign.client.fallback.DemoClientFallback;

@FeignClient(name="demo-service", fallback=DemoClientFallback.class)
public interface DemoFeignClient {

	@GetMapping("/feign/getUser/{id}")
	String getUser(@PathVariable(value="id") Integer id);

}
