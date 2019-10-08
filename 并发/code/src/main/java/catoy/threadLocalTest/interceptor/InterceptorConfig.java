package catoy.threadLocalTest.interceptor;

import catoy.threadLocalTest.ThreadLocalRequestHandle;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName InterceptorConfig
 * @Description 请求结束时去除ThreadLocal中的数据，避免内存溢出
 * @Author admin
 * @Date 2019-10-03 11:49
 * @Version 1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor()).addPathPatterns("/**");
    }

    public HandlerInterceptor handlerInterceptor() {
       return new HandlerInterceptor(){
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.out.println("afterCompletion remove");
                ThreadLocalRequestHandle.remove();
            }
        };
    }
}
