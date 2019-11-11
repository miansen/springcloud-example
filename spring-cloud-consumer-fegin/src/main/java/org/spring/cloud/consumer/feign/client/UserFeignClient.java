package org.spring.cloud.consumer.feign.client;

import org.spring.cloud.consumer.feign.config.FeignContractConfig;
import org.spring.cloud.feign.api.UserApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "spring-cloud-provider", configuration = FeignContractConfig.class)
public interface UserFeignClient extends UserApiService {
	
	// SpringMVC 版本
	// @GetMapping("/users/{name}")
	// public String getUser(@PathVariable("name") String name);
	
	// Feign 版本
	@RequestLine("GET /users/{name}")
	public String getUser(@Param ("name") String name);
	
}
