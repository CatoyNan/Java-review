package com.gx.mvc.interceptor.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义一个日志拦截器
 * <p>
 * - HandlerInterceptor：自定义处理程序执行链。应用程序可以为某些处理程序组注册任意数量的现有或自定义拦截器，以添加常见的预处理行为，
 * 而无需修改每个处理程序实现。
 * <p>
 * - preHandle（HttpServletRequest请求，HttpServletResponse响应，Object对象）抛出异常：拦截处理程序的执行。
 * 在HandlerMapping确定适当的处理程序对象之后调用，但在HandlerAdapter调用处理程序之前。
 * <p>
 * - postHandle（HttpServletRequest请求，HttpServletResponse响应，Object对象，ModelAndView模型）抛出异常：拦截处理程序的执行。
 * 在HandlerAdapter实际调用处理程序之后调用，但在DispatcherServlet呈现视图之前调用。可以通过给定的ModelAndView将其他模型对象暴露给视图。
 * <p>
 * - afterCompletion（HttpServletRequest请求，HttpServletResponse响应，Object对象，异常arg3）抛出异常：完成请求处理后回调，即渲染视图后。
 * 将调用处理程序执行的任何结果，从而允许适当的资源清理。
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) {
		log.info("请求完成!");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) {
		log.info("Method executed 执行的方法");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
		log.info("在处理请求之前");
		return true;
	}

}