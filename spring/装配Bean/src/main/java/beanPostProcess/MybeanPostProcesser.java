package beanPostProcess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:后处理bean实现类
 * @author: xjn
 * @create: 2019-04-23 11:35
 **/
public class MybeanPostProcesser implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("前方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后方法");
        //生成代理
        return Proxy.newProxyInstance(MybeanPostProcesser.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy,method,args) ->{
                      System.out.println("事物插入1");
                      //执行目标方法
                      Object returnvalue = method.invoke(bean,args);
                      System.out.println("事物插入2");
                      return returnvalue;
                });
    }
}
