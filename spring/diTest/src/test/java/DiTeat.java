import entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 16:13
 **/
public class DiTeat {
    @Test
    public void test1(){
        //获取容器
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        //获取内容
        UserService userService = (UserService) applicationContext.getBean("userServiceId");
        User user = userService.getAllInfo();
        System.out.println(user.toString());
    }
}
