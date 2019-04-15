import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 测试Itrator使用lambda
 * @author: xjn
 * @create: 2019-04-13 22:51
 **/
public class Test20 {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(9);
        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()){
//            Integer i = iterator.next();
//            i++;
//            System.out.println(i.intValue());
//        }
//        System.out.println(list.get(0));
//        iterator.forEachRemaining(element -> element = new Integer(4));
//        System.out.println(list.toString());
        iterator.forEachRemaining(element -> System.out.println(element.intValue()));
    }
}
