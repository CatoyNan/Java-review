package catoy.base;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个线程优雅的终止另一个线程（两阶段终止模式）
 */
@Slf4j
public class Interrupt3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    //料理后事
                    log.debug("打断后的逻辑");
                    break;
                }
                try {
                    log.debug("执行监控机制");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 抛InterruptedException异常打断标记会被清除，重新设置打断标记
                    Thread.currentThread().interrupt();
                }
            }
        },"t1");
        t1.start();
        //打断阻塞中的
        Thread.sleep(4000);

        log.debug("打断");
        t1.interrupt();
    }

}
