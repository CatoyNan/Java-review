package top.caoy.aopTest.aopExample.aopManual;

import org.junit.Test;
import top.caoy.aopExample.aopManual.beanFactory.BeanFactory;
import top.caoy.logTest.pojo.User;
import top.caoy.aopExample.aopTarget.UserService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 12:47
 **/
public class TestAop {
    @Test
    public void test(){
        UserService userService = BeanFactory.creatBean();
        userService.addUser(new User());
        userService.delUser();
    }
}
