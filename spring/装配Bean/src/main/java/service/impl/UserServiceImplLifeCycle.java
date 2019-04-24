package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-22 23:43
 **/
public class UserServiceImplLifeCycle implements UserService {
    private UserDao userDao;

    public UserServiceImplLifeCycle() {
        this.userDao = new UserDaoImpl();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getAllInfo() {
        System.out.println("执行目标类方法");
        return userDao.getAllInfo();
    }

    public void myInit(){
        System.out.println("初始化");
    }

    public void myDestroy(){
        System.out.println("销毁");
    }
}
