/**
 * @description:
 * @author: xjn
 * @create: 2019-04-02 13:25
 **/

/**
 * 1.声明函数式接口，接口中声明方法
 * 2.声明类，类中编写方法是用接口作为参数
 * 3.实现对字符串的大小写转换盒截取操作
 */
public class Test3 {
    @FunctionalInterface
    public interface MyFunction{
        String getValues(String str);
    }

    public String strHandler(String str,MyFunction myFunction){
        return myFunction.getValues(str);
    }

    public static void main(String[] args){
       Test3 test3 = new Test3();
       String strToLower = test3.strHandler("ABCDefg",str->{return str.toLowerCase();});//abcdefg
       String strSub = test3.strHandler(strToLower,str -> {return str.substring(1,2);});//b
       System.out.println(strSub);
    }
}
