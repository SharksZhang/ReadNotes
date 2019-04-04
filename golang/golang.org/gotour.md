###gotour

[TOC]

### Methods and interfaces

#### 方法
Go 没有类。不过你可以为结构体类型定义方法。

方法就是一类带特殊的 **接收者** 参数的函数。

方法接收者在它自己的参数列表内，位于 func 关键字和方法名之间。
```
func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

```


方法只是个带接收者参数的函数,但是带指针参数的函数和以指针为接受者的方法还是有区别的。带指针参数的函数必须接受一个指针。而以指针为接受者的方法被调用时，接受者及能为值也能为指针。以值为参数的函数和以值为接受者的方法也是相同的。

```
func Abs(v Vertex) float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}
```


##### 为非结构体类型声明方法
你只能为在同一包内定义的类型的接收者声明方法，而不能为其它包内定义的类型（包括 int 之类的内建类型）的接收者声明方法。

```
type MyFloat float64

func (f MyFloat) Abs() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}
```

##### 接受者类型
接受者有两种类型，指针接受者和值接受者。指针接收者的方法可以修改接收者指向的值，值接收者的方法会对原始值的副本进行操作。方法经常需要修改它的接收者，指针接收者比值接收者更常用。

同时，使用值接受者可以调用指针接受者的方法，使用指针接受者也可以调用值接受者的方法。golang会进行自动的转换。如果是指针，调用值接受者的方法，会先取值，然后使用值的副本再调用方法，不会改变接受者的值。而用值调用指针接受者的方法，会先取指针，所以会改变接受者的值。

```
package main

import (
"fmt"
)

type Vertex struct {
	X, Y float64
}

func (v *Vertex) Scale_Pointer_reciever(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func (v Vertex) Scale_value_reciever(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}


func main() {
	v_pointer := &Vertex{3, 4}
	fmt.Printf("Before v_pointer.Scale_Pointer_reciever: %+v_pointer\n", v_pointer)
	v_pointer.Scale_Pointer_reciever(5)
	fmt.Printf("After v_pointer.Scale_Pointer_reciever: %+v_pointer\n", v_pointer)
	fmt.Println()

	v_value := Vertex{3, 4}
	fmt.Printf("Before v_value.Scale_Pointer_reciever: %+v_pointer\n", v_value)
	v_value.Scale_Pointer_reciever(5)
	fmt.Printf("After v_value.Scale_Pointer_reciever: %+v_pointer\n", v_value)

	fmt.Printf("call Scale_value_reciever\n")
	fmt.Println()
	v1_pointer := &Vertex{3, 4}
	fmt.Printf("Before v1_value.Scale_value_reciever: %+v_pointer\n", v1_pointer)
	v1_pointer.Scale_value_reciever(5)
	fmt.Printf("After v1_value.Scale_value_reciever: %+v_pointer\n", v1_pointer)

	fmt.Println()
	v1_value := Vertex{3, 4}
	fmt.Printf("Before v1_value.Scale_value_reciever: %+v_pointer\n", v1_pointer)
	v1_value.Scale_value_reciever(5)
	fmt.Printf("After v1_value.Scale_value_reciever: %+v_pointer\n", v1_pointer)

}

打印：
Before v_pointer.Scale_Pointer_reciever: &{X:3 Y:4}_pointer
After v_pointer.Scale_Pointer_reciever: &{X:15 Y:20}_pointer

Before v_value.Scale_Pointer_reciever: {X:3 Y:4}_pointer
After v_value.Scale_Pointer_reciever: {X:15 Y:20}_pointer
call Scale_value_reciever

Before v1_value.Scale_value_reciever: &{X:3 Y:4}_pointer
After v1_value.Scale_value_reciever: &{X:3 Y:4}_pointer

Before v1_value.Scale_value_reciever: &{X:3 Y:4}_pointer
After v1_value.Scale_value_reciever: &{X:3 Y:4}_pointer

```

**使用指针接收者的原因：**
1. 方法能够修改其接收者指向的值。
2. 这样可以避免在每次调用方法时复制该值。若值的类型为大型结构体时，这样做会更加高效

#### interface

##### 接口声明和实现
接口类型 是由一组方法签名定义的集合，接口类型的变量可以保存任何实现了这些方法的值。类型通过实现一个接口的所有方法来实现该借口，无需显示声明。
##### 接口的值
在内部，接口值可以看做包含值和具体类型的元组：(value, type)
接口值保存了一个具体底层类型的具体值。

接口值调用方法时会执行其底层类型的同名方法。
```
package main

import (
	"fmt"
	"math"
)

type I interface {
	M()
}

type T struct {
	S string
}

func (t *T) M() {
	fmt.Println(t.S)
}

type F float64

func (f F) M() {
	fmt.Println(f)
}

func main() {
	var i I

	i = &T{"Hello"}
	describe(i)
	i.M()

	i = F(math.Pi)
	describe(i)
	i.M()
}

func describe(i I) {
	fmt.Printf("(%v, %T)\n", i, i)
}


print：

(&{Hello}, *main.T)
Hello
(3.141592653589793, main.F)
3.141592653589793

```

##### 底层值为 nil 的接口值和nil接口值
即便接口内的具体值为 nil，方法仍然会被 nil 接收者调用。

在一些语言中，这会触发一个空指针异常，但在 Go 中通常会写一些方法来优雅地处理它（如本例中的 M 方法）。这个是因为保存了 nil 具体值的接口其自身并不为 nil
```
package main

import "fmt"

type I interface {
	M()
}

type T struct {
	S string
}

func (t *T) M() {
	if t == nil {
		fmt.Println("<nil>")
		return
	}
	fmt.Println(t.S)
}

func main() {
	var i I

	var t *T
	i = t
	describe(i)
	i.M()

	i = &T{"hello"}
	describe(i)
	i.M()
}

func describe(i I) {
	fmt.Printf("(%v, %T)\n", i, i)
}

```
**nil 接口值**
nil 接口值既不保存值也不保存具体类型。

为 nil 接口调用方法会产生运行时错误，因为接口的元组内并未包含能够指明该调用哪个 具体 方法的类型
```

package main

import "fmt"

type I interface {
	M()
}

func main() {
	var i I
	describe(i)
	i.M()
}

func describe(i I) {
	fmt.Printf("(%v, %T)\n", i, i)
}

```

##### 空接口

指定了零个方法的接口值被称为 *空接口：*

interface{}
空接口可保存任何类型的值。（因为每个类型都至少实现了零个方法。）

空接口被用来处理未知类型的值。例如，fmt.Print 可接受类型为 interface{} 的任意数量的参数。

##### 类型断言

类型断言 提供了访问接口值底层具体值的方式。

t := i.(T)
该语句断言接口值 i 保存了具体类型 T，并将其底层类型为 T 的值赋予变量 t。

若 i 并未保存 T 类型的值，该语句就会触发一个恐慌。

为了 判断 一个接口值是否保存了一个特定的类型，类型断言可返回两个值：其底层值以及一个报告断言是否成功的布尔值。

t, ok := i.(T)
若 i 保存了一个 T，那么 t 将会是其底层值，而 ok 为 true。

否则，ok 将为 false 而 t 将为 T 类型的零值，程序并不会产生恐慌。

请注意这种语法和读取一个映射时的相同之处。

##### 类型选择
类型选择 是一种按顺序从几个类型断言中选择分支的结构。

类型选择与一般的 switch 语句相似，不过类型选择中的 case 为类型（而非值）， 它们针对给定接口值所存储的值的类型进行比较。
```
switch v := i.(type) {
case T:
    // v 的类型为 T
case S:
    // v 的类型为 S
default:
    // 没有匹配，v 与 i 的类型相同
}
```


类型选择中的声明与类型断言 i.(T) 的语法相同，只是具体类型 T 被替换成了关键字 type。

此选择语句判断接口值 i 保存的值类型是 T 还是 S。在 T 或 S 的情况下，变量 v 会分别按 T 或 S 类型保存 i 拥有的值。在默认（即没有匹配）的情况下，变量 v 与 i 的接口类型和值相同

```
package main

import "fmt"

func do(i interface{}) {
	switch v := i.(type) {
	case int:
		fmt.Printf("Twice %v is %v\n", v, v*2)
	case string:
		fmt.Printf("%q is %v bytes long\n", v, len(v))
	default:
		fmt.Printf("I don't know about type %T!\n", v)
	}
}

func main() {
	do(21)
	do("hello")
	do(true)
}
```

### Concurrency
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

##### sync.Mutex

若我们只是想保证每次只有一个 goroutine 能够访问一个共享的变量，从而避免冲突？

这里涉及的概念叫做 _互斥（mutual_exclusion）_ ，我们通常使用 _互斥锁（Mutex）_ 这一数据结构来提供这种机制。

Go 标准库中提供了 sync.Mutex 互斥锁类型及其两个方法：

Lock
Unlock
我们可以通过在代码前调用 Lock 方法，在代码后调用 Unlock 方法来保证一段代码的互斥执行。参见 Inc 方法。

我们也可以用 defer 语句来保证互斥锁一定会被解锁


