package top.catoy.example.common.controller;

public abstract class BaseController {
    /**
     * 默认成功返回
     *
     * @param data
     * @return
     */
    protected <T> HttpResult<T> responseSuccess(T data) {
        HttpResult<T> restResult = new HttpResult();
        restResult.setSuccess(true);
        restResult.setData(data);
        restResult.setCode(ResultCode.SUCCESS.getCode());
        restResult.setMessage(ResultCode.SUCCESS.getMassege());
        return restResult;
    }

    /**
     * 默认成功返回带消息
     *
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> HttpResult<T> responseSuccess(T data, String msg) {
        HttpResult<T> restResult = new HttpResult();
        restResult.setSuccess(true);
        restResult.setData(data);
        restResult.setCode(ResultCode.SUCCESS.getCode());
        restResult.setMessage(msg);
        return restResult;
    }

    /**
     * 失败返回
     *
     * @param <T>
     * @return
     */
    protected <T> HttpResult<T> responseFail(String msg, ResultCode code) {
        HttpResult<T> resResult = new HttpResult<T>();
        resResult.setSuccess(false);
        resResult.setCode(code.getCode());
        resResult.setMessage(msg == null ? code.getMassege() : msg);
        return resResult;
    }

    /**
     * 返回失败，带信息
     *
     * @param msg
     * @param <T>
     * @return
     */
    protected <T> HttpResult<T> responseFail(String msg) {
        return responseFail(msg, ResultCode.FAIL);
    }

    /**
     * 失败返回，带code
     *
     * @param code
     * @param <T>
     * @return
     */
    protected <T> HttpResult<T> responseFail(ResultCode code) {
        return responseFail(code.getMassege(), code);
    }
}
