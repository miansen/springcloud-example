package org.spring.cloud.gateway.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class MyAddRequestHeaderGatewayFilterFactory
		extends AbstractGatewayFilterFactory<MyAddRequestHeaderGatewayFilterFactory.Config> {

	// 必须要显示调用父类的构造方法，指定 configClass，否则报类型转换异常
	public MyAddRequestHeaderGatewayFilterFactory() {
		super(Config.class);
	}

	// 指定 args 数量和顺序，否则无法初始化 Config
	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("headerName", "headerValue");
	}

	// 在这里面写逻辑
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest().mutate()
					.header(config.getHeaderName(), config.getHeaderValue()).build();
			return chain.filter(exchange.mutate().request(request).build());
		};
	}

	// 配置类
	public static class Config {

		// 请求头的名称
		private String headerName;

		// 请求头的值
		private String headerValue;

		public String getHeaderName() {
			return headerName;
		}

		public void setHeaderName(String headerName) {
			this.headerName = headerName;
		}

		public String getHeaderValue() {
			return headerValue;
		}

		public void setHeaderValue(String headerValue) {
			this.headerValue = headerValue;
		}
	}
}
