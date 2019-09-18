package synchronizedTest;


import java.util.concurrent.TimeUnit;

/**
 * @ClassName Exp3
 * @Description 脏读例子
 * @Author admin
 * @Date 2019-07-28 16:27
 * @Version 1.0
 **/
public class Exp3 {
    private String name;
    private long balance = 0;

    public synchronized void set(String name,long balance){
        System.out.println("存数据");
        this.name = name;
        try {
            Thread.sleep(2000);//数据操作存在的时间差
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized long getBalance(){
        System.out.println("读取数据");
        return this.balance;
    }

    public static void main(String[] args){
        Exp3 exp3 = new Exp3();
        new Thread(()->{
            exp3.set("小明",1000);
        }).start();

        new Thread(()->{
            System.out.println(exp3.getBalance());
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println(exp3.getBalance());
        }).start();
    }
}
