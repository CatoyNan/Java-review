import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Exp2
 * @Description 创建一个基本的zookeeper对象，复用sessionId和session passwd，由于zkClient的重连机制，第一个zk和第三个zk会交替重连
 * @Author admin
 * @Date 2019-08-30 14:49
 * @Version 1.0
 **/
public class Exp2 implements Watcher {
    private static CountDownLatch cd = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, new Exp2());
        cd.await();
        long sessionId = zk.getSessionId();
        byte[] passwd = zk.getSessionPasswd();
//        zk = new ZooKeeper("localhost:2181", 5000, new Exp2(), 1L, "haha".getBytes());
        zk = new ZooKeeper("localhost:2181", 5000, new Exp2(), sessionId, passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("cd countDown");
            cd.countDown();
        }
    }
}
