package top.catoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EarekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EarekaServerApplication.class, args);
    }
}
