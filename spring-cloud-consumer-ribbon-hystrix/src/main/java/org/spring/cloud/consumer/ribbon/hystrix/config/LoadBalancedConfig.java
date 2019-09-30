package org.spring.cloud.consumer.ribbon.hystrix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@ExcludeFromComponentScan
@Configuration
public class LoadBalancedConfig {

	@Bean
	public IRule ribbonRule() {
		// return new RoundRobinRule(); // 轮训
		// return new WeightedResponseTimeRule(); // 加权权重
		// return new RetryRule(); // 带有重试机制的轮训
		// return new RandomRule(); // 随机
		return new MyRule(); // 自定义规则
	}
}
