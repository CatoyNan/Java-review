package top.catoy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Exp1 {
    public static List<Apple> appleList = Arrays.asList(
            new Apple(2, "red"),
            new Apple(1,"yellow"));

    public static void main(String[] args) {
        appleList.stream().forEach((apple) -> {
            System.out.println(apple.getColor());
        });
        Apple.sort(appleList, Comparator.comparingInt(Apple::getWeight).reversed());
        appleList.stream().forEach((apple) -> {
            System.out.println(apple.getColor());
        });

        System.out.println(Apple.applePredicate(Exp1.appleList.get(0),apple -> "red".equals(apple.getColor())));
    }
}

class Apple {
    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public static void sort(List<Apple> list, Comparator<Apple> comparator){
        list.sort(comparator);
    }

    public static boolean applePredicate(Apple apple,Predicate<Apple> predicate){
        return predicate.test(apple);
    }
}