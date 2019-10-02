package top.catoy.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FilterController
 * @Description TODO
 * @Author admin
 * @Date 2019-10-02 14:07
 * @Version 1.0
 **/
@RestController
public class FilterController {
    @GetMapping
    public String hello(){
        return "hello filter";
    }
}
