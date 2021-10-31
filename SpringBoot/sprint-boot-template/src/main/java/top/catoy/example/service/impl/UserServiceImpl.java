package top.catoy.example.service.impl;

import org.springframework.stereotype.Service;
import top.catoy.example.pojo.data.User;
import top.catoy.example.pojo.dto.UserDTO;
import top.catoy.example.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    public User updateUser(int id, UserDTO userDTO) {
        return new User(1,"小米",10,"engineer,",1);
    }
}
