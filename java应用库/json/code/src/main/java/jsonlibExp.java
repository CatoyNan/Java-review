import javaBean.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName jsonlibExp
 * @Description TODO
 * @Author admin
 * @Date 2019-07-22 10:54
 * @Version 1.0
 **/
public class jsonlibExp {
    @Test
    /**
     * java对象 --> JSONArray & JSONObject
     */
    public void javaObjToJSONArrayOrJSONObject(){
        //list to JSONArray
        List<String> list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray);

        //String to JSONArray
        String s = "[1,2,3]";
        JSONArray jsonArray1 = JSONArray.fromObject(s);
        System.out.println(jsonArray1);

        //String to JSONObject
        String s1 = "{\"name\":\"小明\",\"password\":\"123\"}";
        JSONObject jsonObject = JSONObject.fromObject(s1);
        System.out.println(jsonObject);

        //Array to JSONArray
        boolean[] booleans = new boolean[]{true,false,false};
        JSONArray jsonArray2 = JSONArray.fromObject(booleans);
        System.out.println(jsonArray2);
    }

    /**
     * javaBean & Map --> JSONObject
     */
    @Test
    public void beanOrMapToJSONObject(){
        //Map to JSONObject
        Map<String,String> map = new HashMap();
        map.put("name","小明");
        map.put("password","234");
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println(jsonObject);

        //javaBean to JSONObject
        Student student = new Student();
        student.setName("小明");
        student.setPassword("123");
        JSONObject jsonObject1 = JSONObject.fromObject(student);
        System.out.println(jsonObject1);
    }
}
