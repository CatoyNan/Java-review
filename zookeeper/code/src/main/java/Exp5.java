import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Exp5
 * @Description 查
 * @Author admin
 * @Date 2019-09-20 17:21
 * @Version 1.0
 **/
public class Exp5 implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args){

    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            if(){

            }
        }
    }
}
