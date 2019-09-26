[TOC]
# springmvc自定义拦截器
如果我们要实现自己定义的拦截器的话，有如下3个步骤：
1. 我们自己写的类要实现HandlerInterceptor接口，里面包含三个方法，说明如下。
2. 把这个类加载到IOC容器中
3. 把自定义的拦截器追加到我们的过滤器链中
---
## 步骤1、2如下
```java
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
```
## 步骤3如下
我自己写一个配置类，实现springmvc的过滤器链方法，把我们自己写的过滤器类，加入到过滤器链中。如下：
```java
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
	    // 把我们自己的过滤器类，加入到spring请求的过滤器链中
		registry.addInterceptor(logInterceptor);
	}
}
```
## 写一个测试方法
```java
@RequestMapping("/hello")
public String hello(){
    log.info("Welcome to access RequestMapping: /hello!");
    return "Hello World!";
}
```

测试结果如下
```text
2019-02-21 16:13:31.097  INFO 9860 --- [nio-8080-exec-1] c.g.m.interceptor.config.LogInterceptor  : 在处理请求之前
2019-02-21 16:13:31.097  INFO 9860 --- [nio-8080-exec-1] c.g.m.interceptor.config.LogInterceptor  : Method executed 执行的方法
2019-02-21 16:13:31.098  INFO 9860 --- [nio-8080-exec-1] c.g.m.interceptor.config.LogInterceptor  : 请求完成!
2019-02-21 16:13:31.116  INFO 9860 --- [nio-8080-exec-1] c.g.m.interceptor.config.LogInterceptor  : 在处理请求之前
2019-02-21 16:13:31.154  INFO 9860 --- [nio-8080-exec-1] c.g.m.interceptor.config.LogInterceptor  : Method executed 执行的方法
2019-02-21 16:13:31.207  INFO 9860 --- [nio-8080-exec-1] c.g.m.interceptor.config.LogInterceptor  : 请求完成!
2019-02-21 16:13:36.176  INFO 9860 --- [nio-8080-exec-2] c.g.m.interceptor.config.LogInterceptor  : 在处理请求之前
2019-02-21 16:13:36.177  INFO 9860 --- [nio-8080-exec-2] c.g.m.i.controller.SimpleWebController   : Welcome to access RequestMapping: /hello!
2019-02-21 16:13:36.206  INFO 9860 --- [nio-8080-exec-2] c.g.m.interceptor.config.LogInterceptor  : Method executed 执行的方法
2019-02-21 16:13:36.206  INFO 9860 --- [nio-8080-exec-2] c.g.m.interceptor.config.LogInterceptor  : 请求完成!
```