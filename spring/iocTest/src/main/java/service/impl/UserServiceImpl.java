package service.impl;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 15:21
 **/
public class UserServiceImpl implements UserService {
    @Autowired
    private User myUser;
    public User getAllInfo() {
        return new User("小明",new Date());
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }
}
