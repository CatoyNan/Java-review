package catoy.base;

import lombok.extern.slf4j.Slf4j;

/**
 * 打断阻塞中的线程 sleep、wait、join,打断标记会被清除
 */
@Slf4j
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.debug("打断标记{}", Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }
}
