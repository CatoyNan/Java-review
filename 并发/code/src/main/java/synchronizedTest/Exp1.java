package synchronizedTest;

/**
 * @ClassName Exp1
 * @Description synchronized加锁方式
 * @Author admin
 * @Date 2019-07-27 19:11
 * @Version 1.0
 **/
public class Exp1 {
    private int count = 10;
    Object suo = new Object();
    public void m1(){
        synchronized (suo){//synchronized已object对象为锁
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }

    public void m2(){
        synchronized (this){//synchronized已自身为锁
            count--;
            System.out.println(Thread.currentThread().getName() + "count" + count);
        }
    }

    public synchronized void m3(){//synchronized已自身为锁
        count--;
        System.out.println(Thread.currentThread().getName() + "count" + count);
    }
}


