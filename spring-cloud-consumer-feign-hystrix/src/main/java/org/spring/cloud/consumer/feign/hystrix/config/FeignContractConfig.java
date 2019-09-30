package org.spring.cloud.consumer.feign.hystrix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@ExcludeFromComponentScan
@Configuration
public class FeignContractConfig {

	@Bean
	public Contract feignContract() {
		return new Contract.Default();
	}
}
