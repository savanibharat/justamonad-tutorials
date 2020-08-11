package com.justamonad.tutorials.spring.dependency.injection;

import javax.inject.Named;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Named
public class ApplicationListener {

	@EventListener
	public void postInitialization(ContextRefreshedEvent event) {
//		ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
//        System.out.println(handlerMethods);
	}

}
