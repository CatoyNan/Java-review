import java.util.function.Consumer;

/**
 * @description: lambda表达式作用域
 * @author: xjn
 * @create: 2019-04-07 16:54
 **/
public class Test10 {
    public static void main(String[] args){
        String text2 = "外部自由变量";
        Consumer<String> consumer = (text) ->{
            System.out.println(text);
            System.out.println(text2);
            //会报错
//            text2 = "重新赋值";
            text = "从新赋值";
        };
        consumer.accept("内部参数");
    }
//    public static void main(String[] args){
//        String text2 = "外部自由变量";
//        Consumer<String> consumer = (text) ->{
//            System.out.println(text);
//            System.out.println(text2);
//        };
//        consumer.accept("内部参数");
//        //会报错
//        text2 = "外部自由变量重新赋值";
//    }
//    public static void main(String[] args){
//        String text = "外部自由变量";
//        Consumer<String> consumer = (text) ->{
//            System.out.println(text);
//        };
//        consumer.accept("内部参数");
//    }
//public void run(){
//    System.out.println("run");
//}
//public void start(){
//    Consumer<String> consumer = (text) ->{
//        this.run();
//    };
//    consumer.accept("内部参数");
//}
//public static void main(String[] args){
// Test10 test10 = new Test10();
//    test10.start();
//
//}
}
