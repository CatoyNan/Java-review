package catoy.commonUnsafe;

import java.util.concurrent.*;

/**
 * @ClassName StringBuilderTest
 * @Description TODO
 * @Author admin
 * @Date 2019-10-04 15:21
 * @Version 1.0
 **/
public class StringBuilderTest {
    //请求总数
    private static final int clientTotal = 5000;
    //同时并发执行的线程数
    private static final int threadTotal = 200;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(500);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executor.execute(new Thread() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        sb.append("1");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        System.out.println(sb.length());
    }
}
