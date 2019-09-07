import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Exp4
 * @Description 创建节点，使用异步接口
 * @Author admin
 * @Date 2019-08-30 16:26
 * @Version 1.0
 **/
public class Exp4 implements Watcher {
    private static CountDownLatch cd = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 5000, new Exp4());

        cd.await();
        zookeeper.create("/node5",
                "data1".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),
                "context");

        zookeeper.create("/node6",
                "data2".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(),
                "context");

        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:" + watchedEvent);
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            cd.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback {
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("create path result: [" + i + ", " + s + ", " + o + ", real path name: " + s1 + "]");
    }
}