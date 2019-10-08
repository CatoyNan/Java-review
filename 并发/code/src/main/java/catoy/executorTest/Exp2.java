package catoy.executorTest;

import java.util.concurrent.Executor;

/**
 * @ClassName Exp2
 * @Description 自定义executor,任务的提交与执行解耦
 * @Author admin
 * @Date 2019-08-13 21:47
 * @Version 1.0
 **/
public class Exp2 {
    private static final int NTHREADS = 1;
    private static Executor executor1 = new MyExecutor1();
    private static Executor executor2 = new MyExecutor2();

    public static void main(String[] args){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("1" + "name:" + Thread.currentThread().getName());
            }
        };
//        executor1.execute(task);
        executor2.execute(task);
        executor2.execute(task);
    }
}

/**
 * 每一个请求都创建新线程
 */
class MyExecutor1 implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}

/**
 * 同步方式执行每个任务(主线程串行执行)
 */
class MyExecutor2 implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}