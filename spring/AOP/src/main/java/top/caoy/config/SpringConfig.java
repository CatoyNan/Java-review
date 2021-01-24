package top.caoy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.caoy.aspect.RetryAspect;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "top.caoy")
public class SpringConfig {
    @Bean
    public RetryAspect retryAspect(){
        RetryAspect retryAspect = new RetryAspect();
        retryAspect.setMaxRetry(10);
        return retryAspect;
    }

}
