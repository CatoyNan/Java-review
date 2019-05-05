import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.caoy.aspect.MySpringAspect;
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
        UserService userService =(UserService) applicationContext.getBean("ProxyUserServiceId");
        userService.delUser();
    }
}
