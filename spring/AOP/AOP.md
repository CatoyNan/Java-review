# AOP

## 一、简介

- Aspect Oriented Programming:面向切面编程。

- 降低耦合度提高可重用性

- 底层采用代理机制实现

- oop(面向对象编程)的延续

- 横向抽取机制取代了纵向继承

  （性能监视、事物管理、安全检查、缓存）

![1556595558696](http://ww1.sinaimg.cn/large/006tNc79ly1g4zljnwpmbj30t60e4q5o.jpg)

## 二、AOP实现原理

- 接口+实现类：spring 采用jdk的动态代理Proxy
- 实现类：cglib字节码增强

## 三、AOP术语

- target:需要被代理的类
- JoinPoint:连接点，那些可能被拦截的方法
- PoinCut:切入点，已经被增强的连接点
- Advice:通知，增强的代码，例如：after,before
- weaving:织入
- Proxy:代理类
- Aspect:切面，切入点和通知的结合

## 四、手动方式

### 4.1jdk动态代理

#### 4.11目标类

```java
@Service
public class UserServiceImpl implements UserService {
    public void addUser() {
        System.out.println("addUser");
    }

    public void delUser() {
        System.out.println("delUser");
    }

    public void updateUser() {
        System.out.println("updateUser");
    }
}
```

#### 4.12切面MyAspect

```java
public class MyAspect {
    public void before(){
        System.out.println("before()");
    }

    public void after(){
        System.out.println("after()");
    }
}
```

#### 4.13工厂BeanFacory

![1556604649175](http://ww3.sinaimg.cn/large/006tNc79ly1g4zljrhwplj31210g8wi2.jpg)

```java
public class BeanFactory {
   public static UserService creatBean(){
        //目标类
       final UserService userService = new UserServiceImpl();//jdk1.8后默认final
        //切面类
       final MyAspect myAspect = new MyAspect();
        //代理类
        UserService userServiceProxy =(UserService) Proxy.newProxyInstance(BeanFactory.class.getClassLoader(),userService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               Object result = null;
                if(method.getName().equals("addUser")){
                    //执行前方法
                    myAspect.before();
                    //执行代理类方法
                    result = method.invoke(userService,args);
                    //Object result = method.invoke(userService,args);
                    //执行后方法
                    myAspect.after();
                }
                return result;
            }
        });
        return userServiceProxy;
    }
}
```

#### 4.14测试

```java
@Test
public void test(){
    UserService userService = BeanFactory.creatBean();
    userService.addUser();
    userService.delUser();
}
/**
 * before()
 * addUser
 * after()
 */
```

### 4.2CGLIB

- 没有接口只有实现类
- 采用字节码增强框架cglib,在运行时创建子类，从而对目标进行增强。
- spring-core中已经存在

#### 4.21目标类

```java
public class UserServiceImpl{
    public void addUser() {
        System.out.println("addUser");
    }

    public void delUser() {
        System.out.println("delUser");
    }

    public void updateUser() {
        System.out.println("updateUser");
    }
}
```

#### 4.22切面

```java
public class MyAspect {
    public void before(){
        System.out.println("before()");
    }

    public void after(){
        System.out.println("after()");
    }
}
```

#### 4.23工厂BeanFactory

```java
public class BeanFactoryCjlib {
    public static UserServiceImpl creatBean(){
       //目标类
        final UserServiceImpl userService = new UserServiceImpl();
        //切面类
        final MyAspect myAspect = new MyAspect();
        //代理类,底层:创建目标类的子类
        //核心类
        Enhancer enhancer = new Enhancer();
        //确定父类
        enhancer.setSuperclass(UserServiceImpl.class);
        //设置回调函数,MethodInterceptorde等同于InvocationHandler
        //intercept前三个参数和jdk invoke一样
        enhancer.setCallback(new MethodInterceptor(){
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                if(method.getName().equals("addUser")){
                    //执行前方法
                    myAspect.before();
                    //执行代理类方法
                    result = method.invoke(userService,objects);
                    //Object result = method.invoke(userService,args);
                    //执行后方法
                    myAspect.after();
                }
                return result;
            }
        });
        //创建代理类
        UserServiceImpl userService1 =(UserServiceImpl) enhancer.create();
        return userService1;
    }
}
```

#### 4.14测试

```java
@Test
public void testCjlib(){
    UserServiceImpl userService = BeanFactoryCjlib.creatBean();
    userService.addUser();
    userService.delUser();
}
/**
 * before()
 * addUser
 * after()
 */
```

## 五、spring编写代理：半自动

- 让spring创建代理对象，从spring容器中手动获取代理

- 核心jar包

  - 核心4+1
  - AOP联盟规范
  - spring-aop

  ![1557047380753](http://ww1.sinaimg.cn/large/006tNc79ly1g4zljplprpj30k906kab4.jpg)

### 5.1AOP联盟通知类型

![1557047019031](http://ww1.sinaimg.cn/large/006tNc79ly1g4zljt1rfbj30uh0b00va.jpg)

### 5.2目标类

```java
public class UserServiceImpl implements UserService {
    public void addUser() {
        System.out.println("addUser");
    }

    public void delUser() {
        System.out.println("delUser");
    }

    public void updateUser() {
        System.out.println("updateUser");
    }
}
```

### 5.3切面

```java
/**
 * @description: spring半自动AOP,采用环绕通知
 * @author: xjn
 * @create: 2019-05-05 17:14
 **/
public class MySpringAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("前方法");
        Object obj = methodInvocation.proceed();
        System.out.println("后方法");
        return obj;
    }
}
```

### 5.4配置文件

![1557055793252](http://ww4.sinaimg.cn/large/006tNc79ly1g4zljusylkj30yh0di40y.jpg)

```xml
....

    <!--目标类-->
    <bean id="UserServiceId" class="top.caoy.service.impl.UserServiceImpl"></bean>

    <!--切面类-->
    <bean id="MySpringAspectId" class="top.caoy.aspect.MySpringAspect"></bean>

    <!--代理类-->
    <!--*使用工厂bean FactoryBean，底层调用getobject（）返回特殊bean-->
    <!--*ProxyFactoryBean 用于创建代理工厂bean，生成特殊代理对象-->
        <!--interfaces：确定接口们-->
            <!--通过<array>可以设置多个值-->
            <!--只有一个值时，value=""-->
        <!--target：确定目标类-->
        <!--interceptorNames：通知切面类的名称，类型String[]，如果设置一个值,value=""-->
    <bean id="ProxyUserServiceId" class="org.springframework.aop.framework.ProxyFactoryBean">
        <property name="interfaces">
            <array>
                <value>top.caoy.service.UserService</value>
            </array>
        </property>
        <property name="target" ref="UserServiceId"></property>
        <property name="interceptorNames" value="MySpringAspectId"></property>
    </bean>
</beans>
```

### 5.5测试

```java
@Test
public void testSpringAspect(){
    String path = "applicationContext.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
    UserService userService =(UserService) applicationContext.getBean("ProxyUserServiceId");
    userService.delUser();
}
    /**
     * 前方法
     * delUser
     * 后方法
     */
```

## 六、spring AOP编程:全自动

- 依赖

  ![1557056841529](http://ww4.sinaimg.cn/large/006tNc79ly1g4zljvf9b5j30hx076t9y.jpg)

  

- 配置文件命名空间：

  <https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-annotation-config>

### 6.1目标类

同上

### 6.2切面

同上

### 6.3配置文件

```xml
...
    <!--目标类-->
    <bean id="UserServiceId" class="top.caoy.service.impl.UserServiceImpl"></bean>

    <!--切面类-->
    <bean id="mySpringAspect" class="top.caoy.aspect.MySpringAspect"></bean>

    <!--aop编程-->
    <!--<aop:config>-->
        <!--proxy-target-class="true"声明cjlib代理-->
        <!--<aop:pointcut>切入点-->
        <!--<advisor>特殊的切面，只有一个通知和一个切入点
            advice-ref:通知的引用
            pointcut-ref:切入点的引用-->
    <!--切入点表达式
	execution( *             top.caoy.service.impl.*.       *          (..))-->
  <!--选择方法  返回值任意表  包                     类名任意 方法名任意  参数任意       -->
<aop:config proxy-target-class="true">
  <aop:pointcut id="myPointcut" expression="execution(* top.caoy.service.impl.*.*(..))"/>
  <aop:advisor advice-ref="mySpringAspect" pointcut-ref="myPointcut"/>
</aop:config>
```

### 6.4测试

```java
    @Test
    public void testSpringAutoAspect(){
        String path = "applicationContext2.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService =(UserService) applicationContext.getBean("UserServiceId");
        userService.delUser();
    }
}
    /**
     * 前方法
     * delUser
     * 后方法
     */
```