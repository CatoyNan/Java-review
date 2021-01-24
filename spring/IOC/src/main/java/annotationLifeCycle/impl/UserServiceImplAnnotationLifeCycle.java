package annotationLifeCycle.impl;

import annotationLifeCycle.UserService;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("无参构造方法");
    }

    @Autowired
    public UserServiceImplAnnotationLifeCycle(UserDao userDao) {
        System.out.println("有参构造方法");
        this.userDao = userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        System.out.println("setter");
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
