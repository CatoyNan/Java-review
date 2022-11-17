package src.test;

import src.proxy.UserService;
import src.proxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class Test {
    private int a;

   Integer b = 1;

    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        Object proxy = Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        return method.invoke(target, args);
                    }
                });

        UserService userService = (UserService) proxy;
        userService.addData();
    }
}
