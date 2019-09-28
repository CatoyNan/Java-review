package src.Singleton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName LazySingleton
 * @Description 懒汉模式，线程不安全。
 * 第一个线程判断lazySingleton为null时，第二线程开始执行，并初始化lazySingleton，执行结束后第一个线程继续执行，这时又会重新初始化一次。
 * @Author admin
 * @Date 2019-09-21 22:09
 * @Version 1.0
 **/
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static LazySingleton init() {
        //线程不安全
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    public static void main(String[] args) {
        Executor exec = Executors.newFixedThreadPool(10);
            exec.execute(()->{
                LazySingleton init = LazySingleton.init();
                System.out.println(init);
            });
            exec.execute(()->{
                LazySingleton init = LazySingleton.init();
            System.out.println(init);
        });
    }
}
