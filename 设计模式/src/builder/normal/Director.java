package src.builder.normal;

public class Director {
    public void direct1(Builder builder) {
        builder.setCpu();
        builder.setDriver();
        builder.setRam();
    }

    public void direct2(Builder builder) {
        builder.setCpu();
        builder.setDriver();
    }
}
