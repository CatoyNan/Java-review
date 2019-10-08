package top.catoy.interceptorandfilter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author admin
 * @Date 2019-10-02 15:26
 * @Version 1.0
 **/
@RestController
public class HelloController {
    @PostMapping("/hello")
    public String hello(HttpServletRequest request) throws IOException {
        String s = request.getParameter("name");
        System.out.println("do Controller");
        System.out.println(s);
        System.out.println(request.getCharacterEncoding());
        return s;
    }
}
