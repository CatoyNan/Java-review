import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: xjn
 * @create: 2019-05-07 17:46
 **/
public class TestIterator {
    public static void main(String[] args){
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(3);
        ints.add(2);
        //for循环遍历删除
//        for(int i=0;i<ints.size();i++){
//            if(ints.get(i) == 2){
//                ints.remove(i);
//            }
//        }
//        System.out.println(ints.toString());

        //foreach遍历删除
//        for(Integer integer:ints){
//            if(integer.intValue() == 2){
//                ints.remove(integer);
//            }
//        }
//        System.out.println(ints.toString());

        //iterator遍历,调用集合的remove()删除
//        Iterator<Integer> iterator = ints.iterator();
//        while (iterator.hasNext()){
//            int value = iterator.next();
//            if(value == 2){
//                ints.remove(value);
//            }
//        }

        //iterator遍历,调用iterator的remove()删除
        Iterator<Integer> iterator = ints.iterator();
        while (iterator.hasNext()){
            int value = iterator.next();
            if(value == 2){
                iterator.remove();
            }
        }
            System.out.println(ints.toString());

    }
}
