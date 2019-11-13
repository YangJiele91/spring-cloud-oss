package com.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.feign.client.DemoFeignClient;
import com.demo.model.User;
import com.demo.service.impl.UserServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

/**
 * 控制层
 */
@RestController
@RequestMapping("/demo/")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private DemoFeignClient demoFeignClient;

	@GetMapping("/getUser/{id}")
	public String getUser(@PathVariable Integer id) {
		return userService.getUserById(id).toString();
	}
	
	@GetMapping("/getUserFromDemoService/{id}")
	public String getUserFromDemoService(@PathVariable Integer id) {
		return demoFeignClient.getUser(id);
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

	@PostMapping("/saveUser")
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