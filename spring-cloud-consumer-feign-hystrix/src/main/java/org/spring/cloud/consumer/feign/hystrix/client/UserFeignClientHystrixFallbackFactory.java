package org.spring.cloud.consumer.feign.hystrix.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;

@Component
public class UserFeignClientHystrixFallbackFactory implements FallbackFactory<UserFeignClient> {

	private static Logger log = LoggerFactory.getLogger(UserFeignClientHystrixFallbackFactory.class);
	
	@Override
	public UserFeignClient create(Throwable cause) {
		log.info("the server error is: {}", cause.getMessage());
		return new UserFeignClient() {
			@Override
			public String getUser(String name) {
				return "get " + name +" error";
			}
		};
	}
}
