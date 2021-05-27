package src.adapter.demo1;

/**
 * 适配器
 */
public class Adapter implements Target{
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void talk() {
        adaptee.talk();
    }

    @Override
    public void run() {
        adaptee.run();
    }
}
