# Java并发

### 一、cpu多级缓存与缓存一致性(MESI)

#### 1.1cache

![image-20190913184025742](http://ww1.sinaimg.cn/large/006y8mN6gy1g6y2sq9n9jj30t80kcabl.jpg)

由于cpu频率比内存大好几个数量级，为了不让cpu一直处于等待状态，在内存和cpu之间加了缓存。cache中存储了cpu刚用过的数据和循环使用的数据，提高了数据读取的速度。

#### 1.2cache带来的问题

- 乱序执行优化

- 缓存一致性问题。若CPU1和CPU2同时读取了主存中变量i的值为1，并将他们保存在各自的cache中。在CPU1中i执行了自加操作值变为2，并写入主存中。而CPU2也执行同样的操作。此时主存中i的终结果是2，而不是预期的3。

#### 1.3缓存一致性

为了解决cache带来的数据一致性问题，cpu根据一些协议来读写数据如：MESI

| 状态         | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| M(Modified)  | 这行数据有效，数据被修改了，和内存中的数据不一致，数据只存在于本Cache中。 |
| E(Exclusive) | 这行数据有效，数据和内存中的数据一致，数据只存在于本Cache中。 |
| S(Shared)    | 这行数据有效，数据和内存中的数据一致，数据存在于很多Cache中。 |
| I(Invalid)   | 这行数据无效                                                 |

M状态和I状态：

![image-20190913190934298](http://ww2.sinaimg.cn/large/006y8mN6gy1g6y3mxbfgfj30pg0c0q6a.jpg)

E状态：

![image-20190913190808286](http://ww1.sinaimg.cn/large/006y8mN6gy1g6y3lfbw7uj30oo0b6whd.jpg)



S状态：

![image-20190913190840791](http://ww2.sinaimg.cn/large/006y8mN6gy1g6y3lzzykyj30oe0c8tc5.jpg)

### 二、java内存模型

Java内存模型是基于cpu缓存模型建立的，java内存模型并不是真实存的，而是一组数据读取的规则。

![image-20190914140844024](http://ww2.sinaimg.cn/large/006y8mN6ly1g6z0k96m5sj30zu0kwqje.jpg)

- 主内存：主要存储的是Java实例对象，所有线程创建的实例对象都存放在主内存中，不管该实例对象是成员变量还是方法中的本地变量(也称局部变量)，当然也包括了共享的类信息、常量、静态变量。由于是共享数据区域，多条线程对同一个变量进行访问可能会发现线程安全问题。

- 工作内存：主要存储当前方法的所有本地变量信息(工作内存中存储着主内存中的变量副本拷贝)，每个线程只能访问自己的工作内存，即线程中的本地变量对其它线程是不可见的，就算是两个线程执行的是同一段代码，它们也会各自在自己的工作内存中创建属于当前线程的本地变量，当然也包括了字节码行号指示器、相关Native方法的信息。注意由于工作内存是每个线程的私有数据，线程间无法相互访问工作内存，因此存储在工作内存的数据不存在线程安全问题。

- 存放在主内存上的对象可以被所有持有对这个对象引用的线程访问。当一个线程可以访问一个对象时，它也可以访问这个对象的成员变量。<font color='red'>如果两个线程同时调用同一个对象上的同一个方法，它们将会都访问这个对象的成员变量，但是每一个线程都拥有这个成员变量的私有拷贝。</font>

- <font color='red'>所有对变量的操作无法在主内存中完成，需要将变量拷贝至工作内存中进行操作。</font>

- 弄清楚主内存和工作内存后，了解一下主内存与工作内存的数据存储类型以及操作方式，根据虚拟机规范，对于一个实例对象中的成员方法而言，<font color="red">如果方法中包含本地变量是基本数据类型，将直接存储在工作内存的帧栈结构中，但倘若本地变量是引用类型，那么该变量的引用会存储在功能内存的帧栈中，而对象实例将存储在主内存(共享数据区域，堆)中。但对于实例对象的成员变量，不管它是基本数据类型还是引用类型，都会被存储到堆区。</font>至于static变量以及类本身相关信息将会存储在主内存中。需要注意的是，在主内存中的实例对象可以被多线程共享，倘若两个线程同时调用了同一个对象的同一个方法，那么两条线程会将要操作的数据拷贝一份到自己的工作内存中，执行完成操作后才刷新到主内存，简单示意图如下所示： 

  ![image-20190914143201983](http://ww2.sinaimg.cn/large/006y8mN6ly1g6z18h2o87j30mq0uejv0.jpg)

### 三、atomic包中的CAS(乐观锁)

CAS是英文单词Compare And Swap的缩写，翻译过来就是比较并替换。atomic中类的读写会依赖unsafe类中的方法。方法中有3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。<font color='red'>更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。</font>

atomic下的类只能做到单个变量的原子性，若需要同时保持多个变量的原子操作只能用Sychonized。

![image-20190914195303786](http://ww2.sinaimg.cn/large/006y8mN6gy1g6zaiopoz8j30wu0oggog.jpg)

### 一、synchronized

#### 1.1使用方法

```java
public class exp1 {
    private int count = 10;
    Object suo = new Object();
    public void m1(){
        synchronized (suo){//synchronized锁定object对象
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }

    public void m2(){
        synchronized (this){//synchronized锁定自身
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }

    public synchronized void m3(){//synchronized锁定自身
        count--;
        System.out.println(Thread.currentThread().getName() + "count" + count);
    }
}

```

#### 1.2一个synchronized 代码块是原子操作不可分

#### 1.3同步方法和非同方法是否可以同时调用———可以

#### 1.4发生异常，锁默认会被释放，除非try catch

加了synchronized的方法和不加synchronized互不影响。只有加有synchronized关键字的方法才需要去申请锁。

```java
public class Exp2 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "m1 start....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1 end...");
    }


    public void m2(){
        System.out.println(Thread.currentThread().getName() + "m2 start.....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println(Thread.currentThread().getName() + "m2 end");
    }

    public static void main(String[] args){
        Exp2 exp2 = new Exp2();
        new Thread(()->{
           exp2.m1();
        }).start();
        
        new Thread(()->{
            exp2.m2();
        }).start();
    }
}
Thread-0m1 start...
Thread-1m2 start...
Thread-1m2 end...
Thread-0m1 end...
  
========================================
public class Exp2 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "m1 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1 end...");
    }


    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + "m2 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println(Thread.currentThread().getName() + "m2 end...");
    }

    public static void main(String[] args){
        Exp2 exp2 = new Exp2();
        new Thread(()->{
           exp2.m1();
        }).start();

        new Thread(()->{
            exp2.m2();
        }).start();
    }
}
Thread-0m1 start...
Thread-0m1 end...
Thread-1m2 start...
Thread-1m2 end...
```

#### 1.4同步方法是否可以调用同步方法

##### 1.41情况一

- 同一个类中的两个同步方法—>可以

##### 1.42情况二

- 子类同步方法调用父类中的同步方法—>可以

### 二、脏度数据

- 对写加锁，对读不加锁,在读的时候会读到写还没有完成的数据

```java
public class Exp3 {
    private String name;
    private long balance = 0;

    public synchronized void set(String name,long balance){
        System.out.println("存数据");
        this.name = name;
        try {
            Thread.sleep(2000);//数据操作存在的时间差
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized long getBalance(){
        System.out.println("读取数据");
        return this.balance;
    }

    public static void main(String[] args){
        Exp3 exp3 = new Exp3();
        new Thread(()->{
            exp3.set("小明",1000);
        }).start();

        new Thread(()->{
            System.out.println(exp3.getBalance());
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println(exp3.getBalance());
        }).start();
    }
}
存数据
读取数据
0
读取数据
1000
=================================================
public class Exp3 {
    private String name;
    private long balance = 0;

    public synchronized void set(String name,long balance){
        System.out.println("存数据");
        this.name = name;
        try {
            Thread.sleep(2000);//数据操作存在的时间差
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized long getBalance(){
        System.out.println("读取数据");
        return this.balance;
    }

    public static void main(String[] args){
        Exp3 exp3 = new Exp3();
        new Thread(()->{
            exp3.set("小明",1000);
        }).start();

        new Thread(()->{
            System.out.println(exp3.getBalance());
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println(exp3.getBalance());
        }).start();
    }
}
存数据
读取数据
1000
读取数据
1000
```

