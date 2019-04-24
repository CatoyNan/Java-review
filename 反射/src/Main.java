import entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception{
//        User user = new User();
//
//       Class clazz1 = Class.forName("entity.User");
//       Class clazz2 = user.getClass();
//       Class clazz3 = User.class;
//
//       System.out.println(clazz1);
//       System.out.println(clazz2);
//       System.out.println(clazz3);

       //获取属性信息
        Class userClass = Class.forName("entity.User");
        //返回public修饰的属性
        Field[] field = userClass.getFields();
        //返回所有属性
        Field[] field1 = userClass.getDeclaredFields();
//        System.out.println(Arrays.toString(field));
        //[public java.lang.String entity.User.sex]
//        System.out.println(Arrays.toString(field1));
        //[private java.lang.String entity.User.name,
        // private java.util.Date entity.User.date,
        // public java.lang.String entity.User.sex]

        //获取方法信息
        //获取public修饰方法，包含继承的方法
        Method[] methods1 = userClass.getMethods();
        //获取所有方法,不包含继承的方法
        Method[] methods2 = userClass.getDeclaredMethods();
        //根据方法名获取方法
        Method method = userClass.getDeclaredMethod("run",String.class);
//        System.out.println(Arrays.toString(methods1));
//        System.out.println(Arrays.toString(methods2));
//        System.out.println(method.toString());

        //获取构造器
        //获取所有public修饰构造器,不包括父类的构造器（不能被继承）
        Constructor[] constructors1 = userClass.getConstructors();
        //获取所有public修饰的构造器，不包括父类的构造器（不能被继承）
        Constructor[] constructors2 = userClass.getDeclaredConstructors();
        System.out.println(Arrays.toString(constructors1));
        System.out.println(Arrays.toString(constructors2));
    }
}
