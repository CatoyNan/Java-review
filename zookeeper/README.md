## 一、创建会话

### 创建一个基本的zookeeper会话实例

```shell
#启动服务
sudo ./zkServer.sh start  ../conf/zoo-1.cfg
```

```java
/**
 * @ClassName Exp1
 * @Description 创建一个基本的zookeeper会话实例
 * @Author catoy
 * @Date 2019-08-30 13:53
 * @Version 1.0
 **/
public class Exp1 implements Watcher {
    private static CountDownLatch cd = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181",5000,new Exp1());
        System.out.println(zookeeper.getState());
        try {
            cd.await();
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
```

![image-20190830143109778](http://ww4.sinaimg.cn/large/006y8mN6ly1g6howzizf6j314q0pwaed.jpg)

### 创建一个基本的zookeeper对象，复用sessionId和session passwd

```java
/**
 * @ClassName Exp2
 * @Description 创建一个基本的zookeeper对象，复用sessionId和session passwd
 * @Author catoy
 * @Date 2019-08-30 14:49
 * @Version 1.0
 **/
public class Exp2 implements Watcher {
    private static CountDownLatch cd = new CountDownLatch(1);
    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181",5000,new Exp2());
        cd.await();
        long sessionId = zk.getSessionId();
        byte[] passwd = zk.getSessionPasswd();
      	//wrong sessionId、passwd
        zk = new ZooKeeper("localhost:2181",5000,new Exp2(),1L,"haha".getBytes());
        zk = new ZooKeeper("localhost:2181",5000,new Exp2(),sessionId,passwd);
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
```

###  二、创建节点

### API 创建节点，使用同步（sync）接口

```java
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
```

### API 创建节点，使用异步接口

```java
/**
 * @ClassName Exp4
 * @Description 创建节点，使用异步接口
 * @Author admin
 * @Date 2019-08-30 16:26
 * @Version 1.0
 **/
public class Exp4 implements Watcher{
    private static CountDownLatch cd = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181",5000,new Exp4());

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
        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()){
            cd.countDown();
        }
    }
}
class IStringCallback implements AsyncCallback.StringCallback{
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("create path result: [" + i + ", "+ s + ", " + o + ", real path name: " + s1 + "]");
    }
}
//create path result: [0, /node5, context, real path name: /node5]
//create path result: [0, /node6, context, real path name: /node60000000010]
```