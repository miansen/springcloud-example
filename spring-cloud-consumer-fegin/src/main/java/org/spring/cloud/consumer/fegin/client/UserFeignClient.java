package org.spring.cloud.consumer.fegin.client;

import org.spring.cloud.consumer.fegin.config.FeignContractConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.Param;
import feign.RequestLine;

// @FeignClient("spring-cloud-provider")
@FeignClient(name = "spring-cloud-provider", configuration = FeignContractConfig.class)
public interface UserFeignClient {
	
	// SpringMVC 版本
	
	/*@GetMapping("/users/{name}")
	public String getUser(@PathVariable("name") String name) {
		return userFeignClient.getUser(name);
	}*/
	
	// Feign 版本
	@RequestLine("GET /users/{name}")
	public String getUser(@Param ("name") String name);
	
}
