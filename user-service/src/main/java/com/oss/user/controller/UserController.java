package com.oss.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oss.common.annotate.CurrentUser;
import com.oss.common.model.LoginUser;
import com.oss.common.model.Result;
import com.oss.common.util.EncryptionUtil;
import com.oss.common.util.RedisUtil;
import com.oss.common.util.ResultUtil;
import com.oss.user.entity.SysUser;
import com.oss.user.enums.ExceptionCode;
import com.oss.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author sys
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户数据接口")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @Value("${loginExpireTime}")
    private Long loginExpireTime;

    @ApiOperation("登录")
    @PostMapping({"/login"})
    public Result<String> login(String username, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, username);
        SysUser sysUser = this.userService.getOne(queryWrapper);
        if (sysUser == null) {
            ExceptionCode.USER_NOT_EXIST.toThrow();
        }
        if (!StringUtils.equals(sysUser.getPassWord(), EncryptionUtil.getSha1(password))) {
            ExceptionCode.PASSWORD_INVALID.toThrow();
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(sysUser.getUserName());
        String token = EncryptionUtil.get32UUID();
        this.redisUtil.set("login_user" + token, loginUser, this.loginExpireTime);
        return ResultUtil.success(token);
    }

    @ApiOperation("登出")
    @PostMapping({"/logout"})
    public Result<Void> logout(String token) {
        this.redisUtil.del("login_user" + token);
        return ResultUtil.success();
    }

    @ApiOperation("获取当前登录用户")
    @GetMapping("/getLoginUser")
    public Result<LoginUser> getLoginUser(@CurrentUser LoginUser loginUser) {
        return ResultUtil.success(loginUser);
    }

    @ApiOperation("全部用户列表")
    @GetMapping("/list")
    @Cacheable(cacheNames="userList")
    public Result<List<SysUser>> list() {
        return ResultUtil.success(userService.list());
    }

    @ApiOperation("分页查询用户")
    @GetMapping("/page")
    public Result<IPage<SysUser>> page(@RequestParam(defaultValue = "1") Integer pageIndex,
                                       @RequestParam(defaultValue = "20")Integer pageSize) {
        return ResultUtil.success(userService.page(new Page<>(pageIndex, pageSize), new QueryWrapper<>()));
    }

    @ApiOperation("用户搜索")
    @GetMapping("/searchUserByName")
    public Result<IPage<SysUser>> searchUserByName(String userName,
                                                   @RequestParam(defaultValue = "1") Integer pageIndex,
                                                   @RequestParam(defaultValue = "20")Integer pageSize) {
        return ResultUtil.success(userService.searchUserByName(new Page<>(pageIndex, pageSize), userName));
    }
}