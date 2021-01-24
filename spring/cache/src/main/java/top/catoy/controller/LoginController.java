package top.catoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.catoy.service.DictService;

@RestController
public class LoginController {
    @Autowired
    private DictService dictService;

    @GetMapping("/login")
    public String login(){
        return "success";
    }

    @GetMapping("/getDict")
    public String getDict() throws InterruptedException {
        return dictService.getDict();
    }
}
