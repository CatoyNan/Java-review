package top.caoy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import top.caoy.pojo.SendMessageException;


@Aspect
public class RetryAspect {
    private static final int DEFAULT_MAX_RETRY = 2;

    private int maxRetry = DEFAULT_MAX_RETRY;

    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Pointcut("execution(* top.caoy.service.impl.SendMessageServiceImpl.sendMessage(..)) && @annotation(top.caoy.annotion.DoRetry))")
    public void point(){}

    @Around("point()")
    public Object doRetry(ProceedingJoinPoint joinPoint) throws Throwable {
        int num = 0;
        SendMessageException sendMessageException;
        do {
            num ++;
            System.out.println(String.format("第%s次重试",num));
            try {
                return joinPoint.proceed();
            } catch (SendMessageException e) {
                sendMessageException = e;
            }
        } while (num < maxRetry);
        throw sendMessageException;
    }
}
