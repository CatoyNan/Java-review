package catoy.jmm.visibility;

import lombok.extern.slf4j.Slf4j;

/**
 * synchronized 解决可见性问题
 */
@Slf4j
public class VisibilityTest3 {
    private static boolean flag = true;
    private static final Object lock = new Object(); // todo final???
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (!flag) {
                        break;
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            flag = false;
        }
        log.debug("stop");
    }
}
