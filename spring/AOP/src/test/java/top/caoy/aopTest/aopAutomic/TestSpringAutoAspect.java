package top.caoy.aopTest.aopAutomic;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.caoy.aopExample.aopTarget.UserService;

/**
 * @description: 全自动代理 基于xml
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/automic/applicationContext2.xml");
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

//         Object o = AopContext.currentProxy();
    }
}
