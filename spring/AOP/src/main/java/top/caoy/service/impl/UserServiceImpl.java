package top.caoy.service.impl;

import org.springframework.stereotype.Service;
import top.caoy.service.UserService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 12:12
 **/
@Service("UserService")
public class UserServiceImpl implements UserService {
    public void addUser() {
        System.out.println("@addUser");
    }

    public void delUser() {
        System.out.println("@delUser");
    }

    public void updateUser() {
        System.out.println("@updateUser");
    }

    public void throwError() {
        System.out.println("@throwError");
        int a = 1/0;
    }

    public String returnString() {
        return "hello world";
    }
}
