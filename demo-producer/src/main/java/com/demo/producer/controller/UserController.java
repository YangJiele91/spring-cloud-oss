package com.demo.producer.controller;

import java.util.List;
import java.util.UUID;

import com.demo.producer.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.producer.model.User;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

/**
 * 控制层
 */
@RestController // 标记一个Controller, 并将响应结果自动转为JSON
@RequestMapping("/demo/") // 映射页面访问URL
public class UserController {

	@Autowired // 完成Spring的依赖注入
	private UserServiceImpl userService;

	@GetMapping("/getUser/{id}") // 映射页面访问URL GET
	public String getUser(@PathVariable int id) {
		return userService.getUserById(id).toString();
	}

	@GetMapping("/getUserByName")
	public List<User> getUserByName(String userName) {
		return userService.getUserByName(userName);
	}

	@GetMapping("/listAllUser")
	public List<User> listAllUser() {
		return userService.listAllUser();
	}

	@GetMapping("/pageAllUser")
	public PageInfo<User> pageAllUser(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		return userService.pageAllUser(pageNum, pageSize);
	}

	@GetMapping("/searchUserByName")
	public List<User> searchUserByName(String userName) {
		return userService.searchUserByName(userName);
	}

	@PostMapping("/saveUser") // 映射页面访问URL POST
	public void saveUser(User user) {
		if (StringUtil.isEmpty(user.getId())) {
			user.setId(UUID.randomUUID().toString().replaceAll("-",""));
		}
		userService.saveUser(user);
	}

	@PostMapping("/editUser")
	public void editUser(User user) {
		userService.editUser(user);
	}

	@PostMapping("/deleteUserById")
	public void deleteUserById(Integer id) {
		userService.deleteUserById(id);
	}
}
