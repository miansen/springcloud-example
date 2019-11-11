package org.spring.cloud.provider.controller;

import org.spring.cloud.feign.api.UserApiService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController implements UserApiService {

	@Override
	public String getUserApi(String name) {
		return name + ", from user api";
	}

}
