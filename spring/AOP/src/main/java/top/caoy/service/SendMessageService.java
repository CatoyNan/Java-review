package top.caoy.service;

import top.caoy.pojo.SendMessageException;

public interface SendMessageService {
    public void sendMessage(String message) throws SendMessageException;
}
