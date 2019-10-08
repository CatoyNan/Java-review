package catoy.threadLocalTest.filter;

import catoy.threadLocalTest.ThreadLocalRequestHandle;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName ThreadLocalFilter
 * @Description TODO
 * @Author admin
 * @Date 2019-10-03 11:34
 * @Version 1.0
 **/
@Configuration
public class ThreadLocalFilter {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Filter filter = (servletRequest, servletResponse, filterChain) -> {
            System.out.println("do filter");
            ThreadLocalRequestHandle.add(String.valueOf(new Date().getTime()));
            filterChain.doFilter(servletRequest,servletResponse);
        };
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("ThreadLocalFilter");
        return filterRegistrationBean;
    }

}
