# String、StringBuffer、StringBuider

## String(不可变字符串)

- 这里s1==s3,因为指向同一个值

![1554965538596](http://ww4.sinaimg.cn/large/006tNc79ly1g4zlks56wlj30q90bhgml.jpg)

- s1==s2//false;       s1.equals(s2)//true

  ![1554968117074](http://ww1.sinaimg.cn/large/006tNc79ly1g4zlkt3gqej30z10bhgmk.jpg)

- ![1555160115796](http://ww1.sinaimg.cn/large/006tNc79ly1g4zlksmnntj30qw0d4q3z.jpg)

## StringBuffer

- 线程安全,内部有所机制，详见并发目录
- 可变的字符串序列

#### 练习

```java
public class Test18 {
    public static void main(String[] args){
        String s = "Mircosoft";
        char[] a = {'a','b','c'};
        StringBuffer sb1 = new StringBuffer(s);
        sb1.append('/').append('3');
        System.out.println(sb1);//Mircosoft/3
        StringBuffer sb2 = new StringBuffer("数字");
        for(int i=0;i<9;i++){
            sb2.append(i);
        }
        System.out.println(sb2);//数字012345678
        sb2.delete(8,sb2.length());
        System.out.println(sb2);//数字012345
        sb2.insert(0,a);
        System.out.println(sb2);//abc数字012345
        System.out.println(sb2.reverse());//543210字数cba
    }
}
```



## StringBuider

- 可变的字符串序列

- 前身是StringBuffer

- 线程不安全，详见并发目录

- 当需要多个小段的字符串构建一个字符串时可以用

  ```java
   StringBuilder builder = new StringBuilder();
   builder.append("小");
   builder.append('明');
   builder.append(1);
   System.out.println(builder.toString());
  ```

## 



## 替换字符串中的空格

```java
/**
 * @ClassName RemoveSpace
 * @Description 去除字符串中的空格
 * @Author admin
 * @Date 2019-09-04 16:39
 * @Version 1.0
 **/
public class RemoveSpace {
    public static void main(String[] args){
        String str = "  123  123";
        char[] c = {160};
        str =  str + String.valueOf(c) + "  123  ";
        System.out.println("***|" + str + "|***");
        //方式一:只能去除首位空格
        String newStr1 = str.trim();
        System.out.println("***|" + newStr1 + "|***");

        //方式二:replace
        String newStr2 = str.replace(" ","");
        System.out.println("***|" + newStr2 + "|***");


        //方法三:replaceAll,可以去除不间断空格&nbsp;
        String newStr3 = str.replaceAll(String.valueOf((char)160),"").replaceAll(" ","");
        System.out.println("***|" + newStr3 + "|***");

        //方法四:replaceAll,正则匹配
        String newStr4 = str.replaceAll("\\s","");
        System.out.println("***|" + newStr4 + "|***");

    }
***|  123  123   123  |***
***|123  123   123|***
***|123123 123|***
***|123123123|***
***|123123 123|***

```

