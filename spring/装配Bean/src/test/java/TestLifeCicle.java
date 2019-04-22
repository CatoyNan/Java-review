import entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @description: bean生命周期
 * @author: xjn
 * @create: 2019-04-22 23:49
 **/
public class TestLifeCicle {
    @Test
    public void testLifeCicle(){
        String path = "applicationContext.xml";
        //初始化容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        //销毁容器
        ((ClassPathXmlApplicationContext) applicationContext).destroy();
    }
    /**
     * 初始化
     * 四月 22, 2019 11:54:47 下午 org.springframework.context.support.ClassPathXmlApplicationContext doClose
     * 信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@3ada9e37: startup date [Mon Apr 22 23:54:46 CST 2019]; root of context hierarchy
     * 销毁
     */
}
