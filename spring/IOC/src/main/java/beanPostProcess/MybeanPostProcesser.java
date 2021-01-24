package beanPostProcess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:后处理bean实现类
 * @author: xjn
 * @create: 2019-04-23 11:35
 **/
@Configuration
public class MybeanPostProcesser implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("前方法-beanName:%s",beanName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format("后方法-beanName:%s",beanName));
        return bean;
    }
}
