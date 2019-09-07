import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Exp3
 * @Description API 创建节点，使用同步（sync）接口
 * @Author catoy
 * @Date 2019-08-30 16:13
 * @Version 1.0
 **/
public class Exp3 implements Watcher {
    private static CountDownLatch cd = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181",5000,new Exp3());
        System.out.println(zookeeper.getState());
        try {
            cd.await();
            String path1 = zookeeper.create("/node1",
                    "data1".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);
            System.out.println("Success create znode:" + path1);

            String path2 = zookeeper.create("/node2",
                    "data2".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("Success create znode:" + path2);
        } catch (InterruptedException e){

        }
        System.out.println("zookeeper session estableshed");
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            cd.countDown();
        }
    }
}
