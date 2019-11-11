package org.spring.cloud.consumer.feign.controller;

import org.spring.cloud.consumer.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/users/{name}")
	public String getUser(@PathVariable("name") String name) {
		return userFeignClient.getUser(name);
	}
	
	@GetMapping("/api/users/{name}")
	public String getApiUser(@PathVariable("name") String name) {
		return userFeignClient.getUserApi(name);
	}
}
