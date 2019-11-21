package com.demo.consumer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.consumer.model.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{

	List<User> searchUserByName(@Param("userName") String userName);

}
