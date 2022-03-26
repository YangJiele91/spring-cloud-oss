package com.oss.base.feign;

import com.oss.base.feign.fallback.UserClientFallback;
import com.oss.common.model.LoginUser;
import com.oss.common.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="user-service", fallback= UserClientFallback.class)
public interface UserFeignClient {
	@GetMapping("/user/getLoginUser")
	Result<LoginUser> getLoginUser();
}