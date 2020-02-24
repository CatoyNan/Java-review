## SpringMVC 单元测试

一、注解

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/*.xml")//配置文件
@WebAppConfiguration//为测试类加载WebApplicationContext,默认路径为src/main/webapp,覆盖 value=''
```

