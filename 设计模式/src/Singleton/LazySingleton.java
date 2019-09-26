package src.Singleton;

/**
 * @ClassName LazySingleton
 * @Description 懒汉模式，线程不安全
 * @Author admin
 * @Date 2019-09-21 22:09
 * @Version 1.0
 **/
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton() {

    }

    private LazySingleton init() {
        //线程不安全
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
