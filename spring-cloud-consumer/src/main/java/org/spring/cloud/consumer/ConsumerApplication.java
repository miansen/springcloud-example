package org.spring.cloud.consumer;

import javax.sql.DataSource;

import org.spring.cloud.consumer.config.MyRule;
import org.spring.cloud.consumer.service.BaseFile;
import org.spring.cloud.consumer.service.DateUtil;
import org.spring.cloud.consumer.service.FileUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@RibbonClient(name = "spring-cloud-provider", configuration = MyRule.class)
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	
	@Autowired
	private BaseFile baseFile;
	
	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(baseFile);
		System.out.println(fileUtil);
		System.out.println(dateUtil);
		System.out.println(dataSource);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		ConsumerApplication consumerApplication = applicationContext.getBean(ConsumerApplication.class);
		DataSource source = applicationContext.getBean(DataSource.class);
	}
}
