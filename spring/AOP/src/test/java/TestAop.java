import org.junit.Test;
import top.caoy.beanFactory.BeanFactory;
import top.caoy.service.UserService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 12:47
 **/
public class TestAop {
    @Test
    public void test(){
        UserService userService = BeanFactory.creatBean();
        userService.addUser();
        userService.delUser();
    }
}
