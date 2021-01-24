## Spring单元测试

一、注解

```java
@RunWith(SpringJUnit4ClassRunner.class)/@RunWith(SpringRunner.class)//后者继承于前者，提供了Spring测试功能和JUnit之间的桥梁。每当我们在JUnit测试中使用任何Spring Boot测试功能时，都将需要此批注
@ContextConfiguration("classpath*:spring/*.xml")/@SpringBootTest/@SpringBootTest(classes = {MusicSpriderCornApplication.class})//配置文件，@SpringBootTest替代了spring-test中的@ContextConfiguration注解，目的是加载ApplicationContext，启动spring容器。使用@SpringBootTest时并没有像@ContextConfiguration一样显示指定locations或classes属性，原因在于@SpringBootTest注解会自动检索程序的配置文件，检索顺序是从当前包开始，逐级向上查找被@SpringBootApplication或@SpringBootConfiguration注解的类。
@WebAppConfiguration//为测试类加载WebApplicationContext,默认路径为src/main/webapp,覆盖 value=''
```

