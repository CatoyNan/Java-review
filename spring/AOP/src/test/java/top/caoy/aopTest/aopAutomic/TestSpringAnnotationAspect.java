package top.caoy.aopTest.aopAutomic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.caoy.aopExample.aopTarget.serviceCjlib.CjlibUserServiceImpl;
import top.caoy.logTest.pojo.User;
import top.caoy.aopExample.aopTarget.UserService;

/**
 * @description: 全自动代理 基于注解
 * @author: xjn
 * @create: 2019-05-05 17:21
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/automic/applicationContext3.xml"})
public class TestSpringAnnotationAspect {
    @Autowired
    private UserService userService;

    //cjlib target
    @Autowired
    private CjlibUserServiceImpl cjlibUserServiceImpl;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setAge("10");
        user.setName("小明");
        userService.addUser(user);
        if (AopUtils.isAopProxy(userService)) {
            System.out.println("isAopProxy");
        }

        if (AopUtils.isCglibProxy(userService)) {
            System.out.println("isCglibProxy");
        }

        if (AopUtils.isJdkDynamicProxy(userService)) {
            System.out.println("isJdkDynamicProxy");
        }

        System.out.println(userService.getClass());
//        Object o = AopContext.currentProxy();
    }

    @Test
    public void testAddUserCjlib() {
        cjlibUserServiceImpl.addUser();
        if (AopUtils.isAopProxy(cjlibUserServiceImpl)) {
            System.out.println("isAopProxy");
        }

        if (AopUtils.isCglibProxy(cjlibUserServiceImpl)) {
            System.out.println("isCglibProxy");
        }

        if (AopUtils.isJdkDynamicProxy(cjlibUserServiceImpl)) {
            System.out.println("isJdkDynamicProxy");
        }

        System.out.println(cjlibUserServiceImpl.getClass());
    }
}
