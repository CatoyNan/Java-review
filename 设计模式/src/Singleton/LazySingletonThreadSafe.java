package src.Singleton;

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

    public LazySingletonThreadSafe init() {
        if (lazySingletonThreadSafe == null) {//等于null的时候才去加锁
            synchronized (LazySingletonThreadSafe.lazySingletonThreadSafe) {
                if (lazySingletonThreadSafe == null) {
                    lazySingletonThreadSafe = new LazySingletonThreadSafe();
                }
            }
        }
        return lazySingletonThreadSafe;
    }
}
