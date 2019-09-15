package ConcurrencyTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName 代码并发模拟
 * @Description TODO
 * @Author admin
 * @Date 2019-09-14 18:35
 * @Version 1.0
 **/
public class ConcurrencyTest {
    //请求总数
    private static final int clientTotal = 5000;
    //同时并发执行的线程数
    private static final int threadTotal = 200;

    //    private static AtomicInteger count = new AtomicInteger(0);
    private static int count = 0;

    public static void main(String[] ags) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            countDownLatch.countDown();
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(count);

    }


    public static void add() {
//        count.getAndIncrement();
        count++;
    }

}
