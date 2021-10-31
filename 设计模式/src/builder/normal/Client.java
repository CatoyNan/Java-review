package src.builder.normal;

import javax.crypto.Mac;
import java.util.Comparator;

public class Client {
    public void client(String[] args) {
        MacBuilder macBuilder = new MacBuilder();
        Director director = new Director();
        director.direct1(macBuilder);
        Computer macComputer1 = macBuilder.getComputer();

        MacBuilder macBuilder2 = new MacBuilder();
        director.direct2(macBuilder);
        Computer macComputer = macBuilder.getComputer();
    }
}
