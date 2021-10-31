package top.catoy.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import top.catoy.example.common.controller.BaseController;
import top.catoy.example.common.controller.HttpResult;
import top.catoy.example.common.controller.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Sawyer
 * @Description: 统一异常处理
 * @Date: Created in 上午11:17 17/8/11
 */
@ControllerAdvice
@RestController
@Slf4j
public class ExceptionAdvice extends BaseController {

@Autowired
HttpServletRequest httpServletRequest;

/**
 * 
 * 异常日志记录
 *
 * @param e
 */
private void logErrorRequest(Exception e) {
    String info = String.format("报错API URL: %s%nQuery String: %s",
            httpServletRequest.getRequestURI(),
            httpServletRequest.getQueryString());
    log.error(info);
    log.error(e.getMessage());
    String ipInfo = "报错访问者IP信息：" + httpServletRequest.getRemoteAddr() + "," + httpServletRequest.getRemoteHost();
    log.error(ipInfo);
}

/**
 * 参数校验异常
 *
 * @param exception
 * @return
 */
@ExceptionHandler(MethodArgumentNotValidException.class)
protected HttpResult methodArgumentNotValid(MethodArgumentNotValidException exception) {
    logErrorRequest(exception);
    return responseFail(ResultCode.INVALID_PARAM);
}

/**
 * 参数格式有误
 *
 * @param exception
 * @return
 */
@ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
protected HttpResult typeMismatch(Exception exception) {
    logErrorRequest(exception);
    return responseFail(ResultCode.MISTYPE_PARAM);
}

/**
 * 缺少参数
 *
 * @param exception
 * @return
 */
@ExceptionHandler(MissingServletRequestParameterException.class)
protected HttpResult missingServletRequestParameter(MissingServletRequestParameterException exception) {
    logErrorRequest(exception);
    return responseFail(ResultCode.MISSING_PARAM);
}

/**
 * 不支持的请求类型
 *
 * @param exception
 * @return
 */
@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
protected HttpResult httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
    logErrorRequest(exception);
    return responseFail(ResultCode.UNSUPPORTED_METHOD);
}

///**
// * 业务层异常
// *
// * @param exception
// * @return
// */
//@ExceptionHandler(ServiceEx.class)
//protected HttpResult serviceException(ServiceEx exception) {
//    logErrorRequest(exception);
//    return responseFail(ResultCode.SYSTEM_ERROR, exception.getMessage());
//}

/**
 * 其他异常
 *
 * @param exception
 * @return
 */
@ExceptionHandler({HttpClientErrorException.class, IOException.class, Exception.class})
protected HttpResult commonException(Exception exception) {
    logErrorRequest(exception);
    return responseFail(ResultCode.SYSTEM_ERROR);
}
}