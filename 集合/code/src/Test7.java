import java.util.*;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-04 12:17
 **/
public class Test7 {
    public class Employee implements Comparable<Employee>{
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
        @Override
        public int compareTo(Employee o) {
            int x = Integer.compare(getAge(),o.getAge());
            return x==0?getName().length()-o.getName().length():x;
        }
    }

    public void run(){
        Employee[] employees = new Employee[5];
        employees[0]=new Employee("小明",10);
        employees[1]=new Employee("小黄黄",20);
        employees[2]=new Employee("小黑",20);
        employees[3]=new Employee("小灰",8);
        employees[4]=new Employee("小刘",30);
        Arrays.sort(employees);
        for(int i=0;i<employees.length;i++){
            System.out.println(employees[i].toString());
        }
    }
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        test7.run();
    }
}
