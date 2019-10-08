package catoy.threadLocalTest;

/**
 * @ClassName ThreadLocalRequestHandle
 * @Description 利用threadLocal 为每个线程存储各自的变量
 * @Author admin
 * @Date 2019-10-03 11:21
 * @Version 1.0
 **/
public class ThreadLocalRequestHandle {
    private final static ThreadLocal threadLocal = new ThreadLocal();

    public static void add(String s){
        threadLocal.set(s);
    }

    public static String get(){
        return (String)threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }

}
