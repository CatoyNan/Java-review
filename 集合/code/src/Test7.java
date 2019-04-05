import java.util.*;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-04 12:17
 **/
public class Test7 {
    public static void main(String[] args){
//            int z = 0;
//            int x = 2;
//            for(x=2;x<2537;x++)
//            {
//                z=2537/x;
//                System.out.println(x*z);
//                System.out.println("x=:"+x);
//                System.out.println("z=:"+z);
//                if(2537 == x*z){
//                   System.out.println("不是");
//                    break;
//                }
//            }
//            if(x==2357){
//                System.out.println("是素数");
//            }
        double x = 1;
        for(int i=1;i<=15;i++){
            x = x*16;
        }
        double y = x%4731;
        System.out.println(y);
        }
    }
