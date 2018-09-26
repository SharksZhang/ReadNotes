##python学习手册笔记
####Chapter1.问答环节
######人们为何使用Python?
1. 软件质量。
2. 提高开发者效率
3. 程序可移植性
4. 标准库的支持
5. 组件集成
6. 享受乐趣

######python的缺点
1.执行速度慢

####chapter2.python如何运行程序。
######python解释器
1. python解释器是一个让其它程序运行起来的程序。
2. python会把源代码编译成字节码，然后给虚拟机使用。
3. python会用.pyc的后缀名保存python的字节码在源代码目录，下一次运行程序是，如果没有改过源代码，就会直接加载.pyc。

######python虚拟机(PVM)
1.pvm不是一个独立程序，不需要安装，是python程序的一部分。
2.pvm是迭代运行字节码的一个大循环


######python主要实现方式
1. cpython。标准的python实现方式。有c实现。
2. jython。与java编程语言集成。编译Python源代码，形成java字节码。
3. ironpython。。与.net与windows环境集成。

####chapter3.如何执行程序



