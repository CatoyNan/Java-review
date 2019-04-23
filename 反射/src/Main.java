import entity.User;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {
    public String print(Object[] a){
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

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
        System.out.println(Arrays.toString(field));
        //[public java.lang.String entity.User.sex]
        System.out.println(Arrays.toString(field1));
        //[private java.lang.String entity.User.name,
        // private java.util.Date entity.User.date,
        // public java.lang.String entity.User.sex]
    }
}
