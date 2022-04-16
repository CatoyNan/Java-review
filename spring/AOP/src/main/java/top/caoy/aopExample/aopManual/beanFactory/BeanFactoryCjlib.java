package top.caoy.aopExample.aopManual.beanFactory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import top.caoy.aopExample.aopManual.aspect.MyAspect;
import top.caoy.aopExample.aopTarget.serviceCjlib.UserServiceImpl;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-30 18:36
 **/
public class BeanFactoryCjlib {
    public static UserServiceImpl creatBean(){
       //目标类
        final UserServiceImpl userService = new UserServiceImpl();
        //切面类
        final MyAspect myAspect = new MyAspect();
        //代理类,底层:创建目标类的子类
        //核心类
        Enhancer enhancer = new Enhancer();
        //确定父类
        enhancer.setSuperclass(UserServiceImpl.class);
        //设置回调函数,MethodInterceptorde等同于InvocationHandler
        //intercept前三个参数和jdk invoke一样
        enhancer.setCallback(new MethodInterceptor(){
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                if(method.getName().equals("addUser")){
                    //执行前方法
                    myAspect.before();
                    //执行代理类方法
                    result = method.invoke(userService,objects);
                    //Object result = method.invoke(userService,args);
                    //执行后方法
                    myAspect.after();
                }
                return result;
            }
        });
        //创建代理类
        UserServiceImpl userService1 =(UserServiceImpl) enhancer.create();
        return userService1;
    }
}
