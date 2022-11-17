package top.caoy.logTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.caoy.logTest.RetryAspect;

@Configuration
@ComponentScan(value = "top.caoy")
public class SpringConfig {
    @Bean
    public RetryAspect retryAspect(){
        RetryAspect retryAspect = new RetryAspect();
        retryAspect.setMaxRetry(10);
        return retryAspect;
    }

}
