package top.catoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.dao.mapper1.UserMapper1;
import top.catoy.dao.mapper2.UserMapper2;
import top.catoy.model.User;

@RestController
@RequestMapping(("/user"))
public class UserController {
    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    @GetMapping("/getAllUser1")
    public User getAllUser1() {
       return userMapper1.findAll();
    }

    @GetMapping("/getAllUser2")
    public User getAllUser2() {
        return userMapper2.findAll();
    }
}
