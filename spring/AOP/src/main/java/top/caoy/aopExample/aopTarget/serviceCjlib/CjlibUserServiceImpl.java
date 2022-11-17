package top.caoy.aopExample.aopTarget.serviceCjlib;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 12:12
 **/
@Service
public class CjlibUserServiceImpl {
    public void addUser() {
        System.out.println("addUser");
    }

    public void delUser() {
        System.out.println("delUser");
    }

    public void updateUser() {
        System.out.println("updateUser");
    }
}
