import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.caoy.service.ReadService;
import top.caoy.service.UserService;

/**
 * @description: 全自动代理
 * @author: xjn
 * @create: 2019-05-05 20:13
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:/applicationContext2.xml"})
public class TestSpringAutoAspect {
//    @Autowired
//    @Qualifier("UserServiceId")
//    private UserService userService;
    @Test
    public void testSpringAutoAspect(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/applicationContext2.xml");
        UserService userService =(UserService) applicationContext.getBean("UserServiceId");
        userService.delUser();
        if (AopUtils.isAopProxy(userService)) {
            System.out.println("isAopProxy");
        }

        if (AopUtils.isCglibProxy(userService)) {
            System.out.println("isCglibProxy");
        }

        if (AopUtils.isJdkDynamicProxy(userService)) {
            System.out.println("isJdkDynamicProxy");
        }

         Object o = AopContext.currentProxy();
    }
}
