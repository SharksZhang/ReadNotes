### 1. Kotlin优势

1. 全面支持Lambda 表达式
2. 数据类（data classes）
3. 函数字面量和内联函数（Function literals & inline functions）
4. 函数扩展（Extension functions）
5. 空安全（Null safety）
6. 智能转换(Smart casts)
7. 字符串模板（String templates）
8. 主构造函数（Primary constructors）
9. 类委托（class delegation）?
10. 类型推断（Type inference）
11. 单例（Singletons）
12. 声明点变量（Delearation-site variance）?
13. 区间表达式（Range expressions ）

### compiler

安装

```
brew update
brew install kotlin
```

### main

```
class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}

fun main(args: Array<String>) {
    println(App().greeting)
}
```

### 语法

#### 输入和输出

### 



### 问题记录

1. kotlin 的构造函数为什么要区分主构造函数

2. 如何初始化一个gradle项目并设置kotlin 和 jvm版本

3. ```
   kotlin("jvm") version "1.4.0" 和     id("org.jetbrains.kotlin.jvm") version "1.3.71" 写法的区别
   ```

4. constructor 中的parameter 和field的区别

