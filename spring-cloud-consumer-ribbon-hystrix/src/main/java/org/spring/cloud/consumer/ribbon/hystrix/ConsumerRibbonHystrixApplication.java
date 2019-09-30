package org.spring.cloud.consumer.ribbon.hystrix;

import org.spring.cloud.consumer.ribbon.hystrix.config.ExcludeFromComponentScan;
import org.spring.cloud.consumer.ribbon.hystrix.config.LoadBalancedConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
// 忽略使用  @ExcludeFromComponentScan 注解的类
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class)})
// 指定 spring-cloud-provider 服务使用 LoadBalancedConfig 提供的策在均衡的策略
@RibbonClient(name = "spring-cloud-provider", configuration = LoadBalancedConfig.class)
@EnableEurekaClient
@RestController
@SpringBootApplication
public class ConsumerRibbonHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRibbonHystrixApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/users/{name}")
	@HystrixCommand(fallbackMethod = "getUserFallback")
	public String getUser(@PathVariable("name") String name) {
		return restTemplate.getForObject("http://spring-cloud-provider/users/" + name, String.class);
	}

	public String getUserFallback(String name) {
		return "get " + name +" error";
	}
}
