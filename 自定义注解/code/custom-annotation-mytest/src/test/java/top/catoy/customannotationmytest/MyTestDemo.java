package top.catoy.customannotationmytest;

import top.catoy.customannotationmytest.annotation.MyTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTestDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<CustomAnnotationMytestApplicationTests> aClass = CustomAnnotationMytestApplicationTests.class;
        Method[] methods = aClass.getMethods();

        for(Method m : methods) {
            if (m.isAnnotationPresent(MyTest.class)) {
                m.invoke(aClass.newInstance());
            }
        }
    }
}
