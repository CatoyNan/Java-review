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

  ```sql
  grant select,update,delete,insert on test.* to 'catoy'@'%';//给catoy用户赋予test库所有表的增删改查权限
  grant select on test.* to 'catoy'@'%';//给catoy用户赋予test库所有表的只读权限
revoke all on *.* from 'catoy'@'%';//撤销catoy对所有数据库的所有权限
  show grants;//现实当前用户的权限
  ```

![image-20190702110121068](/Users/admin/Library/Application Support/typora-user-images/image-20190702110121068.png)

## 三、字符集管理

- 现实当前字符集规则设置

  ```sql
  show variables like 'character\_set\_%';
  ```

  ![image-20190705144718310](/Users/admin/Library/Application Support/typora-user-images/image-20190705144718310.png)

- 设置字符集编码

  ```sql
  set names 'utf8';//等价于下列三句话
  
  set character_set_client = utf8;
  set character_set_results = utf8;
  set character_set_connection = utf8;
  
  
  ```

  

