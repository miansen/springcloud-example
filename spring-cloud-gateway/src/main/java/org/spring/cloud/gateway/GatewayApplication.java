package org.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;

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
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/get").filters(f -> f.filter((exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest().mutate().header("X-Request-Foo", "Bar").build();
			return chain.filter(exchange.mutate().request(request).build());
		}).addResponseHeader("X-Response-Foo", "Bar")).uri("http://httpbin.org")).build();
	}
}
