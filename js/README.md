# 一、async和await

### 1.1 async

async 函数（包含函数语句、函数表达式、Lambda表达式）会返回一个 Promise 对象，如果在函数中 `return` 一个直接量，async 会把这个直接量通过 `Promise.resolve()` 封装成 Promise 对象。

#### 使用方式

```javascript
// 方式一
async function hello() { return "Hello" };

// 方式二
let hello = async function() { return "Hello" };

// 方式三
let hello = async () => { return "Hello" };

```



### 1.2 await

它可以放在任何异步的，基于 promise 的函数之前。它会暂停代码在该行上，直到 promise 完成，然后返回结果值。相当于自动帮我们调用了promise.then(),把promise的执行结果返回

#### 使用方式

```javascript
function takeLongTime() {
    return new Promise(resolve => {
        setTimeout(() => resolve("long_time_value"), 1000);
    });
}

const v = await takeLongTime();// 相当于takeLongTime().then();
//区别： await直接返回了值，.then()返回了promise	
```

