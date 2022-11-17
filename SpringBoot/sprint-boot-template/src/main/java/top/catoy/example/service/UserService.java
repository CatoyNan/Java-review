package top.catoy.example.service;

import top.catoy.example.pojo.data.User;
import top.catoy.example.pojo.dto.UserDTO;

public interface UserService {
    User updateUser(int id, UserDTO userDTO);
}
