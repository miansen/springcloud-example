package org.spring.cloud.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class FeignContractConfig {

	@Bean
	public Contract feignContract() {
		return new Contract.Default();
	}
}
