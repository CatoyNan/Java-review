package top.caoy.aopExample.aopAutomic.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.caoy.logTest.pojo.User;

@Configuration
@Aspect
@EnableAspectJAutoProxy
public class MyAspect {

    //切入点
    @Pointcut("within(top.caoy.aopExample.aopTarget.impl.UserServiceImpl)")
    public void performance1() {
    }

    //切入点
    @Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.addUser(top.caoy.logTest.pojo.User)) && args(user)")
    public void performance2(User user) {
    }

    //切入点
    @Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.twoArgs(..)) && args(..,d)))")
    public void performance3(int d) {
    }

    //切入点
    @Pointcut("within(top.caoy.aopExample.aopTarget.serviceCjlib.CjlibUserServiceImpl)")
    public void performance4() {
    }

    //通知方法会在目标方法执行之前执行
    @Before("performance4()")
    public void before() {
        System.out.println("before");
    }

    //通常方法会在目标方法返回后调用
    @AfterReturning("performance4()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    //不论一个方法是如何结束的，最终通知都会运行,相当于finally
    @After("performance4()")
    public void after() {
        System.out.println("after");
    }
//
//    //通知方法会在目标方法抛出异常后调用
//    @AfterThrowing("performance1()")
//    public void afterThrowing() {
//        System.out.println("afterThrowing");
//    }

//    //通知方法将目标方法封装起来
//    @Around("performance1()")
//    public Object around1(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("around-before");
//        Object proceed = joinPoint.proceed();
//        System.out.println("around-after");
//        return proceed;
//    }

//    //通知方法将目标方法封装起来
//    @Around("performance2(user)")
//    public Object around2(ProceedingJoinPoint joinPoint,User user) throws Throwable {
//        System.out.println(user.getAge());
//        Object proceed = joinPoint.proceed();
//        System.out.println(user.getName());
//        return proceed;
//    }

//    //通知方法将目标方法封装起来
//    @Around("performance3(d)")
//    public Object around3(ProceedingJoinPoint joinPoint, int d) throws Throwable {
//        Object proceed = joinPoint.proceed();
//        System.out.println(d);
//        return proceed;
//    }

//    //通知方法将目标方法封装起来
//    @Around("performance3(d)")
//    public Object around3(ProceedingJoinPoint joinPoint, int d) throws Throwable {
//        Object proceed = joinPoint.proceed();
//        System.out.println(d);
//        return proceed;
//    }

//    //通知方法会在目标方法返回或抛出异常后调用
//    @After("performance1()")
//    public void after() {
//        System.out.println("after");
//    }

}