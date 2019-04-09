# Compare和Comparable比较

## 一、Comparable

### 1.1对象实现Comparable接口

```java
public class Test7 {
    public class Employee implements Comparable<Employee>{
        private String name;
        private int age;

		...
            
        @Override
        public int compareTo(Employee o) {
            int x = Integer.compare(getAge(),o.getAge());
            return x==0?getName().length()-o.getName().length():x;
        }
    }

    public void run(){
        Employee[] employees = new Employee[5];
        employees[0]=new Employee("小明",10);
        employees[1]=new Employee("小黄黄",20);
        employees[2]=new Employee("小黑",20);
        employees[3]=new Employee("小灰",8);
        employees[4]=new Employee("小刘",30);
        Arrays.sort(employees);
        for(int i=0;i<employees.length;i++){
            System.out.println(employees[i].toString());
        }
    }
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        test7.run();
    }
}

```

## 二、Comparator

### 2.1比较器实现Comparator接口

```java
public class Test8 {
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
        Arrays.sort(employees,((o1, o2) -> {
            int x = Integer.compare(o1.getAge(),o2.getAge());
            return x==0?o1.getName().length()-o2.getName().length():x;
        }));
        for(int i=0;i<employees.length;i++){
            System.out.println(employees[i].toString());
        }
    }
	...
}
```



### 2.2使用Comparator.comparing()

- Comparator.comparing()返回的是一个定义好compare()方法的Comparator。有两种形式

  - ```java
    //第一个参数为函数型函数式接口，可以传入一个lambda表达式，其中lambda表达式可以用方法引用::。(提取键)
    //第二个参数为Comparator接口，可以传入一个lambda表达式。(指定比较器)
    Comparator.comparing(Function<? super T, ? extends U> keyExtractor,
                Comparator<? super U> keyComparator)
    ```

  - ```java
    //各种变体，避免int、long的封装
    //参数为一个返回int型的函数式接口，可以传入一个lambda表达式，其中lambda可以用方法引用::
    //与变体前缺少一个参数，因为第二个参数源码已实现
    Comparator.comparingInt(ToIntFunction<? super T> keyExtractor)
    ```

    

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
        //lambda表达式可以换成方法引用Class::functionName
        //等于说comparing传入两个lambda表达式，第一个是传给函数式接口Function{}的，用来返回
        //需要比较的对象，第二个是传给Comparator接口的Compare方法
//        Arrays.sort(employees,Comparator.comparing(Employee::getAge,(o1,o2)->{
//            return Integer.compare(o1,o2);
//        }).thenComparing(Employee::getName,(o1,o2) ->{
//            return o1.length()-o2.length();
//        }));

        //用变体，避免int、double的封装
        Arrays.sort(employees,Comparator.comparingInt(Employee::getAge)
                .thenComparingInt(p -> p.getName().length()));
    }
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        test7.run();
    }
}

```

### 2.3nullsFirst、nullsLast

- 如果返回结果中含有null则得用nullsFirst和nullsLast适配器,否则会报错

```java
//nullFirst会修改现有比较器,从而遇到null值时不会抛出异常

public class Test12 {
    public void run(){
        Integer[] arry = new Integer[]{4,1,null,3,null};
        //现有比较器
        Comparator comparator = (o1,o2)->{
            return Integer.compare((int)o1,(int)o2);
        };
        //改造后比较器
        Arrays.sort(arry,Comparator.nullsFirst(comparator));
        for(int i=0;i<arry.length;i++){
            if(arry[i]==null){
                System.out.println("null");
            }else{
                System.out.println(arry[i].intValue());
            }

        }
    }

    public static void main(String[] args){
        Test12 test12 = new Test12();
        test12.run();
    }
}
/*
 *null
 *null
 *1
 *3
 *4
* */
```

