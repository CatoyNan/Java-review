package src.Singleton;

/**
 * @ClassName EagerSingleton
 * @Description 饿汉模式
 * @Author admin
 * @Date 2019-09-21 22:10
 * @Version 1.0
 **/
public class EagerSingleton {
    private static final EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton(){

    }

    private static EagerSingleton init(){
        return eagerSingleton;
    }
}
