## Spring 是如何读取外部资源的

​	在Spring中，资源是通过资源URL表示的，比如/conf/bean.xml、http://xxx、ftp://xxx等。每一种形式代表不同来源的资源形式,但是URL无法表示全部形式的资源，例如classpath:xxx。为了解决这个问题，Spring 通过`Resource`接口去抽象一个资源。

```java
public interface Resource extends InputStreamSource {
 		boolean exists();

    boolean isOpen();

    URL getURL() throws IOException;

    File getFile() throws IOException;

    Resource createRelative(String relativePath) throws IOException;

    String getFilename();

    String getDescription();
}
```

spring中主要实现的`Resource`类型有：

- `ClassPathResource`
- `FileSystemResource`
- `UrlResource`
- `ServletContextResource`
- `InputStreamResource`
- `ByteArrayResource`

`ResourceLoader`是一个可以返`Resource`对象的接口

```java
public interface ResourceLoader {
    Resource getResource(String location);
}
```

所有的`ApplicationContext`都实现了`ResourceLoader`，因此所有的`ApplicationContext`都可以返回对应的`Resource`实力。

