package top.caoy.service.impl;

import org.springframework.stereotype.Service;
import top.caoy.annotion.DoRetry;
import top.caoy.pojo.SendMessageException;
import top.caoy.service.SendMessageService;

import java.util.Date;

@Service("mybean")
public class SendMessageServiceImpl implements SendMessageService {
    @Override
    @DoRetry
    public void sendMessage(String message) {
        long time = new Date().getTime();
        if(time % 2 == 0) {
            System.out.println("success");
        } else {
            throw new SendMessageException("send error");
        }
    }
}
