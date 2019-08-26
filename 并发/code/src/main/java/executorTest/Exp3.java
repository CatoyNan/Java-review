package executorTest;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.*;

/**
 * @ClassName Exp3
 * @Description ExecutorService 增加了一些用于生命周期管理的方法
 *              isShutdown();
 *              isTerminated();
 *              awaitTermination(1000, TimeUnit.SECONDS);
 * @Author admin
 * @Date 2019-08-16 11:51
 * @Version 1.0
 **/
public class Exp3 {
    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        Exp3 exp3 = new Exp3();
        exp3.start();
    }

    public void start(){
        while (!es.isShutdown()){
            Runnable task = ()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "hello world");
                stop();
            };
            try{
                es.execute(task);
            }catch (RejectedExecutionException e){
                e.printStackTrace();
            }

        }
    }

    public void stop(){
        System.out.println("shutdown++++++++++++++++++");
        es.shutdownNow();
    }
}
