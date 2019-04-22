import entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @description: bean作用域
 * @author: xjn
 * @create: 2019-04-22 23:01
 **/
public class TestScope {
    @Test
    //单例
    public void testScope(){
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService1 = applicationContext.getBean("userServiceId3",UserService.class);
        UserService userService2 = applicationContext.getBean("userServiceId3",UserService.class);
        System.out.println(userService1);//service.impl.UserServiceImpl@79be0360
        System.out.println(userService2);//service.impl.UserServiceImpl@79be0360
    }
    @Test
    //多例
    public void testScope2(){
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService1 = applicationContext.getBean("userServiceId4",UserService.class);
        UserService userService2 = applicationContext.getBean("userServiceId4",UserService.class);
        System.out.println(userService1);//service.impl.UserServiceImpl@79be0360
        System.out.println(userService2);//service.impl.UserServiceImpl@22a67b4
    }
}
