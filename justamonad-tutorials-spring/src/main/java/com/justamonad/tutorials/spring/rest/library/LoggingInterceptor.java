package com.justamonad.tutorials.spring.rest.library;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public final class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		InputStream requestInputStream = request.getInputStream();
        System.out.println(new String(StreamUtils.copyToByteArray(requestInputStream)));
		System.out.println("LoggingInterceptor:: Inside preHandle method.");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoggingInterceptor:: Inside postHandle method.");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("LoggingInterceptor:: Inside afterCompletion method.");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
