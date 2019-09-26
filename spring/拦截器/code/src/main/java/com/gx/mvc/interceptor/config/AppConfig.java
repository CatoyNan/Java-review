package com.gx.mvc.interceptor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC配置，把自定义的拦截器追加到我们的过滤器链中
 * WebMvcConfigurerAdapter是WebMvcConfigurer的一个实现，它使用空方法，允许子类只覆盖它们感兴趣的方法。
 * WebMvcConfigurer用于定义回调方法，以自定义通过@EnableWebMvc启用的Spring MVC的基于Java的配置
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
	
	@Autowired
	LogInterceptor logInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
	}
}