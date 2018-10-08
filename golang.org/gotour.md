###gotour

####待完善
- [ ]time包
	- [ ] time.tick,
	- [ ] time.after

####goroutine
goroutine是由Go运行时管理的轻量型线程。goroutine 在相同的地址空间中运行，因此在访问共享的内存时必须进行同步。sync 包提供了这种能力，不过在 Go 中并不经常用到，因为还有其它的办法.

####Channels
信道是带有类型的管道，你可以通过它用信道操作符 <- 来发送或者接收值。
```
ch <- v    // 将 v 发送至信道 ch。
v := <-ch  // 从 ch 接收值并赋予 v。
```

和映射与切片一样，信道在使用前必须创建：

```
ch := make(chan int)
```
默认情况下，发送和接收操作在另一端准备好之前都会阻塞。这使得 Go 程可以在没有显式的锁或竞态变量的情况下进行同步。

```
x, y := <-c, <-c // 从 c 中接收
```
这种方式读必须在channel中有两个值才可以zzzzzzzz

channel可以是 带缓冲的。将缓冲长度作为第二个参数提供给 make 来初始化一个带缓冲的信道：

```
ch := make(chan int, 100)
```
仅当信道的缓冲区填满后，向其发送数据时才会阻塞。当缓冲区为空时，接受方会阻塞。

#####Channel的range和close

发送者可通过 close 关闭一个信道来表示没有需要发送的值了。接收者可以通过为接收表达式分配第二个参数来测试信道是否被关闭：若没有值可以接收且信道已被关闭，那么在执行完之后 ok 会被设置为 false。
```
v, ok := <-ch
```

注意：* 只有发送者才能关闭信道，而接收者不能。向一个已经关闭的信道发送数据会引发程序恐慌（panic）。

*还要注意：* 信道与文件不同，通常情况下无需关闭它们。只有在必须告诉接收者不再有值需要发送的时候才有必要关闭，例如终止一个 range 循环

##### select 语句
select 语句使一个 Goroutine可以等待多个通信操作。

select 会阻塞到某个分支可以继续执行为止，这时就会执行该分支。当多个分支都准备好时会随机选择一个执行。

当 select 中的其它分支都没有准备好时，default 分支就会执行



```
select {
case i := <-c:
    // 使用 i
default:
    // 从 c 中接收会阻塞时执行
}
```

使用for无线循环和select可以实现向一个管道中一直写入值，另一个管道用来控制for循环的结束。

```
package main

import (
	"time"
	"fmt"
)

/**
1.time.Tick会返回一个管道，管道中每个固定时间写入当前时间
2.time.After会在固定时间后在管道中写入时间
3.记得死循环要返回。
**/
func main() {
	tick := time.Tick(100 * time.Millisecond)
	after := time.After(500 * time.Millisecond)

	for {
		select {
		case i := <-tick:
			fmt.Println("tick:[%v]", i)
		case <-after:
			fmt.Println("boom boom boom")
			return
		default:
			fmt.Println("    .")
			time.Sleep(50 * time.Millisecond)
		}

	}

}


```
