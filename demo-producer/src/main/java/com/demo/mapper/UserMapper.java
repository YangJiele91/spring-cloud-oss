package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.model.User;

import tk.mybatis.mapper.common.Mapper;

/**
 * DAO层
 */
public interface UserMapper extends Mapper<User>{
	
	List<User> searchUserByName(@Param("userName") String userName);
	
}