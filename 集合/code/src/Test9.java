import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-06 22:03
 **/
public class Test9 {
    public class Employee{
        private String name;
        private int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public void run(){
        Employee[] employees = new Employee[5];
        employees[0]=new Employee("小明",10);
        employees[1]=new Employee("小黄黄",20);
        employees[2]=new Employee("小黑",20);
        employees[3]=new Employee("小灰",8);
        employees[4]=new Employee("小刘",30);
        // comparing 方法接收一个 Function 函数式接口 ，通过一个 lambda 表达式传入
        //lambda表达式可以换成方法引用Class::functionName
        //等于说comparing传入两个lambda表达式，第一个是传给函数式接口Function{}的，用来返回
        //需要比较的对象，第二个是传给Comparator接口的Compare方法
//        Arrays.sort(employees,Comparator.comparing(Employee::getAge,(o1,o2)->{
//            return Integer.compare(o1,o2);
//        }).thenComparing(Employee::getName,(o1,o2) ->{
//            return o1.length()-o2.length();
//        }));

        //用变体，避免int、double的封装
        Arrays.sort(employees,Comparator.comparingInt(Employee::getAge)
                .thenComparingInt(p -> p.getName().length()));
    }
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        test7.run();
    }
}
