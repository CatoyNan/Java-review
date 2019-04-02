
/**
 * @description:
 * @author: xjn
 * @create: 2019-03-31 10:27
 **/

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 调用Collection.sort()方法，通过定制排序比较两个Employee(先比较年龄，年龄相同比较姓名)
 */
public class test2 {
    public static void main(String[] args){
        List<Employee> list = Arrays.asList(
                new Employee("b",18),
                new Employee("a",18),
                new Employee("小红红",17),
                new Employee("小明",19),
                new Employee("小明",12)
        );

        Collections.sort(list,(e1,e2)->{
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for(Employee emp : list){
            System.out.println(emp);
        }
    }
}
