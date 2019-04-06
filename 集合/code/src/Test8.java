import java.util.Arrays;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-06 21:34
 **/
public class Test8 {
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
        Arrays.sort(employees,((o1, o2) -> {
            int x = Integer.compare(o1.getAge(),o2.getAge());
            return x==0?o1.getName().length()-o2.getName().length():x;
        }));
        for(int i=0;i<employees.length;i++){
            System.out.println(employees[i].toString());
        }
    }
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        test7.run();
    }
}
