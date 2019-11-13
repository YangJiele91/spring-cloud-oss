package com.demo.service;

import java.util.List;

import com.demo.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	User getUserById(Integer id);

	List<User> getUserByName(String userName);

	List<User> listAllUser();

	PageInfo<User> pageAllUser(Integer pageNum, Integer pageSize);

	List<User> searchUserByName(String userName);

	void saveUser(User user);

	void editUser(User user);

	void deleteUserById(Integer id);
}
