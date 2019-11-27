package org.spring.cloud.gateway.filter.global;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author miansen.wang
 * @date 2019-11-27
 */
@Component
public class IPCheckFilter implements GlobalFilter, Ordered {

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		InetSocketAddress host = headers.getHost();
		String hostName = host.getHostName();
		// 此处的 IP 地址是写死的，实际中需要采取配置的方式
		if ("localhost".equals(hostName)) {
			ServerHttpResponse response = exchange.getResponse();
			byte[] datas = "{\"code\": 401,\"message\": \"非法请求\"}".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
			return response.writeWith(Mono.just(buffer));
		}
		return chain.filter(exchange);
	}

}
