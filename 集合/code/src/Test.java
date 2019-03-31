import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: xjn
 * @create: 2019-03-31 22:12
 **/
public class Test {

    /**
     * lambda表达式在Arrays.sort()方法的应用
     */
    public void run(){
        Integer[] ints = new Integer[]{1,4,6,3,2};
        Arrays.sort(ints,(o1,o2) ->  Integer.compare(o1,o2));

       for(Integer i:ints){
           System.out.println(i.intValue());
       }

    }

    public static void main(String[] args){
        Test test = new Test();
        test.run();
    }
}
