package catoy.threadLocalTest.controller;

import catoy.threadLocalTest.ThreadLocalRequestHandle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ThreadLocalController
 * @Description TODO
 * @Author admin
 * @Date 2019-10-03 11:32
 * @Version 1.0
 **/
@RestController
public class ThreadLocalController {
    @GetMapping("/threadLocal")
    public String helloThreadLocal(){
        return ThreadLocalRequestHandle.get();
    }

}
