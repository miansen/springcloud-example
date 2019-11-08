package org.spring.cloud.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${server.port}")
	private String post;
	
	@GetMapping("/users/{name}")
	public String users(@PathVariable("name") String name) {
		return String.format("hello %s，from server %s，post: %s", name, applicationName, post);
	}
}
