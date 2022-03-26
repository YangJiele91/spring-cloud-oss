package com.oss.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oss.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author sys
 * @since 2022-03-25
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "用户", description = "")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String passWord;

    @ApiModelProperty("别名")
    private String realName;
}
