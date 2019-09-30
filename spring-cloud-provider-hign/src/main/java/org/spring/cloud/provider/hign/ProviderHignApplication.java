package org.spring.cloud.provider.hign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class ProviderHignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderHignApplication.class, args);
	}

	@Value("${server.port}")
    private String port;
	
	@GetMapping("/users/{name}")
	public String getUser(@PathVariable("name") String name) {
		return "user name: "+ name +", server name: spring-cloud-provider-hign, port: " + port;
	}
}
