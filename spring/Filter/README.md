### 过滤器

过滤器是servlet规范中的一部分，依赖于servlet。

#### 1.Filter 需要实现的三个方法

- `public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {}`

  在web容器启动时调用的方法

- `public void destroy() {}`

  在web容器销毁时调用的方法

- `public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {}`

  拦截到需要拦截到的请求时调用的方法

#### 2.如何在springBoot中配置一个过滤器？

定义一个类FilterConfig类，在类中定义一个方法，方法返回一个Filter对象，并重写Filter的三个方法。然后定义一个返回FilterRegistrationBean的方法，在该方法中设置filter的相关参数，此时一个过滤器就完成了。等服务启动，就会在spring容器中注入这个FilterRegistrationBean。

```java
@Configuration
public class FilterConfig {
  	@Bean
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

		@Bean
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
    public FilterRegistrationBean registrationBean1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter1());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("filter1");
        filterRegistrationBean.setOrder(7);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }
}
```

#### 3.FilterRegistrationBean 方法说明

- `setFilter`设置自定义的filter

- `addUrlPatterns`需要拦截的路径

- `setName`设置过滤器名字

- `setOrder`设置优先级，order的数字越小，优先级越高

  如果`setOrder`中的数字一样，那么先设置的优先级别高





