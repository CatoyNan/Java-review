import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.caoy.service.UserService;

/**
 * @description: 全自动代理
 * @author: xjn
 * @create: 2019-05-05 20:13
 **/
public class TestSpringAutoAspect {
    @Test
    public void testSpringAutoAspect(){
        String path = "applicationContext2.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService =(UserService) applicationContext.getBean("UserServiceId");
        userService.delUser();
    }
}
