import org.junit.Test;
import top.caoy.beanFactory.BeanFactoryCjlib;
import top.caoy.serviceCjlib.UserServiceImpl;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 18:35
 **/
public class TestCjlib {
    @Test
    public void testCjlib(){
        UserServiceImpl userService = BeanFactoryCjlib.creatBean();
        userService.addUser();
        userService.delUser();
    }
}
