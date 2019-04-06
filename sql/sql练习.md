# sql练习

## 一、基础

```sql
select * from websites where name='Google' or name='Facebook'

select * from websites where name not in ('Google','Facebook')

select  name,url,alexa,country from websites order by country ASC,name desc

insert into websites (name,url,alexa,country) values ('京东','https://jingdong.com','45','CN')

update websites set name='小米',url='https://xiaomi.com' where name='京东'
/*执行没有 WHERE 子句的 UPDATE 要慎重，再慎重。*/

delete from websites where id=6
```

