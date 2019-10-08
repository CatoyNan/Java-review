package catoy.synchronizedTest;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Exp4
 * @Description 错误代码
 * @Author admin
 * @Date 2019-07-30 09:24
 * @Version 1.0
 **/
public class Exp4 {
    private boolean flag = true;
    private double num = 1;

    public void fun(){
        synchronized (Exp4.class){
            while (flag){
            }
            num = 100;
            System.out.println(num);
        }
    }

    public static void main(String[] args){
        Exp4 exp4 = new Exp4();
        new Thread(()->{
            exp4.fun();
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("123");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("345");
        synchronized (Exp4.class){
            System.out.println("111");
            exp4.flag = false;
            System.out.println(exp4.flag);
        }
    }
}
