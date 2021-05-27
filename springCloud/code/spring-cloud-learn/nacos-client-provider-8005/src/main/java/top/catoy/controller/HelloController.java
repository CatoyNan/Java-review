package top.catoy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/pay/{id}")
    public String hello(@PathVariable Long id) {
        return "server.port:" + serverPort + "id" + id;
    }
}
