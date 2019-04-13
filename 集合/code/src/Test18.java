/**
 * @description:
 * @author: xjn
 * @create: 2019-04-13 21:00
 **/
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
