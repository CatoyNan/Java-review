package top.caoy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import top.caoy.pojo.User;

@Lazy
@Service
@Aspect
public class LogAop {

    //切入点
    @Pointcut("within(top.caoy.service.impl.UserServiceImpl)")
    public void performance1() {
    }

    //切入点
    @Pointcut("execution(* top.caoy.service.impl.UserServiceImpl.addUser(top.caoy.pojo.User)) && args(user)")
    public void performance2(User user) {
    }

    //切入点
    @Pointcut("execution(* top.caoy.service.impl.UserServiceImpl.twoArgs(..)) && args(..,d)))")
    public void performance3(int d) {
    }

//    //通知方法会在目标方法执行之前执行
//    @Before("performance1()")
//    public void before() {
//        System.out.println("before");
//    }
//
//    //通常方法会在目标方法返回后调用
//    @AfterReturning("performance1()")
//    public void afterReturning() {
//        System.out.println("afterReturning");
//    }
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

    //通知方法将目标方法封装起来
    @Around("performance3(d)")
    public Object around3(ProceedingJoinPoint joinPoint, int d) throws Throwable {
         Object proceed = joinPoint.proceed();
         System.out.println(d);
         return proceed;
    }

//    //通知方法会在目标方法返回或抛出异常后调用
//    @After("performance1()")
//    public void after() {
//        System.out.println("after");
//    }

}