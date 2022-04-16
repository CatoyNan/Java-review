package top.caoy.aopExample.aopManual.beanFactory;

import top.caoy.aopExample.aopManual.aspect.MyAspect;
import top.caoy.aopExample.aopTarget.UserService;
import top.caoy.aopExample.aopTarget.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 12:19
 **/
 public class BeanFactory {
   public static UserService creatBean(){
        //目标类
        UserService userService = new UserServiceImpl();
        //切面类
        MyAspect myAspect = new MyAspect();
        //代理类
        UserService userServiceProxy =(UserService) Proxy.newProxyInstance(BeanFactory.class.getClassLoader(),userService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               Object result = null;
                if(method.getName().equals("addUser")){
                    //执行前方法
                    myAspect.before();
                    //执行代理类方法
                    result = method.invoke(userService,args);
                    //Object result = method.invoke(userService,args);
                    //执行后方法
                    myAspect.after();
                } else {
                    result = method.invoke(userService, args);
                }
                return result;
            }
        });
        return userServiceProxy;
    }
}
