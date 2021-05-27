package src.adapter.demo1;

public class Client {
    public static void main(String[] args) {
        Target target = new MyTarget();
        target.run();
        target.talk();

        Target adapter = new Adapter(new MyAdaptee());
        adapter.run();
        adapter.talk();
    }
}
