package top.catoy.example.common.controller;

import lombok.Data;

public enum ResultCode {
    SUCCESS("调用成功", 2000),
    FAIL("调用失败", 1000),
    INVALID_PARAM("非法参数", 3000),
    MISTYPE_PARAM("参数格式有误", 4000),
    MISSING_PARAM("缺少参数", 5000),
    UNSUPPORTED_METHOD("不支持请求类型", 6000),
    SYSTEM_ERROR("其他异常", 7000);



    private String massege;
    private int code;

    ResultCode(String massege, int code) {
        this.massege = massege;
        this.code = code;
    }

    public String getMassege() {
        return massege;
    }

    public void setMassege(String massege) {
        this.massege = massege;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}