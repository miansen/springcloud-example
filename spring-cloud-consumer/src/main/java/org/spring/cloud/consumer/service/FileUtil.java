package org.spring.cloud.consumer.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@MyBean
@Component
public class FileUtil extends BaseFile {

	public static void main(String[] args) {
		BaseFile baseFile = new BaseFile();
		FileUtil fileUtil = new FileUtil();
		FileUtil baseFile2 = (FileUtil) getBaseFile();
		BaseFile fileUtil2 = new FileUtil();
		String BASE_PACKAGE = "org.spring.cloud.consumer";
		GenericApplicationContext context = new GenericApplicationContext();
		MyClassPathDefinitonScanner myClassPathDefinitonScanner = new MyClassPathDefinitonScanner(context,
				MyBean.class);
		// 注册过滤器
		myClassPathDefinitonScanner.registerTypeFilter();
		int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
		context.refresh();
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		System.out.println(beanCount);
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		FileUtil bean = context.getBean(FileUtil.class);
		DateUtil bean2 = context.getBean(DateUtil.class);
		MyClassPathDefinitonScanner bean3 = context.getBean(MyClassPathDefinitonScanner.class);
	}

	public static BaseFile getBaseFile() {
		return new FileUtil();
	}
}
