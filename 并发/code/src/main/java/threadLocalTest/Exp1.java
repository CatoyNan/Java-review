package threadLocalTest;

/**
 * @ClassName Exp1
 * @Description ThreadLocal类的使用
 * @Author admin
 * @Date 2019-09-12 13:57
 * @Version 1.0
 **/
public class Exp1 {
    //用一个静态的变量来记录ThreadLocal对象,方法在任何地方法直接调用
    public static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            //在线程的任意地方设置变量
            local.set("你");
            method();
        }).start();
        new Thread(() -> {
            local.set("好");
            method();
        }).start();
    }

    public static void method() {
        //可以在当前线程的任意地方获取变量
        System.out.println(local.get());
    }
}
