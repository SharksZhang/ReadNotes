# nodejs introduction

nodejs trainning. Course 1.

## Whtat is node.js

- not a language
- not a framework

用来执行js代码的运行环境.使用chrome v8 Engine

A javascript runtime built on chrome's v8 javascript engine



## How node.js work

![Screen Shot 2020-11-17 at 4.42.47 PM](Screen%20Shot%202020-11-17%20at%204.42.47%20PM.png)

### keywords

- Single thread

- Non-blocking I/o
- Event Driven
  - Event
  - Callback
  - Observer Pattern]



### event driven

- ![Screen Shot 2020-11-17 at 4.49.19 PM](Screen%20Shot%202020-11-17%20at%204.49.19%20PM.png)

### What is node.js Module

Module 是为了解决 js 全局变量污染问题，比较早的时间，

File/Folder As Module

- Commonjs
- Module Scope
- load Multiple Times, But Run once. 

如何引用

- require
- exports(module.export 的引用)
- module.export

模块的类型

- Build-in module
- file module

How to load medule

- Highest Priority Build-in Module
- Base on path - relative/absolute path
- Load from node_modules/ folder



工具

- npm
- Yarn



### Third

问题

模块系统是nodejs本身提供的功能， java script有没有本身的模块系统。浏览器端有没有模块系统





## ECMAScript



变量声明： let/const。只有声明以后才能使用

![Screen Shot 2020-11-17 at 5.03.22 PM](Screen%20Shot%202020-11-17%20at%205.03.22%20PM.png)

解构赋值

模板字符串  反引号加变量

函数扩展

- 参数默认值
- rest 参数
- 箭头函数
  - 解决了很多this相关的问题



ES Module

Export 用户规定模块的对外接口

import 用来引入模块



推荐使用export 和import





