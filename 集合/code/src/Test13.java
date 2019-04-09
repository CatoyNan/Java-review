import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-08 21:02
 **/
public class Test13 {
    public void run(){
        Integer[] arry = new Integer[]{4,null,null,1,3,null};
        Arrays.sort(arry,Comparator.nullsFirst((o1, o2)->{
            return Integer.compare((int)o1,(int)o2);
        }));
        for(int i=0;i<arry.length;i++){
            if(arry[i]==null){
                System.out.println("null");
            }else{
                System.out.println(arry[i].intValue());
            }
        }
    }

    public static void main(String[] args){
        Test13 test13 = new Test13();
        test13.run();
    }
}
