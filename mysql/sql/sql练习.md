# sql练习

## 一、基础

```sql
select * from websites where name='Google' or name='Facebook'

select * from websites where name != 'Google' and id != 2

select * from websites where name not in ('Google','Facebook')

select  name,url,alexa,country from websites order by country ASC,name desc

insert into websites (name,url,alexa,country) values ('京东','https://jingdong.com','45','CN')

update websites set name='小米',url='https://xiaomi.com' where name='京东'
/*执行没有 WHERE 子句的 UPDATE 要慎重，再慎重。*/

delete from websites where id=6
```

## 二、Limit

```sql
/*从第四行开始读取三条记录，下标从0开始*/
SELECT * FROM websites LIMIT 3 OFFSET 3
/*简化版* 从下标0开始读取三行/
SELECT * FROM websites LIMIT 0,3
```

## 三、Distinct

```sql
/*select distinct country from websites*/

/*除非指定的两列的属性都相同，否则都会列出来*/
/*select distinct name,country from websites*/
```

## 四、表连接

### 4.1内连接

​		**只显示表里都匹配的行**

- ```sql
  select * from t1 inner join t2 where t1.i1=t2.i2;
  select t1.*,t2.* from t1 inner join t2 where t1.i1=t2.i2;
  select t1.*,t2.* from t1,t2 where t1.i1=t2.i2;
  ```

- 建议不免使用逗号运算符

- 若没有where限制，将生成笛卡尔积

- where 可以被on  代替

  ```
  select * from t1 inner join t2 on t1.i1=t2.i2;
  ```

- 可以对连接表里的列进行限定

  ```sql
  select t1,t2.a from t1 inner join t2
  ```

- 可是使用别名

  ```sql
  select t1,m.a from t1 inner join t1 as m where t1.col1>m.col1
  ```

  

### 4.2外连接

​		**可以把其中一个表在另一个表里没有匹配的行也显示出来**。

- left join:会把左表里的在右表里未匹配上的行也显示出来

  | L1   | L2    | R1   |
  | ---- | ----- | ---- |
  | 1    | Name1 | Stu1 |
  | 2    | Name2 | Null |
  | 3    | Name3 | Null |

  right join相反

- 试用场景：

  - 还没有为哪些顾客指派代表

  - 哪些库存的商品一件也没卖出

  - 缺失了哪些值

    ```sql
    select t1.* from t1 left join t2 on t1.i1=t2.i2 where t2.i2 is null
    ```
