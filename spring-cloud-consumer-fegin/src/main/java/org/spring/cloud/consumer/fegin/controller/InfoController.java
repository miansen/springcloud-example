package org.spring.cloud.consumer.fegin.controller;

import org.spring.cloud.consumer.fegin.client.InfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

	@Autowired
	private InfoFeignClient infoFeignClient;
	
	@GetMapping("/info")
	public String getInfo() {
		return infoFeignClient.getInfo();
	}
}
