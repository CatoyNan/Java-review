## 一、mysql的启动和关闭

在安装目录的support-files里有个mysql.server脚本，可以控制mysql的启动和关闭

- mysql.server start 启动
- mysql.server stop 关闭

## 二、账户管理

- 密码强度设置

  ```sql
  set global validate_password.policy=0;//密码等级
  set global validate_password.length=1;//密码允许最短长度
  ```

  

- 创建账户

  ```sql
  create user 'xxx'@'xxx' identified by 'password';
  ```

- 权限分配

  ```
  
  ```

  