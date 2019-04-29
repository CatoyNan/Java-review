package annotationLifeCycle.impl;

import annotationLifeCycle.UserService;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-22 23:43
 **/
@Service
public class UserServiceImplAnnotationLifeCycle implements UserService {
    private UserDao userDao;

    public UserServiceImplAnnotationLifeCycle() {
        this.userDao = new UserDaoImpl();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getAllInfo() {
        System.out.println("执行目标类方法");
        return userDao.getAllInfo();
    }

    @PostConstruct
    public void myInit(){
        System.out.println("初始化");
    }

    @PreDestroy
    public void myDestroy(){
        System.out.println("销毁");
    }
}
