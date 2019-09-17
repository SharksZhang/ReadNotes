1. golang的变量加载顺序。

2. golang的array。

   在golang中， array包含长度就是一种类型

3. golang的基础数据类型

   ```
   bool
   string
   int int8 int16 int32 int64
   uint uint8 uint16 uint32 uint64 uintpr
   byte //alias for uint8
   rune //alias for int32,represents a unicode code point
   float32 float64
   complex64 complex128
   ```

   

4. golang切片的底层实现。

   golang 的切片可以认为是一个结构体，保存了当前切片的长度，底层数组开始位置的指针，以及切片的容量。切片的长度为当前切片中已经拥有的元素，切片的容量为 底层数组的长度减去切片开始的位置。

5. 在函数里传入切片的指针和切片的值有什么区别。

   在函数里传入切片的值不能直接修改切片的容量，传递指针可以直接修改。

6. 如何扩充一个长度小于容量的切片。

   ```
   func PtrSubtractOneFromLength(slicePtr *[]byte) {
       slice := *slicePtr
       *slicePtr = slice[0 : len(slice)+100]
   }
   ```

   

7. 内置函数new和make的区别。make函数如何初始化切片，初始化map。

   1. make只能用于 切片， map， chan
   2. new 返回一个对象的0值得指针

8. 内置函数copy

   1. the number of elements it copies is the minimum of the lengths of the two slices. 
   2. **The `copy` function also gets things right when source and destination overlap,** which means it can be used to shift items around in a single slice. Here's how to use `copy` to insert a value into the middle of a slice.

9. 内置函数append实现

   1. append函数在给数组添加元素时会判断底层数组是否存在和容量是否够用。如果不够用，会新建一个新的底层数组。
   2. 如果两个切片公用一个底层数组，对于前半部分切片增加长度会影响到使用后半部分数组的切片的值。所以最好不要公用同一个底层数组。

10. 空切片和长度为0的切片的区别

    空切片的底层数组为空。长度为0的切片底层数组不为空

11. 字符串的实现。
    1. 字符串的底层实现是一个只读的比特切片。
    2. 使用for range去迭代，会使用utf-8去解码。使用长度去迭代，会得到二进制。
    3. Those sequences represent Unicode code points, called runes.

12. golang map

​	golang中没有set，需要使用map实现

13. go语言的错误处理

    1. 使用多返回值进行处理。
    2. 只要实现了error接口的都是错误。

14. golang的并发调度模型

15. 什么是goroutine，与线程和进程的对比。

    1. 创建时默认的stack的大小
       1. jdk5以后 java thread stack 默认为 1m
       2. GROUTINE 的stack初始化为2k

    2. 和 KSE(kernel Space Entity)的对应关系
       1. java Thread 是1：1
       2. groutine 是M：N

16. golang共享内存并发机制

    1. sync.Mutex
    2. sync.RWmutex
    3. waitGroup  用来阻塞主携程

17. golang的CSP（communicating sequential processed）并发机制

    1. 使用channel进行并发控制

       1. channel总共有三种，容量为0的channel，容量为1的channel，容量为n的channel

       2. 任务取消可以通过 关闭channel通知。

          1. ```
             v, ok <- ch; ok 为bool值， true表示正常接收， false表示关闭，v为该类型的0值
             ```

             

18. 在golang中任务取消的方式
    1. 共享方式，通过一个变量，判断变量的值来确定任务是否要取消
    2. 通道方式：通过关闭通道，通知所有的任务关闭
    3. 使用context包。传递context停止子协程
19. 典型并发任务的实现方式。
    1. 只运行一次： 使用sync.once
    2. 运行制定次数： 使用chan,指定次数。
    3. 所有任务完成
       1. 使用chan
       2. 使用waitgroup
20. sync.Pool对象缓存
21. 反射？
22. json解析的实现？
23. 如何对golang进行性能调优
    1. runtime pprof 
       1. http方式
       2. 文件中打印方式。

24. sync.Map实现原理
25. StringBuilder实现原理
26. chan的底层实现
27. 接口的底层实现，
28. golang的io
29. gpm模型
30. 内存布局
31. cgo
32. 内存管理
33. gc
34. 错误处理方式
35. context包
36. golang run-time包
37. golang和其他语言相比有什么优势？
38. 静态语言有什么优势？
39. golang gmp模型怎么调度的，一个goroutine sleep了，操作系统是怎么唤醒的