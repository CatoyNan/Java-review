/**
 * @description: 字符串中有多少个java
 * @author: xjn
 * @create: 2019-04-13 20:24
 **/
public class Test17 {
    public static void main(String[] args){
        String s = "234jaajajjajajajajavajavajavajava";
        String findStr = "java";
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(findStr,index))!=-1){
            count++;
            index += findStr.length();
        }
        System.out.println(count);
    }

}
