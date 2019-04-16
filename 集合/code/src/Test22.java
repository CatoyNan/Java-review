import java.util.Arrays;

/**
 * @description: 数组拷贝System.arraycopy
 * @author: xjn
 * @create: 2019-04-16 10:23
 **/
public class Test22 {
    public static void main(String[] args){
        Employee[] arr1 = { new Employee("小明",12),
                            new Employee("小黄",11)};
        Employee[] arr2 = new Employee[arr1.length];
        System.arraycopy(arr1,0,arr2,1,1);
        System.out.println("arr1"+Arrays.toString(arr1));
        System.out.println("arr2"+Arrays.toString(arr2));
        System.out.println("************************");
        arr1[0].setName("小丽");
        System.out.println("arr1"+Arrays.toString(arr1));
        System.out.println("arr2"+Arrays.toString(arr2));
    }
}
/**
 * arr1[Employee{name='小明', age=12}, Employee{name='小黄', age=11}]
 *arr2[null, Employee{name='小明', age=12}]
 **************************
 *arr1[Employee{name='小丽', age=12}, Employee{name='小黄', age=11}]
 *arr2[null, Employee{name='小丽', age=12}]
* */