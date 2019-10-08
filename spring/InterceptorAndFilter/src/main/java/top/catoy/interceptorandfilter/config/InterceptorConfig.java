package top.catoy.interceptorandfilter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author admin
 * @Date 2019-10-02 15:38
 * @Version 1.0
 **/
@Component
public class InterceptorConfig {
    @Bean
    public HandlerInterceptor handlerInterceptor1(){
        return new HandlerInterceptor(){

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("handlerInterceptor1 preHandle");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                System.out.println("handlerInterceptor1 postHandle");
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.out.println("handlerInterceptor1 afterCompletion");
            }
        };
    }

    @Bean
    public HandlerInterceptor handlerInterceptor2(){
        return new HandlerInterceptor(){

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("handlerInterceptor2 preHandle");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                System.out.println("handlerInterceptor2 postHandle");
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.out.println("handlerInterceptor2 afterCompletion");
            }
        };
    }
}
