package top.catoy.interceptorandfilter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName AppConfig
 * @Description TODO
 * @Author admin
 * @Date 2019-10-02 15:36
 * @Version 1.0
 **/
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    HandlerInterceptor handlerInterceptor1;
    @Autowired
    HandlerInterceptor handlerInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor1);
        registry.addInterceptor(handlerInterceptor2);
    }
}
