package annotationTest;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-25 15:17
 **/
@Service("UserServiceImplAnnotationId")
public class UserServiceImplAnnotation implements UserService{
    private UserDao userDao;

    public UserServiceImplAnnotation() {
        this.userDao = new UserDaoImpl();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public User getAllInfo() {
        System.out.println("getAllInfo");
        return userDao.getAllInfo();
    }

    @Override
    public String toString() {
        return "UserServiceImplAnnotation{" +
                "userDao=" + userDao +
                '}';
    }
}
