package top.caoy.logTest;

import top.caoy.logTest.pojo.SendMessageException;

public interface SendMessageService {
    public void sendMessage(String message) throws SendMessageException;
}
