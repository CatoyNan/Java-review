package top.catoy.example.common.controller;

import lombok.Data;

@Data
public class HttpResult<T> {
    private boolean success;
    private T data;
    private int code;
    private String message;
}
