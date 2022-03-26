package com.oss.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oss.common.annotate.CurrentUser;
import com.oss.common.model.LoginUser;
import com.oss.common.model.Result;
import com.oss.common.util.ResultUtil;
import com.oss.user.entity.SysUser;
import com.oss.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取当前登录用户")
    @GetMapping("/getLoginUser")
    public Result<LoginUser> getLoginUser(@CurrentUser LoginUser loginUser) {
        return ResultUtil.success(loginUser);
    }
}