package dao.impl;

import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-20 16:28
 **/
@Repository
public class UserDaoImpl implements UserDao {
    public User getAllInfo() {
        return new User("小丽",new Date());
    }
}
