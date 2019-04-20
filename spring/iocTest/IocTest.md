# IOC

# 一、配置文件

- 存放在resource目录下
- 名字任意，按规范应命名为applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--配置service-->
    <!--<bean>配置需要创建的对象
        id从spring容器中获得实例时使用
        class需要创建实例的权限定类名-->
    <bean id="userServiceId" class="service.impl.UserServiceImpl"></bean>
</beans>
```

## 二、从容器中获取实例

```java
 @Test
    public void test1(){
       //1获得容器
        String path = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        //2获得内容
        UserService userService=(UserService)applicationContext.getBean("userServiceId");
        User user = userService.getAllInfo();
        System.out.println(user.toString());
    }
```

- 编译后resource目录中的文件会复制到classes的目录下

