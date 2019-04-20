package service.impl;

import dao.UserDao;
import entity.User;
import service.UserService;

import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 15:21
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getAllInfo() {
        return userDao.getAllInfo();
    }
}
