import java.util.function.IntConsumer;

/**
 * @description: 调用lambda表达式明确在哪次迭代中
 * @author: xjn
 * @create: 2019-04-08 13:00
 **/
public class Test11 {
    public static void repeat(int n, IntConsumer intConsumer){
        for(int i=0;i<n;i++){
            intConsumer.accept(i);
        }
    }
    public static void main(String[] args){
       Test11.repeat(10,i->{System.out.println(9-i);i++;});
    }
}
