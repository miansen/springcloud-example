package org.spring.cloud.consumer.fegin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("spring-cloud-provider")
public interface InfoFeignClient {

	@GetMapping("/info")
	public String getInfo();
	
}
