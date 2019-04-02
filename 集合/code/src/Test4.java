import java.util.function.Function;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-02 14:24
 **/

/**
 * 自带函数式接口Function<T,R>的使用
 */
public class Test4 {
    public void myFunction(Double x,Function<Double,Double> function){
       System.out.println(function.apply(x));
    }
    public static void main(String[] args){
        Test4 test4 = new Test4();
        test4.myFunction(1.2,(x) ->{return x*x;});
        test4.myFunction(1.2,(x) -> {return x+x;});
    }
}
