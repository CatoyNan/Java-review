import org.junit.Test;

import java.util.Date;

/**
 * @ClassName DateExp
 * @Description TODO
 * @Author admin
 * @Date 2019-07-21 17:39
 * @Version 1.0
 **/
public class DateExp {
    /**
     * Date 的俩个构造方法
     */
    @Test
    public void constract(){
        //无参构造
        Date date1 = new Date();
        System.out.println(date1.toString());

        //long参数
        long time = 10000L;
        Date date2 = new Date(time);
        System.out.println(date2.toString());

    }

    @Test
    /**
     *  几个日期比较的方法
     */
    public void compareMethod(){
        Date date = new Date();
        long time = 10000L;
        Date date2 = new Date(time);

        //after,before
        System.out.println(date.after(date2));
        System.out.println(date.before(date2));

        //compateTo
        System.out.println(date.compareTo(date2));

        //equals
        System.out.println(date.equals(date2));
    }

    @Test
    /**
     * 从Date中时间相关
     */
    public void getTime(){
        //得到精确毫秒数
        Date date = new Date();
        System.out.println(date.getTime());

        //设置毫秒数
        Date before = new Date();
        System.out.println("before" + before);
        before.setTime(10000L);
        System.out.println("after" + before);

    }

}
