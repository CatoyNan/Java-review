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

### 2.2应用举例

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

