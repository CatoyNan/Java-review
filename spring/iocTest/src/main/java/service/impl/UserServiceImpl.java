package service.impl;

import entity.User;
import service.UserService;

import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 15:21
 **/
public class UserServiceImpl implements UserService {
    public User getAllInfo() {
        return new User("小明",new Date());
    }
}
