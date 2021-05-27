package top.catoy.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                System.out.println(beanName);
                return bean;
            }

            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }
        };
    }
}
