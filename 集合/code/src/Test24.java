import java.util.Arrays;

/**
 * @description: Arrays.copyOfRange
 * @author: xjn
 * @create: 2019-04-16 11:13
 **/
public class Test24 {
    public static void main(String[] args){
        Employee[] arr1 = { new Employee("小明",12),
                            new Employee("小黄",11)};
        Employee[] arr2 = Arrays.copyOfRange(arr1,0,arr1.length);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println("**************");
        arr1[0].setName("小丽");
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
/**
 * [Employee{name='小明', age=12}, Employee{name='小黄', age=11}]
 * [Employee{name='小明', age=12}]
 * **************
 * [Employee{name='小丽', age=12}, Employee{name='小黄', age=11}]
 * [Employee{name='小丽', age=12}]
 */
