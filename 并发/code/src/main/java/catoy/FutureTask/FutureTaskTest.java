package catoy.FutureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName FutureTaskTest
 * @Description TODO
 * @Author admin
 * @Date 2019-11-11 21:03
 * @Version 1.0
 **/
public class FutureTaskTest {
    static class MyException extends Exception {

    }


    public static void main(String[] args) {
        FutureTask<Integer> task1 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                throw new MyException();
            }
        });

        new Thread(task1).start();

        try {
            task1.get();
        } catch (InterruptedException e) {
            System.out.println("1");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("2");
            e.printStackTrace();
        }


    }
}
