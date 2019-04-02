/**
 * @description: 供给型函数式接口
 * @author: xjn
 * @create: 2019-04-02 21:10
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 产生指定个数的随机数
 */
public class Test6 {
    public List<Integer> myFunction(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            list.add(supplier.get());
        }
        return list;
    }

    public static void main(String[] args){
        Test6 test6 = new Test6();
        List<Integer> list = test6.myFunction(10,() ->(int)(Math.random()*100));
        for(Integer i:list){
            System.out.println(i.intValue());
        }
    }
}
