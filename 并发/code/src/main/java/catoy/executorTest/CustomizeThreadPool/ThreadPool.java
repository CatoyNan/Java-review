package catoy.executorTest.CustomizeThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
@Slf4j
public class ThreadPool {
    //阻塞队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet();

    //核心线程数
    private int coreSize;

    //超时时间
    private long timeout;

    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    private TimeUnit timeUnit;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task) {
        //当任务数没有高过coreSize时直接交给wokers,否则放入任务队列
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker{},{}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
//                taskQuere.put(task);
                //1.死等
                //2.超时等待
                //3.放弃任务
                //4.抛出异常
                //5.调用者自己执行任务
                taskQueue.tryPut(task, rejectPolicy);
            }
        }
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //1.task不为空，执行任务
            //2.当task执行完毕，从任务队列中获取任务
            while (task != null || (task = taskQueue.take(timeout, timeUnit)) != null) {
                try {
                    log.debug("正在执行....{}", task);
                    task.run();
                } catch (Exception e) {

                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker 被移除{}", this);
                workers.remove(this);
            }
        }
    }
}

/**
 * 拒绝策略
 */
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
