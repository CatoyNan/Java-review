# 核心Api

- BeanFactory:用于生成任意bean
- ApplicationContext:BeanFactory的子接口，功能更加强大
  - 实现类1：ClassPathXmlApplicationContext用于加（classPath运行状态）（resource开发状态）下的文件
  - 实现类2：FileSystemXmlApplicationContext加载xml运行时位置下（项目目录下）

