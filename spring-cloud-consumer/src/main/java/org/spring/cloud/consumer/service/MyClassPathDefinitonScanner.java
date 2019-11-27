package org.spring.cloud.consumer.service;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class MyClassPathDefinitonScanner extends ClassPathBeanDefinitionScanner {

	private Class type;
    public MyClassPathDefinitonScanner(BeanDefinitionRegistry registry,Class<? extends Annotation> type){
         super(registry,false);
         this.type = type;
     }
     /**
      * 注册 过滤器
      */
     public void registerTypeFilter(){
        addIncludeFilter(new AnnotationTypeFilter(type));
     }
}
