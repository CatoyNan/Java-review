package BeanFacoty;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.xml.ws.Service;

/**
 * @description: 实例工厂
 * @author: xjn
 * @create: 2019-04-21 11:04
 **/
public class MyBeanFacory2 {
    public UserService createService(){
        return new UserServiceImpl();
    }
}
