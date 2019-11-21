package com.demo.producer.feign.api;

import com.demo.producer.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/")
public class DeomFeignController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/getUser/{id}")
	public String getUser(@PathVariable Integer id) {
		return userService.getUserById(id).toString();
	}
}
