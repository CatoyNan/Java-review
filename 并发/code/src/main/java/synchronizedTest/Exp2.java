package synchronizedTest;

/**
 * @ClassName Exp2
 * @Description TODO
 * @Author admin
 * @Date 2019-07-28 15:43
 * @Version 1.0
 **/
public class Exp2 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "m1 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1 end...");
    }


    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + "m2 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println(Thread.currentThread().getName() + "m2 end...");
    }

    public static void main(String[] args){
        Exp2 exp2 = new Exp2();
        new Thread(()->{
           exp2.m1();
        }).start();

        new Thread(()->{
            exp2.m2();
        }).start();
    }
}
