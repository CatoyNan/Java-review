package top.caoy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAop {

    //切入点
    @Pointcut("within(top.caoy.service.impl.UserServiceImpl)")
    public void performance() {
    }

    //通知方法会在目标方法执行之前执行
    @Before("performance()")
    public void before() {
        System.out.println("before");
    }

    //通常方法会在目标方法返回后调用
    @AfterReturning("performance()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    //通知方法会在目标方法抛出异常后调用
    @AfterThrowing("performance()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    //通知方法将目标方法封装起来
    @Around("performance()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around-before");
        Object proceed = joinPoint.proceed();
        System.out.println("around-after");
        return proceed;
    }

    //通知方法会在目标方法返回或抛出异常后调用
    @After("performance()")
    public void after() {
        System.out.println("after");
    }
}