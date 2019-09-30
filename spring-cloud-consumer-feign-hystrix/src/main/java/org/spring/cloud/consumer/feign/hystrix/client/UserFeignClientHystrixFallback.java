package org.spring.cloud.consumer.feign.hystrix.client;

import org.springframework.stereotype.Component;

@Component
public class UserFeignClientHystrixFallback implements UserFeignClient{

	@Override
	public String getUser(String name) {
		return "get " + name +" error";
	}

}
