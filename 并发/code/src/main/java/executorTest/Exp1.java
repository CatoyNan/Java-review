package executorTest;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Exp1
 * @Description 线程池
 * @Author admin
 * @Date 2019-08-13 19:49
 * @Version 1.0
 **/
public class Exp1 {
    private static final  int NTHREADS = 100;
//    固定长度线程池
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

//    可缓存线程池,可以收回空闲的线程，没有数量限制
    private static final Executor exec2 = Executors.newCachedThreadPool();

//    只创建单个线程，若该线程出现异常将会创建另一个线程，特点串行执行
    private static final Executor exec3 = Executors.newSingleThreadExecutor();

//    固定长度线程池，定时执行任务
    private  static final Executor exec4 = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws IOException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
       while(true) {
           exec2.execute(task);
       }
    }
}
