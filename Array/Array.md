# Array数组

## 一、声明方式

```java
int[] a;
int b[];
```

- 声明数组时不能指定其长度

## 二、初始化

### 2.1动态初始化

数组定义与为元素分配空间和初始化分开操作

```java
int[] a;
a = new int[3];
```

### 2.2静态初始化

数组定义的同时为数组分配空间和初始化

```java
int[] a = {1,2,3}
```

## 三、数组对象的创建

### 3.1元素为基本数据类型

![1555216006921](http://ww2.sinaimg.cn/large/006tNc79ly1g4zlidnxe5j30yx0jvdh4.jpg)

![1555216387175](http://ww1.sinaimg.cn/large/006tNc79ly1g4zlic5cn1j30o80eqq3q.jpg)

### 3.2元素为引用类型

![1555216837672](http://ww3.sinaimg.cn/large/006tNc79ly1g4zliau9c2j30s00fdaar.jpg)

![1555217032611](http://ww3.sinaimg.cn/large/006tNc79ly1g4zlid9drbj30rt0esmy5.jpg)

![1555217167172](http://ww1.sinaimg.cn/large/006tNc79ly1g4zlicmky1j30s30evmy7.jpg)

## 四、数组拷贝

### 4.1两个变量引用同一个数组

- 浅拷贝

```java
 int[] smallPrimes = {2,3,4,5};
 int[] luckyNumber = smallPrimes;
 luckyNumber[1] = 99;
 smallPrimes[0] = 1;
 System.out.println(Arrays.toString(smallPrimes));
 System.out.println(Arrays.toString(luckyNumber));
//[1, 99, 4, 5]
//[1, 99, 4, 5]
```

![1555339373567](http://ww1.sinaimg.cn/large/006tNc79ly1g4zlibn6alj30ug0a7dge.jpg)

### 4.2 System.arraycopy及其包装方法

#### 4.21 System.arraycopy

- 对于引用类型是浅拷贝
- 需要复制到一个已经分配内存单元的数组。

```java
//arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
//原数组， 原数组的开始位置， 目标数组， 目标数组的开始位置， 拷贝个数）
 Employee[] arr1 = { new Employee("小明",12),
                     new Employee("小黄",11)};
 Employee[] arr2 = new Employee[arr1.length];
 System.arraycopy(arr1,0,arr2,1,1);
 System.out.println("arr1"+Arrays.toString(arr1));
 System.out.println("arr2"+Arrays.toString(arr2));
 System.out.println("************************");
 arr1[0].setName("小丽");
 System.out.println("arr1"+Arrays.toString(arr1));
 System.out.println("arr2"+Arrays.toString(arr2));
/**
 *arr1[Employee{name='小明', age=12}, Employee{name='小黄', age=11}]
 *arr2[null, Employee{name='小明', age=12}]
 * *************************
 *arr1[Employee{name='小丽', age=12}, Employee{name='小黄', age=11}]
 *arr2[null, Employee{name='小丽', age=12}]
* */
```

#### 4.22 Arrays.copyOf

- 对于引用类型是前拷贝
- 底层其实是用的System.arraycopy 
- 使用该方法无需我们事先使用new关键字对对象进行内存单元的分配

```java
	//copyOf(T[] original, int newLength)
	//（原数组，拷贝的个数）
    public static void main(String[] args){
        Employee[] arr1 = { new Employee("小明",12),
                            new Employee("小黄",11)};
        Employee[] arr2 = Arrays.copyOf(arr1,1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

//[Employee{name='小明', age=12}, Employee{name='小黄', age=11}]
//[Employee{name='小明', age=12}]
//**************
//[Employee{name='小丽', age=12}, Employee{name='小黄', age=11}]
//[Employee{name='小丽', age=12}]
```



#### 4.23 Arrays.copyOfRange

- 对于引用变量是浅拷贝
- 底层其实是用的System.arraycopy ,只不过封装了一个方法

```java
	//copyOfRange(T[] original, int from, int to)
	//（原数组，开始位置，拷贝的个数）
	//截取从下标为from开始,到下标为（to-1）结束
	//赋值整个数组（a[],0,a.length）
    public static void main(String[] args){
        Employee[] arr1 = { new Employee("小明",12),
                            new Employee("小黄",11)};
        Employee[] arr2 = Arrays.copyOfRange(arr1,0,1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println("**************");
        arr1[0].setName("小丽");
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
/**
 * [Employee{name='小明', age=12}, Employee{name='小黄', age=11}]
 * [Employee{name='小明', age=12}]
 * **************
 * [Employee{name='小丽', age=12}, Employee{name='小黄', age=11}]
 * [Employee{name='小丽', age=12}]
 */
```



### 4.3 clone 方法

#### 4.31基本数据类型+String

- 深拷贝

```java

    public static void main(String[] args) {

        int[] array1 = new int[]{1, 2, 8, 7, 6};
        int[] array2 = new int[array1.length];
        array2 = array1.clone();

        System.out.println("array1 = " + Arrays.toString(array1));
        System.out.println("array2 = " + Arrays.toString(array2));
        System.out.println("======================");

        array2[0] = 100;
        System.out.println("array1 = " + Arrays.toString(array1));
        System.out.println("array2 = " + Arrays.toString(array2));
    }

    //array1 = [1, 2, 8, 7, 6]
    //array2 = [1, 2, 8, 7, 6]
    //======================
    //array1 = [1, 2, 8, 7, 6]
    //array2 = [100, 2, 8, 7, 6]
```

![1555340127085](http://ww3.sinaimg.cn/large/006tNc79ly1g4zlib7xegj30ot0bl75h.jpg)

#### 4.32引用类型

- 浅拷贝

  ```java
  Employee[] employees1 = {new Employee("name",12)};
  Employee[] employees2 = employees1.clone();
  employees1[0].setName("name2");
  System.out.println(employees1[0]);
  System.out.println(employees2[0]);
  ```

![1555341772495](http://ww3.sinaimg.cn/large/006tNc79ly1g4zlie11xsj30sr0d50ux.jpg)