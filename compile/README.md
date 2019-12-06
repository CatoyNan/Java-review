https://www.ibm.com/developerworks/cn/java/j-lo-jse64/index.html#list4

https://seanwangjs.github.io/2018/03/13/java-runtime-compile.html

### eg:

```java
 public static void main(String[] args) {
    //获取系统Java编译器
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    //获取Java文件管理器
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
    //定义要编译的源文件
    File file = new File("/path/to/file");
    //通过源文件获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个 JavaFileObject，也被称为一个汇编单元
    Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);
    //生成编译任务
    JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
    //执行编译任务
    task.call();
  }
```

