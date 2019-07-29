# Java并发

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

- 同一个类中的两个方法—>可以

##### 1.42情况二

- 子类调用父类中的方法—>可以

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

