import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-08 22:07
 **/
public class Test14 {
    public void run(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("小明",15));
        employeeList.add(new Employee("小明",14));
//        Collections.sort(employeeList, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return Integer.compare(o1.getAge(),o2.getAge());
//            }
//        });

//        Collections.sort(employeeList,Comparator.comparing(new Function<Employee, Integer>(){
//            @Override
//            public Integer apply(Employee employee) {
//                return employee.getAge();
//            }
//        },(o1,o2) ->{ return Integer.compare((int)o1,(int)o2);}));

//        Collections.sort(employeeList,Comparator.comparing(o ->  o.getAge(),
//                (o1,o2) ->{ return Integer.compare((int)o1,(int)o2);}));

        Collections.sort(employeeList,Comparator.comparing(o ->  o.getAge(),
                (o1,o2) ->{ return Integer.compare((int)o1,(int)o2);}));

        System.out.println(employeeList.toString());
    }

    public static void main(String[] args){
        Test14 test14 = new Test14();
        test14.run();
    }
}
