package src.adapter.demo1;

public class MyTarget implements Target {
    @Override
    public void talk() {
        System.out.println("MyTarget talk");
    }

    @Override
    public void run() {
        System.out.println("MyTarget run");
    }
}
