package top.catoy.groupBy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class Exp1 {

      private  List<Student> students = Arrays.asList(
                new Student(11, "A"),
                new Student(12, "B"),
                new Student(14, "A"),
                new Student(5, "A"),
                new Student(6, "B"),
                new Student(4, "A"),
                new Student(3, "A"),
                new Student(7, "B"),
                new Student(8, "B"),
                new Student(0, "B"),
                new Student(10, "A")
        );


    @Test
    public void test() {
        Comparator<Student> comparing = Comparator.comparingInt(Student::getAge);
        TreeMap<String, Set<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getName,
                new TreeMap<String,Student>((x,y)->{return 1;})
                , Collectors.toSet()));
        System.out.println(collect);

    }
}

@Data
@AllArgsConstructor
class Student {
    int age;
    String name;

}
