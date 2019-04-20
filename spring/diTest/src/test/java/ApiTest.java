import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.UserService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 19:40
 **/
public class ApiTest {
    @Test
    public void test1(){
        String path = "classpath:applicationContext.xml";
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(path);
        UserService userService = (UserService) fileSystemXmlApplicationContext.getBean("userServiceId");
        System.out.println(userService.getAllInfo());
    }
}
