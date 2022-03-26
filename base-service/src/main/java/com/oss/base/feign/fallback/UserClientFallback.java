package com.oss.base.feign.fallback;

import com.oss.base.feign.UserFeignClient;
import com.oss.common.model.LoginUser;
import com.oss.common.model.Result;
import com.oss.common.util.ResultUtil;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserFeignClient {
	@Override
	public Result<LoginUser> getLoginUser() {
		 return ResultUtil.success(null);
	}
}
