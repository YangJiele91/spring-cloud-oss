package com.oss.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oss.user.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sys
 * @since 2022-03-25
 */
public interface IUserService extends IService<SysUser> {
    IPage<SysUser> searchUserByName(IPage<SysUser> page, String userName);
}
