package top.catoy;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exp4 {
    public static void main(String[] args) {
        Stream<int[]> pythagoreanTriples = IntStream.
                rangeClosed(1, 1000)
                .boxed()
                .flatMap(a -> IntStream
                        .rangeClosed(a, 1000)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .boxed()
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.forEach(ints -> System.out.println(ints[0] + "," + ints[1] + "," + ints[2]));
    }
}
