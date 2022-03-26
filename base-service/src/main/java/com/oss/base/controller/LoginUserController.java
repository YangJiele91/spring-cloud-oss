package com.oss.base.controller;

import com.oss.base.feign.UserFeignClient;
import com.oss.common.model.LoginUser;
import com.oss.common.model.Result;
import com.oss.common.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(tags = "登录数据接口")
public class LoginUserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @ApiOperation("获取当前登录用户")
    @GetMapping("/getLoginUser")
    public Result<LoginUser> getLoginUser() {
        return ResultUtil.success(userFeignClient.getLoginUser().getData());
    }
}
