import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: Comparator.nullFirst
 * @author: xjn
 * @create: 2019-04-08 19:57
 **/
public class Test12 {
    public void run(){
        Integer[] arry = new Integer[]{4,1,3,null};
        Comparator comparator = (o1,o2)->{
            return Integer.compare((int)o1,(int)o2);
        };
        Arrays.sort(arry,Comparator.nullsFirst(comparator));
        for(int i=0;i<arry.length;i++){
            if(arry[i]==null){
                System.out.println("null");
            }else{
                System.out.println(arry[i].intValue());
            }

        }
    }

    public static void main(String[] args){
        Test12 test12 = new Test12();
        test12.run();
    }
}
