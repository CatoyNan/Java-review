import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javaBean.Student;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName FastJsonExp
 * @Description TODO
 * @Author admin
 * @Date 2019-07-22 14:00
 * @Version 1.0
 **/
public class FastJsonExp {
    protected Logger logger;
    private static final String urlPrefix = "http://la.911-express.com/chaxun.asp?order_ids=";
    private static final String appid = "trico035b2gfqq67w8";
    private static final String appSecret = "pk6iiwf2nbmtenjll64vzoumiq65xrvjp";
    private static final String callbackUrl = "http//";
    private static final String segmentName = "spider_dingRun";
    private static final String segmentId = "";
    @Test
    /**
     * javaBean --> String
     */
    public void javaBeanToString(){
        Student student = new Student();
        student.setPassword("123");
        student.setName("小明");
        String jsonStr = JSON.toJSONString(student);
        System.out.println(jsonStr);
    }

    @Test
    /**
     * List --> JSONArray
     */
    public void ListToJSONArray(){
        List<Student> students  = new ArrayList();
        Student student = new Student();
        Student student1 = new Student();
        student.setName("a");
        student.setPassword("234e");
        student1.setName("b");
        student1.setPassword("234");
        students.add(student);
        students.add(student1);
        JSONArray jsonArray = (JSONArray)JSONArray.toJSON(students);
        System.out.println(jsonArray);
    }

    /**
     * String --> javaBean
     */
    @Test
    public void stringToJavaBean(){
        String jsonStr = "{\"name\":\"小明\",\"password\":\"123\"}";
        Student student = JSON.parseObject(jsonStr,Student.class);
        System.out.println(student.toString());
    }

    /**
     * String --> List、Array
     */
    @Test
    public void stringToListOrArray(){
        String jsonStr = "[{\"name\":\"mac\",\"password\":\"123\"},{\"name\":\"lisa\",\"password\":\"234\"}]";
        List<Student> list = JSON.parseArray(jsonStr,Student.class);
        for(Student s:list){
            System.out.println(s.toString());
        }
    }

    /**
     * 复杂javaBean --> String
     */
    @Test
    public void complexJavaBeanToString(){

    }

}
