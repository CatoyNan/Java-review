package catoy.jmm.orderliness;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * JMM指令重排序问题展示
 */
public class Orderliness1 {

    class ConcurrencyTest {
        Object ready;

        public void f1() {
            ready = new Object();
        }

        public void f3() {
            ready = null;
        }

        public void f2() {
            if (ready != null) {
                System.out.println(ready.getClass());
            } else {
                System.out.println("null");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> list = new ArrayList();
        ConcurrencyTest concurrencyTest = new Orderliness1().new ConcurrencyTest();
        for (int i = 0; i < 1000000; i++) {
            executorService.execute(() -> {
                Thread t1 = new Thread(() -> {
                    concurrencyTest.f1();
                }, "t1");
                t1.start();

                Thread t2 = new Thread(() -> {
                    concurrencyTest.f2();
                }, "t2");
                t2.start();

                Thread t3 = new Thread(() -> {
                    concurrencyTest.f3();
                }, "t3");
                t3.start();
            });
        }
    }
}
