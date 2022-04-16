package top.caoy.aopTest.aopExample.aopManual;

import org.junit.Test;
import top.caoy.aopExample.aopManual.beanFactory.BeanFactoryCjlib;
import top.caoy.aopExample.aopTarget.serviceCjlib.UserServiceImpl;

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