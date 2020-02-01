###golang中的defer,panic,和recover

####defer
在golang中，defer是一个常用的操作，一般用于在函数退返回执行释放资源的操作。defer 操作可以被理解为一个压栈和出栈的操作。
defer 有如下三个规则。

1. 被defer 的函数参数是在函数被压栈时的值。在下面这个例子中，println压栈时i为0，最后函数返回前打印出i为0。

    ```
    func a()  {
        i := 0
        defer fmt.Println(i)
        i++
        return
    }
    输出：
    0
    ```

2. 由于defer 可以看做一个栈的操作，所以defer 是后进先出的。所以如下例子输出 3210
```
func b() {
    for i := 0; i < 4; i++ {
        defer fmt.Print(i)
    }
}
```

3. defer可以操作外围函数的返回值。下面面示例返回2.
```
func c() (i int) {
    defer func() { i++ }()
    return 1
}
```

####Panic and recover
panic是一个内置的函数。当panic函数被调用时，函数会返回，该过程一直向上移动，直到当前goroutine中的所有函数都返回，此时程序崩溃。Panics可以直接通过panic（）函数产生，也会被运行时错误产生，例如数组越界。

Recover是一个内置函数，用于恢复被panic的协程。Recover 只有在deffer的函数中才有效。如果当前goroutine处于恐慌状态，则对recover的调用将捕获 panic（）中的值并恢复正常执行。recover获得的值将是panic中传入的值。


```
package main

import "fmt"

func main() {
    f()
    fmt.Println("Returned normally from f.")
}

func f() {
    defer func() {
        if r := recover(); r != nil {
            fmt.Println("Recovered in f", r)
        }
    }()
    fmt.Println("Calling g.")
    g(0)
    fmt.Println("Returned normally from g.")
}

func g(i int) {
    if i > 3 {
        fmt.Println("Panicking!")
        panic(fmt.Sprintf("%v", i))
    }
    defer fmt.Println("Defer in g", i)
    fmt.Println("Printing in g", i)
    g(i + 1)
}

```

以上示例输出如下: recover（）函数捕获到的是panic函数传入的4
```
Calling g.
Printing in g 0
Printing in g 1
Printing in g 2
Printing in g 3
Panicking!
Defer in g 3
Defer in g 2
Defer in g 1
Defer in g 0
Recovered in f 4
Returned normally from f.
```

如果删除recover，输出如下：协程直接退出
```
Calling g.
Printing in g 0
Printing in g 1
Printing in g 2
Printing in g 3
Panicking!
Defer in g 3
Defer in g 2
Defer in g 1
Defer in g 0
panic: 4
 
panic PC=0x2a9cd8
[stack trace omitted]
```





