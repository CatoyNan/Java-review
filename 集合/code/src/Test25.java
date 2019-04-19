import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-17 13:53
 **/
public class Test25 {
    public static void main(String[] args){
        Employee employee = new Employee("小明",12);
        String name1 = employee.getName();
        String name2 = employee.setName();
    }
}
