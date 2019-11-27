package org.spring.cloud.consumer.service;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class MyPathMatchingResourcePatternResolver extends PathMatchingResourcePatternResolver {

	public static void main(String[] args) {
		MyPathMatchingResourcePatternResolver m = new MyPathMatchingResourcePatternResolver();
		try {
			Resource[] resources = m.findPathMatchingResources(CLASSPATH_ALL_URL_PREFIX + "org/spring/cloud/consumer/**/*.class");
			System.out.println(resources);
			for (int i = 0; i < resources.length; i++) {
				String description = resources[i].getDescription();
				System.out.println(description);
				URI uri = resources[i].getURI();
				System.out.println(uri);
				URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(description)});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
