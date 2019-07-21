import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SimpleDateFormatExp
 * @Description TODO
 * @Author admin
 * @Date 2019-07-21 18:04
 * @Version 1.0
 **/
public class SimpleDateFormatExp {
    @Test
    /**
     * 日期的格式化
     */
    public void simpleDateFormatTest(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        System.out.println(time);
    }

    @Test
    /**
     * String --> Date
     */
    public void stringToDate() throws ParseException {
        String s = "2019-09-08 10:10:10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        date = sdf.parse(s);
        System.out.println(sdf.format(date));
    }

    @Test
    /**
     * Date --> String
     */
    public void dateToString() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        System.out.println(dateString);
    }
}
