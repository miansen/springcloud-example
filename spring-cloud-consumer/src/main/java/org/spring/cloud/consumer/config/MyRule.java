package org.spring.cloud.consumer.config;

import java.util.List;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/**
 * 通过实现 IRule 接口自定义负载均衡策略
 * @author miansen.wang
 * @date 2019-11-08
 */
public class MyRule implements IRule {

	private ILoadBalancer lb;

	@Override
	public Server choose(Object key) {
		List<Server> servers = lb.getAllServers();
		return servers.get(0);
	}

	@Override
	public void setLoadBalancer(ILoadBalancer lb) {
		this.lb = lb;
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return lb;
	}

}
