package BeanFacoty;

import service.UserService;
import service.impl.UserServiceImpl;

import java.util.Date;

/**
 * @description:静态工厂
 * @author: xjn
 * @create: 2019-04-21 10:45
 **/
public class MyBeanFacory {
    public static UserService createServie(){
        return new UserServiceImpl();
    }
}
