package org.spring.cloud.consumer.config;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

/**
 * 通过继承 AbstractLoadBalancerRule 抽象类实现负载均衡的策略
 * @author miansen.wang
 * @date 2019-11-08
 */
public class MyRule2 extends AbstractLoadBalancerRule {

	@Override
	public Server choose(Object key) {
		List<Server> servers = getLoadBalancer().getAllServers();
		return servers.get(0);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		// TODO Auto-generated method stub

	}

}
