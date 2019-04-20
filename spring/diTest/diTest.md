# DI

## 一、spring执行过程

1. 创建Service实例UserService userService  = new UserServiceImpl() --->IoC  <bean>
2. 创建Dao实例UserDao userDao = new UserDaoImpl()  --->IoC
3. 将userDao设置给userDervice userService.serDao(userDao) --->DI <property>

## 二、对比

```java
class UserService{
    //spring之前,Service和dao耦合
    UserDao userDao = new UserDaoImpl();
    //spring之后，解耦，不知道具体实现类
    UserDao userDao;
    setter();
}
```

## 三、配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--<property>用于进行属性注入
        name:bean的属性名用于setter方法注入
        ref:另一个bean的id值的引用-->
    <!--创建server-->
    <bean id="userServiceId" class="service.impl.UserServiceImpl">
        <property name="userDao" ref="userDaoId"></property>
    </bean>

    <!--创建dao-->
    <bean id="userDaoId" class="dao.impl.UserDaoImpl"></bean>

</beans>
```

## 四、从容器中获取实例

```java
 @Test
    public void test1(){
        //获取容器
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        //获取内容
        UserService userService=(UserService)applicationContext.getBean("userServiceId");
        User user = userService.getAllInfo();
        System.out.println(user.toString());
    }
```

