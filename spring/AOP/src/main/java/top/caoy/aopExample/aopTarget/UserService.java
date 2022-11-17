package top.caoy.aopExample.aopTarget;

import top.caoy.logTest.pojo.User;

public interface UserService {
    void addUser(User user);
    void delUser();
    void updateUser();
    void throwError();
    String returnString();
    void twoArgs(String a,int b);
}
