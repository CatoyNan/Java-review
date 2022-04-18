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
- weaving:织入，把增强advice应用到目标对象target来创建新的代理对象proxy的过程
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
  - AOP联盟规范（规范）
  - spring-aop（实现）

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
    <bean id="UserServiceId" class="top.caoy.aopExample.aopTarget.impl.UserServiceImpl"></bean>

    <!--切面类-->
    <bean id="MySpringAspectId" class="top.caoy.aopExample.aopSemiautomatic.aspect.MySpringAspect"></bean>

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
                <value>top.caoy.aopExample.aopTarget.UserService</value>
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
    <bean id="UserServiceId" class="top.caoy.aopExample.aopTarget.impl.UserServiceImpl"></bean>

    <!--切面类-->
    <bean id="mySpringAspect" class="top.caoy.aopExample.aopSemiautomatic.aspect.MySpringAspect"></bean>

    <!--aop编程-->
    <!--<aop:config>-->
        <!--proxy-target-class="true"声明cjlib代理-->
        <!--<aop:pointcut>切入点-->
        <!--<advisor>特殊的切面，只有一个通知和一个切入点
            advice-ref:通知的引用
            pointcut-ref:切入点的引用-->
    <!--切入点表达式
	execution( *             top.caoy.aopExample.aopTarget.impl.*.       *          (..))-->
  <!--excution  (访问权限符号没有限制就不加 返回值类型  方法全限定类名(参数表))  -->
	<!--*代表一个或多个，-->
<aop:config proxy-target-class="true">
  <aop:pointcut id="myPointcut" expression="execution(* top.caoy.aopExample.aopTarget.impl.*.*(..))"/>
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

springAop全自动其实就是帮我们写了生成代理对象的工厂，以及工厂中如何获取代理对象。 在bean的后置处理器中获取这个代理对象

## 七、spring AOP 注解

#### 7.1目标类

```java
@Service("UserService")
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

#### 7.2切面

![image-20201207095522247](C:\Users\xjn17\AppData\Roaming\Typora\typora-user-images\image-20201207095522247.png)

```java
@Configuration
@Aspect
@EnableAspectJAutoProxy
public class LogAop {

    //切入点
    @Pointcut("within(top.caoy.aopExample.aopTarget.impl.UserServiceImpl)")
    public void performance() {
    }

    //通知方法会在目标方法执行之前执行
    @Before("performance()")
    public void before() {
        System.out.println("before");
    }

    //通常方法会在目标方法返回后调用
    @AfterReturning("performance()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    //通知方法会在目标方法抛出异常后调用
    @AfterThrowing("performance()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    //通知方法将目标方法封装起来
    @Around("performance()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around-before");
        Object proceed = joinPoint.proceed();
        System.out.println("around-after");
        return proceed;
    }

    //通知方法会在目标方法返回或抛出异常后调用
    @After("performance()")
    public void after() {
        System.out.println("after");
    }
}
```

#### 7.3测试

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/applicationContext3.xml"})
public class TestAnnotionAspect {
    @Autowired
    private UserService userService;

    @Test
    public void testAnnotionAspect(){
        userService.addUser();
    }
}

around-before
before
@addUser
around-after
after
afterReturning七、spring AOP 注解
```

#### 7.4切点表达式写法

- 固定格式

```java
excution(访问权限符号没有限制就不加(todo: spring5可能有区别待验证) 返回值类型  方法全限定方法名(参数表))    
```

- 通配符

  - *

    1. 匹配一个或多个字符

       ```java
       excution(public int top.catoy.myMethod*(int,int))//myMethod开头
       excution(public int top.catoy.myMethod*end(int,int))//myMethod开头end结尾
       ```

    2. 匹配任意一个方法参数

       ```java
       excution(public int top.catoy.myMethod(int,*))//匹配第一个参数为int，第二个参数任意（匹配两个参数）
       ```

    3. *在路径里只能匹配一层路径

       ```java
       excution(public int top.catoy.*.myMethod(int,int))//匹配top.catoy.xxx.myMethod
       ```

       

  - ..

    1. 匹配任意多个方法参数，任意类型方法参数

       ```java
       excution(public int top.catoy.myMethod(..))//不管带什么参数都能匹配
       ```

    2. 匹配任意多层路径

       ```java
       excution(public int top.catoy..myMethod(int,int))//匹配top.catoy.xxx.(任意多层).myMethod
       ```

       



## 八、传递参数给通知

#### 8.1 args

运行时所传入的参数是`A` 接口或是实现`A`接口的类的连接点

```
args（packageName.A）
```

区别于 `execution(* *(java.io.Serializable))`： args版本只有在动态运行时候传入参数是Serializable时才匹配，并且包括其子类。而execution版本在方法签名中声明只有一个 `Serializable`类型的参数时候匹配。

例：

- 只匹配`addUser`方法，且参数类型为`top.caoy.logTest.pojo.User`

```java
@Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.addUser(top.caoy.logTest.pojo.User))")
 public void performance3() {
 }
```

- 只匹配`addUser`方法，且参数类型为`top.caoy.logTest.pojo.User`或其子类

```java
@Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.addUser(..)) && args(top.caoy.logTest.pojo.User)))")
 public void performance3() {
 }
```

#### 8.2 使用args传递参数给通知

如果在一个args表达式中应该使用类型名字的地方 使用一个参数名字，那么当通知执行的时候对应的参数值将会被传递进来。用一个例子应该会使它变得清晰。 假使你想要通知以一个Account对象作为第一个参数的DAO操作的执行， 你想要在通知体内也能访问account对象，可以编写如下的代码：

```java
@Before（"com.xyz.myapp.SystemArchitecture.dataAccessOperation（..） &&" + 
        "args（account,..）"）
public void validateAccount（Account account） {
  // ...
}
```

切入点表达式的 `args(account,..)` 部分有两个目的：首先它保证了 只会匹配那些接受至少一个参数的方法的执行，而且传入的参数必须是`Account`类型的实例， 其次它使得在通知体内可以通过`account` 参数访问实际的`Account`对象。

另外一个办法是定义一个切入点，这个切入点在匹配某个连接点的时候“提供”了 `Account`对象的值，然后直接从通知中访问那个命名切入点。看起来和下面的示例一样：

```java
@Pointcut（"com.xyz.myapp.SystemArchitecture.dataAccessOperation（..） &&" + 
          "args（account,..）"）
private void accountDataAccessOperation（Account account） {}

@Before（"accountDataAccessOperation（account）"）
public void validateAccount（Account account） {
  // ...
}
```

#### 8.3 处理参数

```java
//切入点
@Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.addUser(..)) && args(user)))")
public void performance(User user) {
}

//通知方法将目标方法封装起来
@Around("performance(user)")
public Object around3(ProceedingJoinPoint joinPoint,User user) throws Throwable {
     System.out.println(user.getName());
     Object proceed = joinPoint.proceed();
     System.out.println(user.getAge());
     return proceed;
}
```

#### 8.4 环绕通知获取方法信息

```java
//切入点
@Pointcut("within(top.caoy.aopExample.aopTarget.impl.UserServiceImpl)")
	public void performance1() {
}

//通知方法将目标方法封装起来
@Around("performance1()")
public Object around1(ProceedingJoinPoint joinPoint) throws Throwable {
    Object proceed = null;
    try {
        System.out.println("around-before");
        proceed = joinPoint.proceed();
        System.out.println("around-afterReturning:" + proceed);
    } catch (Throwable throwable) {
        System.out.println("around-afterThrowing:" + throwable.getMessage());
        throw throwable;
    } finally {
        System.out.println("around-After");
    }
    return proceed;
}
```

#### 8.5 AfterReturing通知获取方法信息

```java
//切入点
@Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.returnString())")
  public void performance5() {
}

//AfterReturning通知获取方法的返回值
@AfterReturning(value = "performance5()", returning = "result")
public void afterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("afterReturning");

    Signature signature = joinPoint.getSignature();
    System.out.println("methodName:" + signature.getName());

    System.out.println("result:" + result);

    Object[] args = joinPoint.getArgs();
    System.out.println("args" + Arrays.toString(args));
}
```

8.6 AfterThrowing通知获取方法信息

```java
//切入点
@Pointcut("execution(* top.caoy.aopExample.aopTarget.impl.UserServiceImpl.throwError())")
   public void performance6() {
}

//AfterThrowing通知获取方法的返回值
@AfterThrowing(value = "performance6()", throwing = "error")
public void afterThrowing(JoinPoint joinPoint, Throwable error) {
    System.out.println("afterThrowing");

    Signature signature = joinPoint.getSignature();
    System.out.println("methodName:" + signature.getName());

    System.out.println("error:" + error.getMessage());

    Object[] args = joinPoint.getArgs();
    System.out.println("args" + Arrays.toString(args));
}
```





## 九、执行顺序

#### 9.1不同通知类型的执行顺序

- 正常执行： @Before ===>@After ====>@AfterReturning spring5.0之前， @Before ===>@AfterReturning ====>@After spring5

- 异常执行： @Before ===>@After ====>@AfterThrowing spring5.0之前，  @Before ===>@AfterThrowing ====>@After spring5

  ```java
  try {
      @Before
      method.invoke();
      @AfterReturning
  } cath () {
      @AfterThrowing
  } finally {
      @After
  }
  ```

  





[参考文档]: http://shouce.jb51.net/spring/aop.html#aop-choosing

