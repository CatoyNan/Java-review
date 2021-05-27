import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.caoy.aspect.MySpringAspect;
import top.caoy.service.ReadService;
import top.caoy.service.UserService;

/**
 * @description: spring半自动
 * @author: xjn
 * @create: 2019-05-05 17:21
 **/
public class TestSpringAspect {
    @Test
    public void testSpringAspect(){
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        ReadService readService =(ReadService) applicationContext.getBean("ProxyUserServiceId");
        readService.readData();
        if (AopUtils.isAopProxy(readService)) {
            System.out.println("isAopProxy");
        }

        if (AopUtils.isCglibProxy(readService)) {
            System.out.println("isCglibProxy");
        }

        if (AopUtils.isJdkDynamicProxy(readService)) {
            System.out.println("isJdkDynamicProxy");
        }
    }
}
