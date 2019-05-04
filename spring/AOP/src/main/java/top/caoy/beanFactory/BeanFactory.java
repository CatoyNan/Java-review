package top.caoy.beanFactory;

import top.caoy.aspect.MyAspect;
import top.caoy.service.UserService;
import top.caoy.service.impl.UserServiceImpl;

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
       final UserService userService = new UserServiceImpl();
        //切面类
       final MyAspect myAspect = new MyAspect();
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
                }
                return result;
            }
        });
        return userServiceProxy;
    }
}
