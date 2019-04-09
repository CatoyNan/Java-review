# lambda表达式

## 一、简介

### 1.1代码格式

- ```java
  (String first,String second) -> {
      first.length()-second.length()
  }
  ```

  

- 如果可以推导出类型类型可以省略

  ```java
  (first,second) -> {
      forst.length()-second.length();
  }
  ```

- 如果在一个分支上有返回值，其它分支上没有是不合法的

  ```java
  (x) -> {if(x>0) return 1;}
  ```

  

## 二、函数式接口

### 2.1定义

- 只有一个抽象方法的接口
- java.util.function包中定义了很多有用的函数式接口
  1. Consumer<T>消费型接口void accept(T t)
  2. Supplier<T>供给型接口T get()
  3. Function<T,R>函数型接口R apply(T t)
  4. Predicate<T>断言型接口boolean test(T t)  

### 2.2应用举例

#### 2.21Arrays.sort()

```java
	/**
	 * Arrays.sort(T[] a,Comparator<? super T> c)
     * 匿名内部类在Arrays.sort()方法的应用
     */
    public void run(){
        Integer[] ints = new Integer[]{1,4,6,3,2};
        Arrays.sort(ints,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
       for(Integer i:ints){
           System.out.println(i.intValue());//[1,2,3,4,6]
       }
    }

   /**
    * lambda表达式在Arrays.sort()方法的应用
    */
    public void run(){
        Integer[] ints = new Integer[]{1,4,6,3,2};
        Arrays.sort(ints,(o1,o2) ->  Integer.compare(o1,o2));
        for(Integer i:ints){
           System.out.println(i.intValue());
        }
    }
```

#### 2.22Collection.sort()

```java
/**
 * 调用Collection.sort()方法，通过定制排序比较两个Employee(先比较年龄，年龄相同比较姓名)
 */
public class test2 {
    public static void main(String[] args){
        List<Employee> list = Arrays.asList(
                new Employee("b",18),
                new Employee("a",18),
                new Employee("小红红",17),
                new Employee("小明",19),
                new Employee("小明",12)
        );

        Collections.sort(list,(e1,e2)->{
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for(Employee emp : list){
            System.out.println(emp);
        }
    }
}
/*Employee{name='小明', age=12}
  Employee{name='小红红', age=17}
  Employee{name='a', age=18}
  Employee{name='b', age=18}
  Employee{name='小明', age=19}*/
```

#### 2.23自定义函数式接口

```java
**
 * 1.声明函数式接口，接口中声明方法
 * 2.声明类，类中编写方法是用接口作为参数
 * 3.实现对字符串的大小写转换盒截取操作
 */
public class Test3 {
    @FunctionalInterface
    public interface MyFunction{
        String getValues(String str);
    }

    public String strHandler(String str,MyFunction myFunction){
        return myFunction.getValues(str);
    }

    public static void main(String[] args){
       Test3 test3 = new Test3();
       String strToLower = test3.strHandler("ABCDefg",str->{return 				    		str.toLowerCase();});//abcdefg
       String strSub = test3.strHandler(strToLower,str -> {return				    	   	str.substring(1,2);});//b
       System.out.println(strSub);
    }
}
```

#### 2.24函数型

```java
/**
 * 函数型函数式接口Function<T,R>的使用
 */
public class Test4 {
    public void myFunction(Double x,Function<Double,Double> function){
       System.out.println(function.apply(x));
    }
    public static void main(String[] args){
        Test4 test4 = new Test4();
        test4.myFunction(1.2,(x) ->{return x*x;});
        test4.myFunction(1.2,(x) -> {return x+x;});
    }
}

```

#### 2.25消费型

```java
/**
 * 消费型函数式接口案例
 */
public class Test5 {
    public void myFunction(String str, Consumer<String> consumer){
        consumer.accept(str);
    }
    public static void main(String[] args){
        Test5 test5 = new Test5();
        test5.myFunction("短信内容",str -> System.out.println("发送短信,内容为:"+str));
    }
}
```

#### 2.26供给型

```java
/**
 * 消费型函数式接口案例 产生指定个数的随机数
 */
public class Test6 {
    public List<Integer> myFunction(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            list.add(supplier.get());
        }
        return list;
    }

    public static void main(String[] args){
        Test6 test6 = new Test6();
        List<Integer> list = test6.myFunction(10,() ->(int)(Math.random()*100));
        for(Integer i:list){
            System.out.println(i.intValue());
        }
    }
}
```

## 三、方法引用

- lambda表达式中方法体在某个类或对象中已实现，可以用

  - object::instanceMethod
  - Class::staticMehtod
  - Class::instanceMethod

  替换



```java
public class Test9 {
    public class Employee{
        private String name;
        private int age;

      	...
    }

    public void run(){
        Employee[] employees = new Employee[5];
        employees[0]=new Employee("小明",10);
        employees[1]=new Employee("小黄黄",20);
        employees[2]=new Employee("小黑",20);
        employees[3]=new Employee("小灰",8);
        employees[4]=new Employee("小刘",30);
        // comparing 方法接收一个 Function 函数式接口 ，通过一个 lambda 表达式传入
        //这个表达式是(e) -> e.getAge();
        //所以apply方法体实现为
        //R apply(T t){
        //	return t.getAge();
    	//}
        //这和Employee的getAge()方法功能一样，
        //lambda表达式可以换成方法引用Class::functionName
        //等于说comparing传入两个lambda表达式，第一个是传给函数式接口Function{}的，用来返回
        //需要比较的对象，第二个是传给Comparator接口的Compare方法
        Arrays.sort(employees,Comparator.comparing(Employee::getAge,(o1,o2)->{
           return Integer.compare(o1,o2);
        }).thenComparing(Employee::getName,(o1,o2) ->{
           return o1.length()-o2.length();
        }));

    }
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        test7.run();
    }
}

```

## 四、构造器引用

```java
Person[] perple = stream.toArray(Pserson[]::new)
```

## 五、作用区域

- lambda表达式是一个闭包，自由变量会被保存并且调用，但是不能改变其值，否则会报错(线程不安全)

### 5.1只能引用值不能改变值

```java
/**
 * @description: lambda表达式作用域
 * @author: xjn
 * @create: 2019-04-07 16:54
 **/
public class Test10 {
    public static void main(String[] args){
        String text2 = "外部自由变量";
        Consumer<String> consumer = (text) ->{
            System.out.println(text);
            System.out.println(text2);
            //会报错,lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
			//text2 = "重新赋值";
            
            //不会报错text只是传入的参数
            //text = "重新赋值";
        };
        consumer.accept("内部参数");
    }
}
```

### 5.2在外部改变引用的值(自由变量)不合法

```java
public static void main(String[] args){
        String text2 = "外部自由变量";
        Consumer<String> consumer = (text) ->{
            System.out.println(text);
            System.out.println(text2);
        };
        consumer.accept("内部参数");
        //会报错
        text2 = "外部自由变量重新赋值";
    }

//这里是合法的，i是传入的参数
public class Test11 {
    public static void repeat(int n, IntConsumer intConsumer){
        for(int i=0;i<n;i++){
            intConsumer.accept(i);
        }
    }
    public static void main(String[] args){
       Test11.repeat(10,x->System.out.println(9-x));
    }
}

```

### 5.3lambda表达式变量名与局部变量名重复不合法

```java
public static void main(String[] args){
        String text = "外部自由变量";
        Consumer<String> consumer = (text) ->{
            System.out.println(text);
        };
        consumer.accept("内部参数");
    }
```

### 5.4this

- 指创建这个lambda表达式的方法的this参数