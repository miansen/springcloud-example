package org.spring.cloud.gateway.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class MyQueryRoutePredicateFactory extends AbstractRoutePredicateFactory<MyQueryRoutePredicateFactory.Config> {

	public MyQueryRoutePredicateFactory() {
		super(Config.class);
	}
	
	/**
	 * 返回有关 args 数量和快捷方式分析顺序的提示。
	 * <p>必须要重写这个方法，否则 Config 设置不了参数。
	 */
	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("param", "regexp");
	}



	/**
	 * 自己实现 Query 路由断言工厂
	 * <p>在这个方法里写逻辑
	 */
	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return exchange -> {
			System.out.println(config.toString());
			if (config.getRegexp() == null || "".equals(config.getRegexp())) {
				return exchange.getRequest().getQueryParams().containsKey(config.getParam());
			}
			List<String> values = exchange.getRequest().getQueryParams().get(config.getParam());
			if (values == null) {
				return false;
			}
			for (String value : values) {
				if (value != null && value.matches(config.getRegexp())) {
					return true;
				}
			}
			return false;
		};
	}

	/**
	 * Config 静态内部类用来保存配置信息
	 * @author miansen.wang
	 * @date 2019-11-14
	 */
	public static class Config {

		// 请求参数名
		private String param;

		// 请求参数值的正则
		private String regexp;

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		public String getRegexp() {
			return regexp;
		}

		public void setRegexp(String regexp) {
			this.regexp = regexp;
		}

		@Override
		public String toString() {
			return "Config {param=" + param + ", regexp=" + regexp + "}";
		}
		
	}
}
