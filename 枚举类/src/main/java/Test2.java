import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author admin
 * @Date 2019-07-02 16:30
 * @Version 1.0
 **/
public class Test2 {
   public static void main(String[] args) {
       CloseableHttpClient httpClient = HttpClients.createDefault();
       try {
           HttpGet get=new HttpGet("https://www.baidu.com");
           httpClient.execute(get);
           HttpClientContext context = HttpClientContext.create();
           CloseableHttpResponse response = httpClient.execute(get, context);
           try{
               System.out.println(">>>>>>headers:");
               Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
               System.out.println(">>>>>>cookies:");
               context.getCookieStore().getCookies().forEach(System.out::println);
               System.out.println(">>>>>>context");
               System.out.println(context.getCookieStore().toString());
           }
           finally {
               response.close();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           try {
               httpClient.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }

}
