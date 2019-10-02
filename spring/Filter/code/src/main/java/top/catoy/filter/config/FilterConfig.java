package top.catoy.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName FilterConfig
 * @Description TODO
 * @Author admin
 * @Date 2019-10-02 14:01
 * @Version 1.0
 **/
@Configuration
public class FilterConfig {
    public Filter filter1() {
        return new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                System.out.println("qequest doFilter1");
                filterChain.doFilter(servletRequest, servletResponse);
                System.out.println("response doFilter1");
            }

            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void destroy() {

            }
        };
    }


    public Filter filter2() {
        return new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                System.out.println("qequest doFilter2");
                filterChain.doFilter(servletRequest, servletResponse);
                System.out.println("response doFilter2");
            }

            @Override
            public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void destroy() {

            }
        };
    }

    @Bean
    public FilterRegistrationBean registrationBean2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter2());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("filter2");
        filterRegistrationBean.setOrder(7);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean registrationBean1(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter1());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("filter1");
        filterRegistrationBean.setOrder(7);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }


}
