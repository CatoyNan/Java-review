package src.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:调用处理程序
 * @author: xjn
 * @create: 2019-04-24 18:46
 **/
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public  MyInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备插入数据");
        Object returnvalue = method.invoke(target,args);
        System.out.println("数据插入成功");
        return returnvalue;
    }
}
