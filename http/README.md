# http——java发送/接收http请求

[ 参考文档]: https://www.yeetrack.com/?p=779



## 一、HttpClient

##### HttpClient类似一个浏览器（不是浏览器），功能是执行一个http方法。

```java
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("https://www.yeetrack.com/");
    CloseableHttpResponse response = httpclient.execute(httpget);
    try {
        <...>
    } finally {
        response.close();
    }
```

##### HttpClient提供`URIBuilder`工具类来简化URIs的创建和修改过程。

```java
URI uri = new URIBuilder()
    .setScheme("http")
    .setHost("www.google.com")
    .setPath("/search")
    .setParameter("q", "httpclient")
    .setParameter("btnG", "Google Search")
    .setParameter("aq", "f")
    .setParameter("oq", "")
    .build();
    HttpGet httpget = new HttpGet(uri);
    System.out.println(httpget.getURI());
```

##### 设置超时时间

```java
  	CloseableHttpClient httpclient = HttpClients.createDefault();
    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(1000)
            .setConnectTimeout(1000)
            .build();

    HttpGet httpget1 = new HttpGet("https://www.yeetrack.com/1");
    httpget1.setConfig(requestConfig);
```

## 二、请求头/相应头

##### 增加请求头

```java
response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
```

##### 获取请求头

```java
 HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
    response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
    response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"yeetrack.com\"");
    Header h1 = response.getFirstHeader("Set-Cookie");
    System.out.println(h1);
    Header h2 = response.getLastHeader("Set-Cookie");
    System.out.println(h2);
    Header[] hs = response.getHeaders("Set-Cookie");
    System.out.println(hs.length);
```

```java
HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"yeetrack.com\""); HeaderIterator it = response.headerIterator("Set-Cookie");
while (it.hasNext()) { 
  System.out.println(it.next()); 
} 
```

## 三、请求体/相应体

如果要从Http实体中读取内容，我们可以利用`HttpEntity`类的`getContent`方法来获取实体的输入流（`java.io.InputStream`)，或者利用`HttpEntity`类的`writeTo(OutputStream)`方法来获取输出流，这个方法会把所有的内容写入到给定的流中。
当实体类已经被接受后，我们可以利用`HttpEntity`类的`getContentType()`和`getContentLength()`方法来读取`Content-Type`和`Content-Length`两个头消息（如果有的话）。由于`Content-Type`包含mime-types的字符编码，比如text/plain或者text/html,`HttpEntity`类的`getContentEncoding()`方法就是读取这个编码的。如果头信息不存在，`getContentLength（）`会返回-1,`getContentType()`会返回NULL。如果`Content-Type`信息存在，就会返回一个`Header`类。

##### 通过请求实体得到请求实体类型

```java
Header header = httpResponse.getEntity().getContentType();
```

HttpClient推荐使用`HttpEntity`的`getConent()`方法或者`HttpEntity`的`writeTo(OutputStream)`方法来消耗掉Http实体内容。HttpClient也提供了`EntityUtils`这个类，这个类提供一些静态方法可以更容易地读取Http实体的内容和信息。和以`java.io.InputStream`流读取内容的方式相比，EntityUtils提供的方法可以以字符串或者字节数组的形式读取Http实体。但是，强烈不推荐使用`EntityUtils`这个类，除非目标服务器发出的响应是可信任的，并且http响应实体的长度不会过大。

```java
 String entity = EntityUtils.toString(httpResponse.getEntity());
```

```java
/**
	* 将entity内容转换为字符串
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
```



##### 表单形式添加实体内容

```java
    List<NameValuePair> formparams = new ArrayList<NameValuePair>();
    formparams.add(new BasicNameValuePair("param1", "value1"));
    formparams.add(new BasicNameValuePair("param2", "value2"));
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
    HttpPost httppost = new HttpPost("https://www.yeetrack.com/handler.do");
    httppost.setEntity(entity);
//UrlEncodedFormEntity实例会使用所谓的Url编码的方式对我们的参数进行编码，产生的结果如下：
// param1=value1&param2=value2
```

##### 将参数保存到上下文中

```java

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            HttpGet httpGet = new HttpGet();
            URI uri = new URI("http://httpbin.org/get");
            httpGet.setURI(uri);
            HttpClientContext httpClientContext = HttpClientContext.create();
            HttpResponse httpResponse = httpclient.execute(httpGet,httpClientContext);
            if(httpResponse.getStatusLine().getStatusCode()==200){
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
```

