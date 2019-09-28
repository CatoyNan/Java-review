package src.Singleton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName LazySingletonThreadSafe
 * @Description 懒汉模式线程安全, 加锁保证线程的同步访问，volatile避免重排序，双重验证提升性能(第一次初始化的时候才回去加锁)
 * @Author admin
 * @Date 2019-09-21 22:33
 * @Version 1.0
 **/
public class LazySingletonThreadSafe {
    private volatile static LazySingletonThreadSafe lazySingletonThreadSafe = null;

    private LazySingletonThreadSafe() {

    }

    public static LazySingletonThreadSafe init() {
        if (lazySingletonThreadSafe == null) {//等于null的时候才去加锁
            synchronized (LazySingletonThreadSafe.class) {
                if (lazySingletonThreadSafe == null) {
                    lazySingletonThreadSafe = new LazySingletonThreadSafe();
                }
            }
        }
        return lazySingletonThreadSafe;
    }

    public static void main(String[] args) {
        Executor exec = Executors.newFixedThreadPool(10);
        exec.execute(()->{
            LazySingletonThreadSafe init = LazySingletonThreadSafe.init();
            System.out.println(init);
        });
        exec.execute(()->{
            LazySingletonThreadSafe init = LazySingletonThreadSafe.init();
            System.out.println(init);
        });
    }
}
