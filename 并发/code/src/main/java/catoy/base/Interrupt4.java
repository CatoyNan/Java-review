package catoy.base;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个线程优雅的终止另一个线程（两阶段终止模式）,使用volatile优化
 */
@Slf4j
public class Interrupt4 {
    private volatile static boolean interrupt = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (interrupt) {
                    //料理后事
                    log.debug("打断后的逻辑");
                    break;
                }
                try {
                    log.debug("执行监控机制");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        t1.start();
        //打断阻塞中的
        Thread.sleep(4000);
        log.debug("打断");
        interrupt = true;
    }
}
