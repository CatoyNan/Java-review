import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @ClassName HttpClientExp
 * @Description TODO
 * @Author admin
 * @Date 2019-07-21 21:15
 * @Version 1.0
 **/
public class HttpClientExp {
    @Test
    /**
     * 发送post请求
     */
    public void postExp(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            httpPost.setEntity(new StringEntity("hello world"));
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            String resp = httpClient.execute(httpPost,responseHandler);
            System.out.println(resp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(httpClient != null){
                    httpClient.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void postJsonExp(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            httpPost.addHeader("ContentType","application/json");

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            String resp = httpClient.execute(httpPost,responseHandler);
            System.out.println(resp);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(httpClient != null){
                    httpClient.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Test
    /**
     * 发送一个get请求
     */
    public void getExp(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
       try{
           HttpGet httpGet = new HttpGet();
           URI uri = new URI("http://httpbin.org/get");
           httpGet.setURI(uri);

           ResponseHandler<String> responseHandler = response -> {
               int status = response.getStatusLine().getStatusCode();
               if (status >= 200 && status < 300) {
                   HttpEntity entity = response.getEntity();
                   return entity != null ? EntityUtils.toString(entity) : null;
               } else {
                   throw new ClientProtocolException("Unexpected response status: " + status);
               }
           };
           String resp = httpclient.execute(httpGet,responseHandler);
           System.out.println(resp);

       }catch (Exception e){
            e.printStackTrace();
       }
       finally {
           try{
               if(httpclient != null){
                   httpclient.close();
               }
           }catch (IOException e){
               e.printStackTrace();
           }
       }
    }

    @Test
    /**
     * 获得请求头
     */
    public void getHeadExp(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            HttpGet httpGet = new HttpGet();
            URI uri = new URI("http://httpbin.org/get");
            httpGet.setURI(uri);
            HttpClientContext httpClientContext = HttpClientContext.create();
            HttpResponse httpResponse = httpclient.execute(httpGet,httpClientContext);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                System.out.println("请求头====================================");
                HeaderIterator headerIterator = httpResponse.headerIterator();
                while (headerIterator.hasNext()){
                    Header header = headerIterator.nextHeader();
                    System.out.print(header.getName());
                    System.out.print(":");
                    System.out.println(header.getValue());
                }
                System.out.println("请求体====================================");
                InputStream is = httpResponse.getEntity().getContent();
                Header header = httpResponse.getEntity().getContentType();
                System.out.println(header.getName() + ":" + header.getValue());
                String entity = EntityUtils.toString(httpResponse.getEntity());
                System.out.println(entity);
                System.out.println(httpClientContext.getTargetHost());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(httpclient != null){
                    httpclient.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test() throws IOException{
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://m.haihu.com/getSignatureJson?url=https%253A%252F%252Fm.haihu.com%252FproductDetails.html%253FgoodsId%253D1765606");
        CloseableHttpResponse closeableHttpResponse =  closeableHttpClient.execute(httpGet);
        HttpEntity entity = closeableHttpResponse.getEntity();
        System.out.println(getEntityContent(entity));

        }


    /**
     * 将entity内容转换为字符串
     * @param entity
     * @return
     * @throws IOException
     */
    public String getEntityContent(HttpEntity entity) throws IOException{
        InputStream inputStream = entity.getContent();
        if (inputStream == null) {
            return null;
        } else {
            try {
                int i = (int) entity.getContentLength();
                if (i < 0) {
                    i = 4096;
                }
                Charset charset = null;
                ContentType contentType = ContentType.get(entity);
                if (contentType != null) {
                    charset = contentType.getCharset();
                }

                if (charset == null) {
                    charset = Charset.forName("utf-8");
                }

                Reader reader = new InputStreamReader(inputStream, charset);
                CharArrayBuffer buffer = new CharArrayBuffer(i);
                char[] tmp = new char[1024];

                int l;
                while ((l = reader.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }

                String var9 = buffer.toString();
                return var9;
            } finally {
                inputStream.close();
            }
        }
    }
}
