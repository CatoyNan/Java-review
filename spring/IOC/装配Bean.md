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

<iframe frameborder="0" style="width:100%;height:613px;" src="https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=bean%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F.svg#Uhttps%3A%2F%2Fraw.githubusercontent.com%2FCatoyNan%2FimageCloud%2Fmaster%2Fbean%25E7%2594%259F%25E5%2591%25BD%25E5%2591%25A8%25E6%259C%259F.svg"></iframe>