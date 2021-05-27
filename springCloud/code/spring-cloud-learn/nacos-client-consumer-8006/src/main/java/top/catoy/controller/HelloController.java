package top.catoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    private RestTemplate myRestTemplate;

    @Value("${server-url.nacos-user-service}")
    private String serverUrl;


    @GetMapping(value = "/consumer/pay/{id}")
    public String hello(@PathVariable Long id) {
        return myRestTemplate.getForObject(serverUrl + "/payment/pay/" + id,String.class);
    }
}
