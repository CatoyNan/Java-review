import entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 15:31
 **/
public class TestIoc {
    @Test
    public void test1(){
       //1获得容器
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        //2获得内容
        UserService userService = (UserService)applicationContext.getBean("userServiceId");
        User user = userService.getAllInfo();
        System.out.println(user.toString());
    }
}
