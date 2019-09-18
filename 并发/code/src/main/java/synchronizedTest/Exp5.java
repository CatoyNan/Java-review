package synchronizedTest;

import java.util.concurrent.atomic.AtomicLong;

/**
 * volatile 可以实现变量的可见性，单不能实现操作的原子性
 */
public class Exp5 {
//    private volatile static AtomicLong count = new AtomicLong(0);

        private volatile static long count = 0;
    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
//            count.getAndIncrement();
            count++;
        }
    }

    public static long calc() throws InterruptedException {
        final Exp5 test = new Exp5();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Exp5.calc());
    }
}

