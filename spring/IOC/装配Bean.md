# IOC

## 一、实例化Bean

### 1.1 实例化方法

- 3种bean实例化方法，默认构造、静态工厂、实例工厂

#### 1.11默认

#### 构造

```xml
//默认无参构造函数
<Bean id="" class=""><Bean>
```



#### 1.12静态工厂（工厂方法模式）

- 用于整合其他框架或工具
- 静态工厂：用于生成实例对象，所有的方法必须是static

```xml
<!--<Bean id="" class="工厂全限定类名" factory-method="静态方法">-->
<!--将静态工厂创建的实例交给spring-->
<bean id="myFactoryBeanId" class="FacotyBean.MyFacoryBean" factory-method="createServie"></bean>
```

```java
//静态工厂类
public class MyFacoryBean {
    public static UserService createServie(){
        return new UserServiceImpl();
    }
}
```

```java
public class TestFactoryBean {
    @Test
    public void testBeanFactory(){
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService = applicationContext.getBean("myBeanFactoryId",UserService.class);
        System.out.println(userService.getAllInfo());
    }
}
```

#### 1.13实例工厂

- 必须先有工厂实例，通过实例创建对象，提供的所有方法都是非静态的

```xml
<!--将实例工厂创建的实例交给spring-->
<!--创建工厂实例-->
<bean id="myFactoryBeanId2" class="FacotyBean.MyFacoryBean2"></bean>
<!--获得工厂创建的实例-->
<bean id="userServiceid" factory-bean="myFactoryBeanId2" factory-method="createService"></bean>
```

```java
//实例工厂
public class MyFacoryBean2 {
    public UserService createService(){
        return new UserServiceImpl();
    }
}
```

```java
@Test
public void testBeanFactory2(){
    String path = "applicationContext.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
    UserService userService = applicationContext.getBean("userServiceid2",UserService.class);
    System.out.println(userService.getAllInfo());
}
```

## ~~二、基于注解~~

## 三、Bean的种类

- 普通bean<bean id="" class="A"><bean>创建A的实例并返回
- FactoryBean：是一个特殊的bean,具有工厂生产对象的能力，只能生产特定对象。bean必须去实现FactoryBean接口，此接口提供一个方法getObject(),用于返回特定的bean。AOP使用
- 参考链接<https://www.cnblogs.com/quanyongan/p/4133724.html>

## 四、Bean作用域

![1555945136527](http://ww1.sinaimg.cn/large/006tNc79ly1g4zljhxzo8j30k80aqq4v.jpg)

### 4.1配置文件

```xml
<!--bean作用域-->
<!--单例-->
<bean id="userServiceId3" class="service.impl.UserServiceImpl" scope="singleton"></bean>
<!--多例-->
<bean id="userServiceId4" class="service.impl.UserServiceImpl" scope="prototype"></bean>
```

### 4.2生产实例

```java
//单例
@Test
public void testScope(){
    String path = "applicationContext.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
    UserService userService1 = applicationContext.getBean("userServiceId3",UserService.class);
    UserService userService2 = applicationContext.getBean("userServiceId3",UserService.class);
    System.out.println(userService1);//service.impl.UserServiceImpl@79be0360
    System.out.println(userService2);//service.impl.UserServiceImpl@79be0360
}
```

```java
//多例
@Test
public void testScope2(){
    String path = "applicationContext.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
    UserService userService1 = applicationContext.getBean("userServiceId4",UserService.class);
    UserService userService2 = applicationContext.getBean("userServiceId4",UserService.class);
    System.out.println(userService1);//service.impl.UserServiceImpl@79be0360
    System.out.println(userService2);//service.impl.UserServiceImpl@22a67b4
}
```

## 五、Bean生命周期

[基于注解]: #七、装配Bean基于注解



## 5.1初始化和销毁

- 初始化：准备数据
- 销毁：销毁数据。容器销毁才能执行，bean要为单例

### 5.11配置文件

```xml
<!--生命周期-->
<bean id="userServiceId5"
      class="service.impl.UserServiceImplLifeCycle"
      init-method="myInit"
      destroy-method="myDestroy">
</bean>
```

### 5.12增加初始化和销毁方法

```java
public class UserServiceImplLifeCycle implements UserService {
    private UserDao userDao;

    public UserServiceImplLifeCycle() {
        this.userDao = new UserDaoImpl();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getAllInfo() {
        return userDao.getAllInfo();
    }

    public void myInit(){
        System.out.println("初始化");
    }

    public void myDestroy(){
        System.out.println("销毁");
    }
}
```

### 5.13测试

```java
@Test
public void testLifeCicle(){
    String path = "applicationContext.xml";
    //初始化容器
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
    //销毁容器
    ((ClassPathXmlApplicationContext) applicationContext).destroy();
}
/**
 * 初始化
 * 四月 22, 2019 11:54:47 下午 org.springframework.context.support.ClassPathXmlApplicationContext doClose
 * 信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@3ada9e37: startup date [Mon Apr 22 23:54:46 CST 2019]; root of context hierarchy
 * 销毁
 */
```

## 5.2beanPostProcess

- 只要实现这个接口(beanPostProcess)，并且将实现类提供给spring容器，在初始化方法前将执行before(),在初始化方法之后将执行after()

![1555986950378](http://ww4.sinaimg.cn/large/006tNc79ly1g4zljhj01sj31440ad76u.jpg)

- spring提供工厂钩子用于修改实例，对象可以生成代理对象，是AOP底层

  模拟：

  A a = new A();

  a = B.before(a);   -->将实例对象a传给后处理bean，生成代理对象并返回

  a.init();	

  a = B.after(a);

  a.function();

  a.destroy();

### 5.21 UserServiceImplLifeCycle实现类

```java
public class UserServiceImplLifeCycle implements UserService {
    private UserDao userDao;
    ...
    public User getAllInfo() {
        System.out.println("执行目标类方法");
        return userDao.getAllInfo();
    }

    public void myInit(){
        System.out.println("初始化");
    }

    public void myDestroy(){
        System.out.println("销毁");
    }
}
```

### 5.22 编写后处理类beanPostProcess

- 将事物写在后方法里，是因为在前方法之后会调用UserServiceImplLifeCycle的初始化方法，而其接口没有该方法，使用JDK动态代理的话，如果接口中没有相应的方法就不会执行
- 如果写在前方法里，需要接口中也要有初始化方法

```java
public class MybeanPostProcesser implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("前方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后方法");
        //生成代理
        return Proxy.newProxyInstance(MybeanPostProcesser.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy,method,args) ->{
                      System.out.println("事物插入1");
                      //执行目标方法
                      Object returnvalue = method.invoke(bean,args);
                      System.out.println("事物插入2");
                      return returnvalue;
                });
    }
}
```

### 5.23 beanPostProcess实现类提供给容器

```xml
 	<!--生命周期-->
    <bean id="userServiceId5"
          class="service.impl.UserServiceImplLifeCycle"
          init-method="myInit"
          destroy-method="myDestroy">
    </bean>

    <bean class="beanPostProcess.MybeanPostProcesser"></bean>
```

### 5.24 测试

```java
	@Test
    public void testLifeCicle2(){
        String path = "applicationContext.xml";
        //初始化容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
       UserService userService = 		         applicationContext.getBean("userServiceId5",UserService.class);
       userService.getAllInfo();

        //销毁容器
        ((ClassPathXmlApplicationContext) applicationContext).destroy();
    }
}
//前方法
//初始化
//后方法
//事物插入1
//执行目标类方法
//事物插入2
//销毁
```

## 六、Bean属性注入

[基于注解]: #七、装配Bean基于注解



### 6.1 属性注入

```xml
<!--bean属性注入-->
<!--sertter方法注入-->
<bean id="studentId" class="entity.Student">
    <!--普通数据-->
    <property name="name" value="小明"></property>
    <!--引用数据-->
    <property name="address" ref="addressId"></property>
</bean>
<!--注入的对象-->
<bean id="addressId" class="entity.Address">
    <property name="home" value="北京"></property>
</bean>
```

### 6.2 集合注入

```xml
<!--集合注入-->
<!--集合的注入都是给property添加子标签-->
<!--普通数据value、引用数据ref-->
<bean id="collectionData" class="Data.CollectionData">
    <property name="listData">
        <list>
            <value>first</value>
            <value>second</value>
            <value>third</value>
        </list>
    </property>
    <property name="mapData">
        <map>
            <entry key="first" value="data1"></entry>
            <entry key="second" value="data2"></entry>
            <entry key="third" value="data3"></entry>
        </map>
    </property>
    <property name="setData">
        <set>
            <value>first</value>
            <value>second</value>
            <value>third</value>
        </set>
    </property>
    <property name="properties">
        <props>
            <prop key="first">data1</prop>
            <prop key="second">data2</prop>
            <prop key="third">data3</prop>
        </props>
    </property>
</bean>
```

## 七、装配Bean基于注解

- 配置文件命名空间：

  <https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-annotation-config>

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          https://www.springframework.org/schema/context/spring-context.xsd">
  	<!--组件扫描，扫描含有组件的类-->
      <context:component-scan base-package="entity"></context:component-scan>
  
  </beans>
  ```

- 注入到容器中

  ```java
  @Service("UserServiceImplAnnotationId")
  public class UserServiceImplAnnotation implements UserService{
     .....
  }
  ```

  

- @Compenent取代<bean class=""><bean>
  @Compenent("id")取代<bean id="" class=""><bean>

  SpringMVC中：

  @Repository：dao层

  @Service: service层

  @Controller: web层

  

- 依赖注入，可以给私有字段设置，可以给setter方法设置。

  [xml方法]: #六、Bean属性注入

  普通值： @Value

  引用值：

  - 按照类型注入@Autowired

  - 按照名称注入1

    @Autowired(如果查询的结果不止一个，那么@Autowired会根据名称来查找。)

    @Quaifier("名称")

  - 按照名称注入2

    @Resource("名称")

- 生命周期  

  [xml配置]: #五、Bean生命周期

  

  - 初始化： @PostConstruct

  - 销毁： @PreDestroy

    ```java
    @Service
    public class UserServiceImplAnnotationLifeCycle implements UserService {
    	.....
        @PostConstruct
        public void myInit(){
            System.out.println("初始化");
        }
    
        @PreDestroy
        public void myDestroy(){
            System.out.println("销毁");
        }
    }
    
    ```

    

- 多例

  - @Scope("prototype") 多例

    ```java
    @Service
    @Scope("protptype")
    public class UserServiceImplAnnotation implements UserService{
       .....
    }
    ```

    

## 八、容器的初始化过程

- 容器的接口BeanFactory

以ClassPathXmlApplicationContext为例

- refresh();
  - Preparefresh();
  
  - obtaionFreshBeanfactory();
  
    

## 九、bean的初始话过程

<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAxwAAAJtCAYAAAC8DnbYAAAJYnRFWHRteGZpbGUAJTNDbXhmaWxlJTIwaG9zdCUzRCUyMmFwcC5kaWFncmFtcy5uZXQlMjIlMjBtb2RpZmllZCUzRCUyMjIwMjEtMDEtMjRUMDklM0EzNyUzQTEzLjIxMFolMjIlMjBhZ2VudCUzRCUyMjUuMCUyMChXaW5kb3dzJTIwTlQlMjAxMC4wJTNCJTIwV2luNjQlM0IlMjB4NjQpJTIwQXBwbGVXZWJLaXQlMkY1MzcuMzYlMjAoS0hUTUwlMkMlMjBsaWtlJTIwR2Vja28pJTIwQ2hyb21lJTJGODcuMC40MjgwLjE0MSUyMFNhZmFyaSUyRjUzNy4zNiUyMiUyMGV0YWclM0QlMjIxc2JNM0tjX09LSGFGYVJPNU1veCUyMiUyMHZlcnNpb24lM0QlMjIxNC4yLjQlMjIlMjB0eXBlJTNEJTIyZ2l0aHViJTIyJTNFJTNDZGlhZ3JhbSUyMGlkJTNEJTIya2dwS1lRdFRIWjB5QUt4S0tQNnYlMjIlMjBuYW1lJTNEJTIyUGFnZS0xJTIyJTNFN1ZyYmNxTTRFUDBhVmUwJTJCWklxckRZJTJGR2RySmJsZXlrZHFacVpoOWxrRzEyTUtLRVNPeDglMkZRcVF1RWs0T0RGeE1yTXZObXBhRjdwUG4yNEpnRG5mN1c4SVRMWjNPRUFSTUxSZ0Q4d0ZNQXhkTXgzMmwwc09wY1IyN0ZLd0lXSEFsV3JCbCUyRkFKaVo1Y21vVUJTbHVLRk9PSWhrbGI2T000Umo1dHlTQWglMkJMR3R0c1pSZTlZRWJwQWslMkJPTERTSlolMkJDd082TGFXT3JkWHlQMUM0MllxWmRZM2YyVUdoekFYcEZnYjRzU0V5bDhDY0U0eHBlYlhiejFHVUcwJTJGWXBleDMzWE8zV2hoQk1SM1N3WXd6NzNwUFhQOXdxeVh4NTI5ZkRYaHp4VWQ1Z0ZIR0gzaTJTaW1CUHZVUWpLJTJGWlB5WUh2bjU2RUVaaGo1TGtsOWt1dWczWEtBcGoxdklTUk1JZG9vaXdPeEVYMzljeWo3bUlRaWJMNyUyQnRGTzRwZ2tvYXJZbGlOU1FqeU01S0dEJTJCaHZsSlpJS0tRNGl3TVU4RlpseHFKQkNmNVJPU1lmVkxhS2VFUkVLTm8zUk54S053aXpCZVlQcWZHN1UlMkI0d2psaUhOeDlyOSUyQnZDJTJGZHVHNnljQ3NwQkRibE9OWEh1RlhYREhuT0FrVTNKU2dHOVE0YUhmZnBlY2t6OW95QUE4aThKTnpFUXJUQ25lRmNhQ2hNN3ltR0JTekVaak1oUUhRcktLc1A5RHFQRkFkRjVpOEdOQVEwRXIzR1FmRUJSQnloRFFEbVNGUlhuWGV4eXllU3ZmWGVsMnkzblRqazlTbkJFZjhVN05ZT21NMDhGQWR4aG1vZzJpMGpETWxQRFFVRXR5aGJSJTJGdFpaeWxob3E1WGcxY0NvN0RjSlNoamJ4JTJGSyUyRkh4V2ZuWU56UjY0ZnY5dFAyU29DMEFTWUpRVnU2aTdoMyUyQlJNd3NlMEJlOUdKY2t6b0ZtOXdES05tblBmRTIxQ1FESTNMcVNJdUZXRnAyV09GcGQzTG5iT000c2VRb0RsTUlHTzNYNWRLVGUxWkxsVTZ6WGJQNERRMSUyRnVXTTkzN3dieDgxcHY2OE1aVUJZSTFteTZraU1jMEpnaFQxNUthYyUyRnIlMkZ3Wm0yOFpTMzFHcmFIUElGRmFFMXpnQ2JRRCUyQlBOYmRGYUdPcmsxUVk0c3pZNWZPZkRGWTElMkYlMkJCMWxObnE1aDE2WnRYUzk3Vm05VzByMHBLMVQ4NDF1MnNwNWxCbEhzY3BPYjNOWVZqeGI2bklsdVBrVjJQNk1XZDBTJTJCJTJCaCUyRjBMMFlkTFk5RHVoNjV1a3J2U1NRbnFydnR2UlBCYlhic2NMSW9EYmVjejMyWEQ2eTJxWXpKc01TMGxqNXlKQXJzbzlxeThIVjdXaTJuRWkyVEhDU1JSODN0NWVCUEFBJTJCbCUyQkpqelcxRG9MdHhPVnNSWUNubkdYWGJLWTRCZjRMUU5PMExoNllwMTBGaEhOS1F4ZFRUVHh1Y0FrRHZKRGpOYnUwN1VuQ0tlVVlOVHR1U0FQWE1QcTRCbHQ3alJnVkllcHclMkZ6S2RzQVlXTlQlMkZkOUkxREZudm0xeDR4R3U2YXR6amxPUFdmczd1bUg3cWxPeFZWM3djSVFmZXZxNnV0dmdVTmRydCUyRjh5NkJRd1VxWEJWejF4a0g0WSUyRkpDd0ZYRVZlMnVuRUdBTzl0JTJCeDNnZmhjRHhjMHZyM1pYamNzNSUyRlFZNXZKJTJCbGE1eGJqaEJ2OFgwVHBnYiUyRjlnUm5GN2NxZ3l1dmFwMGtyczMlMkJhc09xWkN4YjdaaFd3T0RSYkRUOE5QUkxSWmZKWG0yZzZzSlF3eGlrUnJHNU9tTDd4U1lKOEdndVdObkRtd0xIekMxY0RNdzhzTGVBdGdic0FTd2Q0VERnQnl3bndUREJ6Q21VYnpKaXlDeGh0ZVY2aHc1U1pLU1pSWGdtdUNMdmE1RmRjeHkybThCYkZCVlBXZ0dPQzVSUzRGaHV3VzBOSVl4eUolMkI0RGc1S3R3cEhhZW9KNTBPTFNpd21aUU80cW9IdTFsaERGZ1Z5UmVFTzF3a0JWQlhFU29lRSUyRmpDRUgxRVlJMTFGaEhRTlMlMkZGNUlNWmlqc1paeXJ5SnEyJTJGRFZ0RDREWDZ4U05Fa21tSXBJdVFMWm41TVp5VjNGJTJCeXRNN2FkTHRESEdtNm5YU3FaSU44M2oxS3VuemRZMTd4Q0h2b2xhTSUyQlhLQ25ERVNYWExxZFR6T3lpWDFzbCUyRlBMVWlVOGZUMXBTbFJGMTluWFl3U3pRRm51RzlMaVgyeDgwdFJvbVYlMkJjQVlVbGZuQWc2WlhVT1dyNEMlMkJXcVNBUnh5bktOc1ltMHhhSlNIVlYzdUVlcCUyRlNlWUIlMkJsS1NaWFZ5dTB4Z1RKcXZtNTRSMWlmZ21HREFQWGVlRiUyQllZcXlWTWV2YjBwUjFvQlBTTjZXb2l6dHFBayUyRk9rV3hadjJaYktsZWYyeHNMdjhEJTNDJTJGZGlhZ3JhbSUzRSUzQyUyRm14ZmlsZSUzRWXMhJkAACAASURBVHhe7L1/zFXZdd+9SNJEEGdcFeraQqKINwlUdYuKnKlbQJrhD0OmjV3NtBAFcOTQFg9QVFxGHkP53aFjQU1LYWz+IGkERMOkRnKjegCrMBKQTlKEhNXqBdUvIkS0mb4PkmsmUL+t+7xaJ6zbffdz7r373PNr73M/V7I8PPectdf+rH3W2d+79j5nlvCBAAQgAAEIQAACEIAABCBQE4FZNdnFLAQgAAEIQAACEIAABCAAAUFwMAggAAEIQAACEIAABCAAgdoIIDhqQ4thCEAAAhCAAAQgAAEIQADBwRiAAAQgAAEIQAACEIAABGoj0KbgmK6tVxie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0cINCNRLgHxQL1+sQyAGAkHXeQyO4gMERhFAcIwiFM/3QYkHwRFPwPAEAjUSKJIPanQjM/2eiLxYdyPYh8AEEgi6zieQC11OkACCI52gBSUeBEc6AcVTCJQgEFM+0Cfp8KCREsHkVAgMIBB0nUMPAikQQHCkEKU/8TEo8SA40gkonkKgBIGY8kGf4AhyrETHJ/HUWc8S+yT2fcL7zOU04QOg6e6/+OKL8t57WrQe7/PCCy/I1atXc09GcIzHtI2zghIPgqON0NAmBBonEFM+QHDUHH4ER82A4zUfdJ3H6z6epUZA55DT0+O//mXY+QiOdEZDUOJBcKQTUDyFQAkCMeWDoYLjxo0bcujQITl37pzMnTt3aJcfPXok69evl0uXLmXHbd68WY4dOyazZ88ugar/VPVl0aJFWTvDPnrc3r17+w45e/bsyPPKOKr93717t7zxxhvy1ltvyapVq2T58uWC4ChDNelzg67zpHuI81ERQHBEFY7WnAlKPAiO1uJDwxBokkBM+aASwWFiY+PGjb1JvYqUa9euVSo6iggODeiePXuyuN69e1e2b98ux48fl8WLF9cSa/XNRIYrPubNm8cemVqIR2806DqPvhc4mAwBBEcyoarV0aDEg+CoNQYYh0AsBGLKBzMEh07O161bJ7dv35aDBw+KVjmswqH/vWLFioyjW8HIExdPnz6VHTt2iIqQefPmZRN+/Wie0+OnpqZ67axevbqviqLfb9iwITt+6dKlcv78ebl582bvb1atGOSPTv5dweH6olUH+/epU6ey465fv55VI/Tj2nS/07+fOHEiO+btt9/u+aUCRpmdPn1aDhw40KvoaB/0s2HDBgRHLFdes34EXefNukRrXSLg79lYuHCh3L9/v9fFYXsy8jiwpKoboyMo8SA4uhFsegGBEQRiygd9gmNqampalytZpUIn7iY4TCCcPHlSli1blomJ+fPny86dO7P/Xrly5cAlSyZi9Fyd2FtF2VHM/AAAIABJREFURCsQ+m9t5+HDh1k15NatW33LuFzx4FY43KrFggULev6oTV9w6LH79+/PBIMuDXO/1/5t3bo1EzX6cSshrpBSv1RsqThx+6/tmbhwl3qZCDly5MgcEXnKVTFxBIKu84mjQocrIzCqojHqe98RBEdloWnVUFDiQXC0GiMah0BTBGLKB32C486dO9PuxNyd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" style="cursor:pointer;max-width:100%;" onclick="(function(img){if(img.wnd!=null&&!img.wnd.closed){img.wnd.focus();}else{var r=function(evt){if(evt.data=='ready'&&evt.source==img.wnd){img.wnd.postMessage(decodeURIComponent(img.getAttribute('src')),'*');window.removeEventListener('message',r);}};window.addEventListener('message',r);img.wnd=window.open('https://viewer.diagrams.net/?client=1&page=0&edit=_blank');}})(this);"/>

  