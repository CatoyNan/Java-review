# Redis

### 一、客户端基本操作

启动/关闭redis服务

```shell
redis-server
redis-cli SHUTDOWN
```

客户端链接服务

```
redis-cli -h localhost -p 6379
```





### 二、过期策略

- 定期扫描：每秒十次，取20个key，超过四分之一循环,默认不超过25ms。同一时间大量key过期会导致卡顿
- 惰性策略：客户端访问这个key的时候进行检查



### 三、内存淘汰策略

​	当 Redis 内存超出物理内存限制时，内存的数据会开始和磁盘产生频繁的交换 (swap)。交换会让 Redis 的性能急剧下降，对于访问量比较频繁的 Redis 来说，这样龟速的存取效率基本上等于不可用。 

#### 1.配置参数

```
maxmemory 
```

#### 2.策略

- noeviction 不会继续服务写请求 (DEL 请求可以继续服务)，读请求可以继续进行。这样
  可以保证不会丢失数据，但是会让线上的业务不能持续进行。这是默认的淘汰策略。 

- volatile-lru 尝试淘汰设置了过期时间的 key，最少使用的 key 优先被淘汰。没有设置过
  期时间的 key 不会被淘汰，这样可以保证需要持久化的数据不会突然丢失。 

- volatile-ttl 跟上面一样，除了淘汰的策略不是 LRU，而是 key 的剩余寿命 ttl 的值，ttl 
  越小越优先被淘汰。 

- volatile-random 跟上面一样，不过淘汰的 key 是过期 key 集合中随机的 key。 

- allkeys-lru 区别于 volatile-lru，这个策略要淘汰的 key 对象是全体的 key 集合，而不
  只是过期的 key 集合。这意味着没有设置过期时间的 key 也会被淘汰。 

- allkeys-random 跟上面一样，不过淘汰的策略是随机的 key。 

- volatile-xxx 策略只会针对带过期时间的 key 进行淘汰，allkeys-xxx 策略会对所有的 
  key 进行淘汰。如果你只是拿 Redis 做缓存，那应该使用 allkeys-xxx，客户端写缓存时
  不必携带过期时间。如果你还想同时使用 Redis 的持久化功能，那就使用 volatile-xxx 
  策略，这样可以保留没有设置过期时间的 key，它们是永久的 key 不会被 LRU 算法淘
  汰。 



### 四、redis-cli常用命令

#### 1.string

- set,重复赋值会覆盖
- get
- mget,获取所有给定key的值
- setnx(if not exists ),不存在才赋值。分布式锁的方案之一
- setex(set with expire),设置过期时间





### 五、reids数据类型

- string
  - 二进制安全，可以存储图片文件、json
  - 作为计数器
  - 单值缓存
  - 分布式锁
- list:相当于java的linkList
- hash:存储对象，存储对象时使用空间比string少。
- set:唯一无序
- zset:唯一有序



### 六、redis持久化机制

#### 1.快照(RDB)

#### 2.之追加文件(AOF)

默认不开启，通过配置参数`appendonly yes`开启。有三种方式：

```conf
appendfsync always    #每次有数据修改发生时都会写入AOF文件,这样会严重降低Redis的速度
appendfsync everysec  #每秒钟同步一次，显示地将多个写命令同步到硬盘
appendfsync no        #让操作系统决定何时进行同步
```



### 七、事务

`multi`、`exec`、`discard`

- Redis 的事务根本不能算「原子性」，而仅仅是满足了事务的

  

### 八、分布式锁

分布式锁需要注意的几个点：

- 锁无法被释放

  - 原因：加锁后redis服务器挂了

  - 解决方案：

- 多个线程会拿到同一把锁

  - 原因：锁会先因过期被释放，这时第一个业务还在执行，如果有第二个线程进来又会拿到锁。

  - 解决方案：

- A线程会释放B线程的锁
  - 原因：第一个业务还在执行，第二个线程拿到了锁，这时如果第一个业务执行完成，锁被删除
  - 解决方案：

