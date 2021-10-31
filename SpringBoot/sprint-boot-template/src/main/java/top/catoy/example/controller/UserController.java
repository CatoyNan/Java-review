package top.catoy.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.catoy.example.common.controller.BaseController;
import top.catoy.example.common.controller.HttpResult;
import top.catoy.example.pojo.data.User;
import top.catoy.example.pojo.dto.UserDTO;
import top.catoy.example.service.UserService;

import javax.validation.Valid;

@RestController("/")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/{id}")
    public HttpResult<UserDTO> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        UserDTO convert = UserDTO.convert(updatedUser);
        return responseSuccess(convert);
    }
}
