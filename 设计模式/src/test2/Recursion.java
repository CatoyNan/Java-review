package src.test2;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * java 多线程递归遍历文件
 */
public class Recursion {
    public static void main(String[] args) {
        Recursion r = new Recursion();
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.DAYS, new java.util.concurrent.LinkedBlockingQueue<Runnable>());
        executorService.execute(() -> r.recursion("D:\\"));


    }

    public void recursion(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                recursion(f.getAbsolutePath());
            } else {
                System.out.println(f.getAbsolutePath());
            }
        }
    }

}
