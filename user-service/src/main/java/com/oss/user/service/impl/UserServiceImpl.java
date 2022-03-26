package com.oss.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oss.user.entity.SysUser;
import com.oss.user.mapper.UserMapper;
import com.oss.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sys
 * @since 2022-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements IUserService {
    @Override
    public IPage<SysUser> searchUserByName(IPage<SysUser> page, String userName) {
        return this.getBaseMapper().searchUserByName(page, userName);
    }
}
