# java新版本特性

### java9

java9中重要改变

- 模块化系统

- jShell命令

语法改变：

接口私有方法

Diamond Operator 的使用升级

语法改进：try语句

api改变：

String 存储结构变更

便利的集合特性: of

增强的Stream API

全新的http客户端API

Deprecated的相关API

其它：

JAVA动态编译器



#### API改变

String 存储结构变更

便利的集合特性: of

增强的Stream API

全新的http客户端API

Deprecated的相关API

### java9中的重点

#### 模块化系统

##### 为什么要引入模块化系统

1. java运行环境的膨胀和臃肿，每次JVM启动的时候，至少会有30-60M的内存加载，主要原因是JVM需要加载rt.jar
2. 当代代码库越来越大，创建复杂，不同版本类库交叉依赖导致让人头疼的问题，这些都阻碍了开发和运行效率的提升
3. 很难对代码进行封装，每一个公共类都可以被类路径之下任何其它的公共类访问到， 这样就会导致无意中使用了并不想被公开方位的api。

##### 模块化是什么？

本质上讲，模块（module） 的概念，其实就是package 外再包一层，也就是说，用模块来管理各个package,通过声明某个package暴露，不声明默认就是隐藏

##### 实现目标：

1. 减少内存开销

2. 只需必要的模块，而非全部jdk模块，简化各种类库和大型应用的开发和维护

3. 开进java se平台，事情不同大小的计算设备

4. 改进安全性，可维护性，提高性能

   



#### jShell命令

REPL（read-evaluate-print-loop）工具：

直接在命令行可以执行java代码.

```
 ~/WorkSpace/laboratory/java-demos   master ●  jshell
|  Welcome to JShell -- Version 13.0.2
|  For an introduction type: /help intro

jshell> System.out.println("hello world")
hello world

jshell>
```

/help 查看帮助

/imports 查看导入哪些包

/vars

/methods

/edit  写脚本

tab进行补全

#### 语法改变

#### 接口私有方法

Java 8 中 规定接口中除了抽象方法之外，还可以定义静态方法和默认的方法
java9 中，接口中可以定义私有方法

#### 语法改进：try语句

java8可以实现资源的自动关闭， 要求执行后关闭的所有资源必须在try子句中初始化

```
    void testTryCatch() {
        try(InputStreamReader reader = new InputStreamReader(System.in)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

Java9：可以将资源在外面声明

```
    void testJava9TryCatch() {
        InputStreamReader reader = new InputStreamReader(System.in);
        InputStreamReader reader2 = new InputStreamReader(System.in);
        try(reader;reader2) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```



#### API改变

#### String 存储结构变更

底层由char[] 数组 变成byte数组

#### 便利的集合特性: of

快速创建只读集合

Map.of()
List.of()

Set.of()

#### input stream 新方法

```
    void transferTo() throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("hello.txt");
        final FileOutputStream os = new FileOutputStream("helloCopy.txt");

        try(is; os) {
            is.transferTo(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```



#### 增强的Stream API

Java9 四个新的方法：

takeWhile（）返回从开头开始的尽量多的元素。

dropWhile()  与takeWhile相反，返回剩余的元素。

ofNullable() 创建一个单元素stream，可以包含一个非空元素，也可以创建一个空stream。

Optional 类新增stream方法：

Optional.stream();



### Java10 新特性

286: Local-Variable Type Inference 局部变量类型推断
296: Consolidate the JDK Forest into a Single Repository JDK库的合并
304: Garbage-Collector Interface 统一的垃圾回收接口
307: Parallel Full GC for G1 为G1提供并行的Full GC
310: Application Class-Data Sharing 应用程序类数据（AppCDS）共享
312: Thread-Local Handshakes ThreadLocal握手交互
313: Remove the Native-Header Generation Tool (javah) 移除JDK中附带的javah工具
314: Additional Unicode Language-Tag Extensions 使用附加的Unicode语言标记扩展
316: Heap Allocation on Alternative Memory Devices 能将堆内存占用分配给用户指定
的备用内存设备
317: Experimental Java-Based JIT Compiler 使用基于Java的JIT编译器
319: Root Certificates 根证书
322: Time-Based Release Versioning 基于时间的发布版本

#### Local-Variable Type Inference 局部变量类型推断

编译器在编译时会自动推断类型



copyof方法：如何已经是一个只读集合 返回只读集合，如果不是只读集合，新建一个。



### Java11 新特性（LTS Long-Term-Support）

1. ZGC 
2. Epsilon

oracle javase support