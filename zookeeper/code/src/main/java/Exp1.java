import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;


/**
 * @ClassName Exp1
 * @Description 创建一个基本的zookeeper会话实例
 * @Author admin
 * @Date 2019-08-30 13:53
 * @Version 1.0
 **/
public class Exp1 implements Watcher {
    private static CountDownLatch cd = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 5000, new Exp1());
        System.out.println(zookeeper.getState());
        try {
            cd.await();
        } catch (InterruptedException e) {

        }
        System.out.println("zookeeper session estableshed");
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            cd.countDown();
        }
    }
}
