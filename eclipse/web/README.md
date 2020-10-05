## eclipse 使用tomcat部署一个web项目

> #### 一、配置编译参数

打开项目配置的build path

![image-20200809120605669](README.assets/image-20200809120605669.png)



配置需要编译的目录和编译输出目录

![image-20200809120701148](README.assets/image-20200809120701148.png)



选择该项目需要依赖的其他项目

![image-20200809120737833](README.assets/image-20200809120737833.png)



选择项目需要依赖的第三方jar文件

![image-20200809120846779](README.assets/image-20200809120846779.png)



order and export 

**TODO**



> #### 二、配置发布参数

打开项目配置的Deployment Assembly

![image-20200809121229532](README.assets/image-20200809121229532.png)



配置需要复制到tomcat目录的文件目录，赋值过去的是已经编译好的class文件和其他配置文件

![image-20200809121417292](README.assets/image-20200809121417292.png)



tomcat的运行目录可以在配置文件中 配置

![image-20200809121547712](README.assets/image-20200809121547712.png)