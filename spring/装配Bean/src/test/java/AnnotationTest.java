import annotationTest.UserService;
import annotationTest.UserServiceImplAnnotation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-25 15:20
 **/
public class AnnotationTest {
    public static void main(String[] args){
        String path = "applicationContext2.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService = applicationContext.getBean("UserServiceImplAnnotationId",UserService.class);
        userService.getAllInfo();
        System.out.println(userService.toString());
    }
}
