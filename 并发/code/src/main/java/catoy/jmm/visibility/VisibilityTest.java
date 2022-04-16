package catoy.jmm.visibility;

import lombok.extern.slf4j.Slf4j;

/**
 * JMM可见性问题示例
 */
@Slf4j
public class VisibilityTest {
    private static boolean flag = true;
    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {

            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        log.debug("stop");
    }
}
