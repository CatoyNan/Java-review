import entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 测试属性注入
 * @author: xjn
 * @create: 2019-04-24 23:06
 **/
public class TestDi {
    public static void main(String[] args){
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        Student student = applicationContext.getBean("studentId",Student.class);
        System.out.println(student.toString());
    }
}
