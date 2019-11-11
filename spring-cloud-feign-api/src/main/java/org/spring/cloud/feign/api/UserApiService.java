package org.spring.cloud.feign.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserApiService {
	
	@GetMapping("/api/users/{name}")
	public String getUserApi(@PathVariable("name") String name);
}
