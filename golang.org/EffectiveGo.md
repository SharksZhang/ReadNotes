## go语言官网

### Effective go

#### Introduction

to write Go well, it's important to understand its properties and idioms(习惯用法). It's also important to know the established conventions for programming in Go, such as naming, formatting, program construction, and so on.

### Commentary



todo



### Names

#### Package names

1. 包名要短， 最好是小写，一个单词。因为引用包的地方需要使用包名。

2. 包名并不需要唯一，在引用包的地方可以使用其它名字

3. 包名最好是文件夹名，使用包结构帮助你选择一个好名字



#### Getters

go语言没有提供自动的get和set方法，但是推荐这样做。

1. get方法不需要把Get写入。

2. set方法需要加上set



```

	owner := obj.Owner()

	if owner != user {

    obj.SetOwner(user)

}

```



#### Interface names

1.一个方法的接口一般命名为 方法名+er



#### MixedCapsc

go语言推荐使用MixedCaps或者mixedCaps，不推荐使用下划线连接多个单词。







