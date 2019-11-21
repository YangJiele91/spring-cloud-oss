package com.demo.consumer.feign.client;

import com.demo.consumer.feign.client.fallback.DemoClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="demo-service", fallback= DemoClientFallback.class)
public interface DemoFeignClient {

	@GetMapping("/feign/getUser/{id}")
	String getUser(@PathVariable(value="id") Integer id);

}
