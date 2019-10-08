package top.catoy.interceptorandfilter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;

/**
 * @ClassName FilterConfig
 * @Description TODO
 * @Author admin
 * @Date 2019-10-02 15:28
 * @Version 1.0
 **/
@Configuration
public class FilterConfig {

    public Filter getEncodingFilter() {
        return (servletRequest, servletResponse, filterChain) -> {
            System.out.println("request doEncodingFilter");
            servletRequest.getParameter("name");
            servletRequest.setCharacterEncoding("Big5");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("response doEncodingFilter");
        };
    }


    public Filter getFilter2() {
        return (servletRequest, servletResponse, filterChain) -> {
            System.out.println("request doFilter2");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("response doFiltle2");
        };
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getEncodingFilter());
        filterRegistrationBean.setName("filter1");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(6);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getFilter2());
        filterRegistrationBean.setName("filter2");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(5);
        return filterRegistrationBean;
    }


}
