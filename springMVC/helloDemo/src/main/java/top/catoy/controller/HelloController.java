package top.catoy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author admin
 * @Date 2019-10-05 15:13
 * @Version 1.0
 **/
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "index";
    }
}
