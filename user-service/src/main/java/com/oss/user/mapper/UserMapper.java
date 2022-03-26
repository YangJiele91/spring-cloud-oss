package com.oss.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oss.user.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sys
 * @since 2022-03-25
 */
public interface UserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> searchUserByName(IPage<SysUser> page, @Param("userName") String userName);
}
