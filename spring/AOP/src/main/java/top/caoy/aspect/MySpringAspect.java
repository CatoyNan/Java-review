package top.caoy.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description: spring半自动AOP,采用环绕通知
 * @author: xjn
 * @create: 2019-05-05 17:14
 **/
public class MySpringAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("前方法");
        Object obj = methodInvocation.proceed();
        System.out.println("后方法");
        return obj;
    }
}
