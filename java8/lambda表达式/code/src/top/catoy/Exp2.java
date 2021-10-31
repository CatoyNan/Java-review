package top.catoy;

import java.util.List;
import java.util.stream.Collectors;

public class Exp2 {
    public static void main(String[] args) {
        List<Apple> appleArrayList = Exp1.appleList;
        appleArrayList.stream()
                      .filter(apple -> {
                          System.out.println(apple.getColor());
                          return apple.getWeight() > 1;
                      })
                      .collect(Collectors.toList());
    }
}
