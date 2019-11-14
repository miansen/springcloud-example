package org.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/**
	 * 通过代码的形式配置路由
	 * @param builder
	 * @return
	 */
	/*@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						p -> p.path("/get")
						     .filters(f -> f.addRequestHeader("X-Request-Foo", "Bar").addResponseHeader("X-Response-Foo", "Bar"))
						     .uri("http://httpbin.org")
						)
				.build();
	}*/
}
