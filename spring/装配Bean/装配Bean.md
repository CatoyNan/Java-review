# 装配Bean

## 一、基于XML

### 1.1 实例化方法

- 3种bean实例化方法，默认构造、静态工厂、实例工厂

#### 1.11默认构造

```xml
//默认无参构造函数
<Bean id="" class=""><Bean>
```



#### 1.12静态工厂

- 用于整合其他框架或工具
- 静态工厂：用于生成势力对象，所有的方法必须是static

```xml
<!--<Bean id="" class="工厂全限定类名" factory-method="静态方法">-->
<!--将静态工厂创建的实例交给spring-->
<bean id="myBeanFactoryId" class="BeanFacoty.MyBeanFacory" factory-method="createServie"></bean>
```

```java
//静态工厂类
public class MyBeanFacory {
    public static UserService createServie(){
        return new UserServiceImpl();
    }
}
```

```java
public class TestBeanFactory {
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
<bean id="myBeanFactoryId2" class="BeanFacoty.MyBeanFacory2"></bean>
<!--获得工厂创建的实例-->
<bean id="userServiceid" factory-bean="myBeanFactoryId2" factory-method="createService"></bean>
```

```java
//实例工厂
public class MyBeanFacory2 {
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

## 二、基于注解

## 三、Bean的种类

- 普通bean<bean id="" class="A"><bean>创建A的实例并返回
- FactoryBean