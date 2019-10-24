package org.spring.cloud.zuul.server;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyFilter extends ZuulFilter {

	/**
	 * 是否要过滤，可以写具体的逻辑进行判断。
	 * <p>我这里为 true，永远过滤。
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤的具体逻辑
	 */
	@Override
	public Object run() throws ZuulException {
		// 共享 RequestContext，上下文对象
		RequestContext ct = RequestContext.getCurrentContext();
		// 我这里只是简单的获取 token，然后判断是否为空
		String token = ct.getRequest().getParameter("token");
		if(token == null || "".equals(token)) {
			// 过滤该请求，不对其进行路由
			ct.setSendZuulResponse(false);
			// 返回错误代码
			ct.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			// 返回错误信息
			ct.setResponseBody("token must be not null");
		}
		return null;
	}

	/**
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下
	 * <p>pre：路由之前
	 * <p>routing：路由之时
	 * <p>post： 路由之后
	 * <p>error：发送错误调用
	 */
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	/**
	 * 过滤的顺序， 越小越靠前
	 */
	@Override
	public int filterOrder() {
		return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
	}

}
