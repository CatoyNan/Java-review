import Data.CollectionData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 测试集合注入
 * @author: xjn
 * @create: 2019-04-25 10:36
 **/
public class TestCollectionDi {
    public static void main(String[] args){
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        CollectionData collectionData = applicationContext.getBean("collectionData",CollectionData.class);
        System.out.println(collectionData.toString());
    }
}
