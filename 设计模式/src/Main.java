import proxy.MyInvocationHandler;
import proxy.UserService;
import proxy.UserServiceImpl;
import proxy.UserServiceProx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
//        //静态代理
//        //被代理对象
//        UserService target = new UserServiceImpl();
//        //代理类
//        UserService useService  = new UserServiceProx(target);
//        useService.addData();

        //动态代理
        UserService target = new UserServiceImpl();
        //第一个参数是指定代理类的类加载器（我们传入当前测试类的类加载器） 
        //第二个参数是代理类需要实现的接口（我们传入被代理类实现的接口，这样生成的代理类和被代理类就实现了相同的接口） 
        //第三个参数是invocation handler，用来处理方法的调用。这里传入我们自己实现的handler
        UserService userServiceProx = (UserService) Proxy.newProxyInstance(Main.class.getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("准备插入数据");
                        Object returnvalue = method.invoke(target,args);
                        System.out.println("数据插入成功");
                        return returnvalue;
                    }
                });

        userServiceProx.addData();
    }
}
