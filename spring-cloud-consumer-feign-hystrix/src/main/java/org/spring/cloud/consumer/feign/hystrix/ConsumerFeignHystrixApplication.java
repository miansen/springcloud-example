package org.spring.cloud.consumer.feign.hystrix;

import org.spring.cloud.consumer.feign.hystrix.config.ExcludeFromComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

// 因为需要监控功能，所以需要显示的开启断路器
@EnableCircuitBreaker
//忽略使用  @ExcludeFromComponentScan 注解的类
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class)})
@EnableFeignClients
@EnableEurekaClient
@RestController
@SpringBootApplication
public class ConsumerFeignHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerFeignHystrixApplication.class, args);
	}
	
	/**
	 * 低版本直接启动即可使用 http://ip:port/hystrix.stream 查看监控信息
	 * 高版本需要添加本方法方可使用 http://ip:port/{urlMappings} 查看监控信息
	 * @return
	 */
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
	    ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
	    registrationBean.setLoadOnStartup(1);
	    registrationBean.addUrlMappings("/actuator/hystrix.stream");
	    registrationBean.setName("HystrixMetricsStreamServlet");
	    return registrationBean;
	}
}
