import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @description: Array
 * @author: xjn
 * @create: 2019-04-14 11:58
 **/
public class Test21 {
    public static void main(String[] args) {
//        int[] a = {1, 3, 5, 6};
//        int[] b = new int[]{2, 4, 6};
//        int[] c = new int[4];
//        int[] d;HashSet hashSet = new HashSet();
//        int[] e = {3,1,5,3,6,2};
//        int[] smallPrimes = {2,3,4,5};
//        int[] luckyNumber = smallPrimes;
//        luckyNumber[1] = 99;
//        smallPrimes[0] = 1;
//        System.out.println(smallPrimes);
//        System.out.println(luckyNumber);
//        int[] g;
//        g = new int[2];
//        System.out.println(g);
//    }
//        String[] array1 = new String[]{"1","2"};
//        String[] array2 = new String[array1.length];
//        System.out.println("array1 的地址是： " + array1);
//        System.out.println("array2 的地址是： " + array2);
//        array2 = array1.clone();
//        System.out.println("array2 的地址是： " + array2);
        Employee[] employees1 = {new Employee("name",12)};
        Employee[] employees2 = employees1.clone();
        employees1[0].setName("name2");
        System.out.println(employees1[0]);
        System.out.println(employees2[0]);
    }
}
