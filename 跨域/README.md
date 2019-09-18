## 一、同源策略

| URL                                                    | 说明                           | 是否允许通信                           |
| ------------------------------------------------------ | ------------------------------ | -------------------------------------- |
| http://www.a.com/a.js http://www.a.com/b.js            | 同一域名下                     | 允许                                   |
| http://www.a.com/lab/a.js http://www.a.com/script/b.js | 同一域名下不同文件夹           | 允许                                   |
| http://www.a.com:8000/a.js http://www.a.com/b.js       | 同一域名，不同端口             | 不允许                                 |
| http://www.a.com/a.js https://www.a.com/b.js           | 同一域名，不同协议             | 不允许                                 |
| http://www.a.com/a.js http://70.32.92.74/b.js          | 域名和域名对应ip               | 不允许                                 |
| http://www.a.com/a.js http://script.a.com/b.js         | 主域相同，子域不同             | 不允许                                 |
| http://www.a.com/a.js http://a.com/b.js                | 同一域名，不同二级域名（同上） | 不允许（cookie这种情况下也不允许访问） |
| http://www.cnblogs.com/a.js http://www.a.com/b.js      | 不同域名                       | 不允许                                 |

特别注意两点：

- 第一，如果是协议和端口造成的跨域问题“前台”是无能为力的，

- 第二：在跨域问题上，域仅仅是通过“URL的首部”来识别而不会去尝试判断相同的ip地址对应着两个域或两个域是否在同一个ip上。

## 二、解决方案

https://www.cnblogs.com/dujunfeng/p/8004273.html

## 三、CROS

由后端解决跨域问题，前端不需要作出改动

### 场景一：简单请求

##### 满足条件：

1. 请求方法是一下三种
   -  HEAD
   - GET
   - POST

2. HTTP的头信息不超出以下几种字段：
   - Accept
   - Accept-Language
   - Content-Language
   - Last-Event-ID
   - Content-Type：只限于三个值application/x-www-form-urlencoded、multipart/form-data、text/plain

两个条件只要有一个不满足就是非简单请求

##### 简单请求图示：