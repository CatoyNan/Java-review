### maven default中部分生命周期

![image-20191006195811171](/Users/admin/Desktop/document/学习/Java-review/maven/assets/image-20191006195811171.png)

| 阶段          | 处理     | 描述                                                     |
| :------------ | :------- | :------------------------------------------------------- |
| 验证 validate | 验证项目 | 验证项目是否正确且所有必须信息是可用的                   |
| 编译 compile  | 执行编译 | 源代码编译在此阶段完成                                   |
| 测试 Test     | 测试     | 使用适当的单元测试框架（例如JUnit）运行测试。            |
| 包装 package  | 打包     | 创建JAR/WAR包如在 pom.xml 中定义提及的包                 |
| 检查 verify   | 检查     | 对集成测试的结果进行检查，以保证质量达标                 |
| 安装 install  | 安装     | 安装打包的项目到本地仓库，以供其他项目使用               |
| 部署 deploy   | 部署     | 拷贝最终的工程包到远程仓库中，以共享给其他开发人员和工程 |

详见https://www.runoob.com/maven/maven-build-life-cycle.html

​	

### 插件目标

每个插件都包含若干个目标，每个目标就相当于一个功能，如：`surefile:test`表示  `maven-surefile-plugin`插件中的test目标。生命周期中的某些阶段都是由插件去完成的，通过将生命周期与插件中的目标进行绑定来实现。

| 生命周期阶段 | 插件目标                 |
| :----------- | :----------------------- |
| Pre-clean    |                          |
| Clean        | Maven-clean-plugin:clean |
| Post-clean   |                          |



### maven中的常见标签

##### `<resource>` todo