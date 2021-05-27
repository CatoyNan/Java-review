package src.adapter.demo1;

public class MyAdaptee implements Adaptee{
    @Override
    public void talk() {
        System.out.println("Adaptee talk");
    }

    @Override
    public void run() {
        System.out.println("Adaptee run");
    }
}
