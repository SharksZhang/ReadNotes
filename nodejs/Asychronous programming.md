## Asynchronous Programing

- 异步
- 非阻塞
- 回调

### javaScript Feature

- Synchronous and asynchronous
- Blocking and Non-Blocking
- Event Loop

### Asynchronous solution

#### Callback

Pro:

- Easy to understanding
- Node.js API:

Cons:

- Not clear flow
- Only one callback in one task
- Synchronization
- Callback hell

#### Promise

ecmaScript 2015

- the Promise object is used for Asynchronous computations
- A promise represents a value which maybe avaliable now ...

![Screen Shot 2020-11-24 at 4.46.28 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%204.46.28%20PM.png)

![Screen Shot 2020-11-24 at 4.47.30 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%204.47.30%20PM.png)

Promise 的构造函数和then是同步的

##### notes：

nodejsapi中所有方法默认是异步的。

##### Promise others methods

- Promise.all
- Promise.allSettled
- Promise.race

##### Problem with promise:

- unknown the state before it resolved or rejected
- unknown the error message unless add callback
- can't cancel promise



#### Async/Await

- Async/await is final solution for Async programming, based on Promise
- Features:
  - No more callback hell
  - The experience of synchronous programming, the efficient of ...
- Async function
- Async/await keyword
- Async function always returns Promise
- can be handled by try/catch

Await 具有传染性，与这个方法相关的调用链都需要使用 awayt

底层是基于promise的



## Node.js Event Loop

- #### 什么是event loop

  ![Screen Shot 2020-11-24 at 5.09.06 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%205.09.06%20PM.png)

- v8 引擎提供Javascript运行环境，并使用其能够调用C++代码上下文。解释器
- node Bindings 由众多C++实现的插件组成
- LIBUV
  - 提供多种异步I/O库，并负责分配线程给不同的I/O。而分配I/o的逻辑的设计理念成为EventLoop



![Screen Shot 2020-11-24 at 5.14.11 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%205.14.11%20PM.png)

![Screen Shot 2020-11-24 at 5.19.12 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%205.19.12%20PM.png)

![Screen Shot 2020-11-24 at 5.41.45 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%205.41.45%20PM.png)

![Screen Shot 2020-11-24 at 5.46.41 PM](Asychronous%20programming.assets/Screen%20Shot%202020-11-24%20at%205.46.41%20PM.png)

timer里的回调相当于是一个同步调用，都看作是宏任务

- 为什么要有eventloop

  

- eventloop对列结构

- 对列优先级

