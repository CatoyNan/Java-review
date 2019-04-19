import javax.swing.plaf.synth.SynthSpinnerUI;
import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-17 13:53
 **/
public class Test25 {
    private String job;
    private final Date date;
    private static String name;
    private static String dex;
    public Test25(){
        date = new Date();
    }

    public void run2(){
        System.out.println(job);
        System.out.println(name);
    }
//    public static void run(){
//       System.out.println(job);
//       System.out.println(name);
//    }
    public static void main(String[] args){
        Test25 test25 = new Test25();
        test25.date.setTime(123);
        System.out.println(test25.date);
        //Thu Jan 01 08:00:00 CST 1970

        Employee employee = new Employee("小明",12);
//        Test25.run();
        test25.run2();
    }
}
