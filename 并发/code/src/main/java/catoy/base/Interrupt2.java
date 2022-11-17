package catoy.base;

import lombok.extern.slf4j.Slf4j;

/**
 * 打断正常运行的线程
 */
@Slf4j
public class Interrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean flag = Thread.currentThread().isInterrupted();
                if (flag) {
                    log.debug("线程被打断");
                    break;
                }
            }
        },"t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }

//    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//
//            }
//            log.debug("线程被打断");
//
//        },"t1");
//        t1.start();
//        Thread.sleep(1000);
//        log.debug("interrupt");
//        t1.interrupt();
//    }
}
