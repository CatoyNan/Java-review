import annotationLifeCycle.UserService;
import annotationLifeCycle.impl.UserServiceImplAnnotationLifeCycle;
import annotationTest.UserServiceImplAnnotation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:注解生命周期
 * @author: xjn
 * @create: 2019-04-29 20:01
 **/
public class AnnotationLifeCycle {
    @Test
    public void testAnnotationLifeCycle(){
        String path = "applicationContext2.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.getAllInfo();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
