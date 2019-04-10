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

