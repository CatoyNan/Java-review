/**
 * @description:
 * @author: xjn
 * @create: 2019-04-02 14:47
 **/

import java.util.function.Consumer;

/**
 * 消费型函数式接口案例
 */
public class Test5 {
    public void myFunction(String str, Consumer<String> consumer){
        consumer.accept(str);
    }
    public static void main(String[] args){
        Test5 test5 = new Test5();
        test5.myFunction("短信内容",str -> System.out.println("发送短信,内容为:"+str));
    }
}
