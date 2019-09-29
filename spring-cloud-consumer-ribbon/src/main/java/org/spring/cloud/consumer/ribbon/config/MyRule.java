package org.spring.cloud.consumer.ribbon.config;

import java.util.List;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

/**
 * 自定义负载均衡的策略
 * @author miansen.wang
 * @date 2019-09-25
 */
public class MyRule extends AbstractLoadBalancerRule {

	@Override
	public Server choose(Object key) {
		List<Server> servers = getLoadBalancer().getAllServers();
		// 取服务列表的第一个
		return servers.get(0);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		// TODO Auto-generated method stub
		
	}
}
