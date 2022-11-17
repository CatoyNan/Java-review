package top.catoy;

import java.util.List;
import java.util.stream.Collectors;

public class Exp5 {
    public static void main(String[] args) {
        Exp1.appleList
                .stream()
                .collect(Collectors.groupingBy((apple) -> {return apple.getWeight();}));
    }
}
