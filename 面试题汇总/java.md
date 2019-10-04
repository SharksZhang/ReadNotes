1. throw和throws的异同
2. 实现一个equals方法？
3. default方法是什么意思？
4. hashCode方法作用？
5.  完成一次oom的排查
6. ConcurrentHashMap 的实现详解
7. JVM内存模型内存模型详解
8. 垃圾回收的过程
9. FullGC是否停顿用户线程
10. 8G文件存储可能重复的QQ号，判断某个号码是否出现，限制内存1G，接上问，求出出现次数TOP10的号码

11. 内部锁和显示锁的区别？
    1. synchronized 自动加锁解锁。 lock 手动加锁解锁
    2. synchronized是悲观锁，lock是乐观锁，用的是CAS的方式所以效率更高。
12. java如何避免死锁
    1. 死锁的四个条件
    2. 使用Jstack命令 和jconsole工具
    3. Lock接口提供了`boolean tryLock(long time, TimeUnit unit) throws InterruptedException`方法，该方法可以按照固定时长等待锁，因此线程可以在获取锁超时以后，主动释放之前已经获得的所有的锁。
13. 为什么选ConcurrentHashMap，不选Hashtable
    1. hashTable只有一个锁，而concurrentHashMap使用了分段锁， 这样能够大大的减少锁冲突的概率。
14. ConcurrentHashMap和HashTable迭代器在使用时你是怎么抉择的
15. 分拆锁是什么，怎么用的，为什么要这样做
16. 频繁发生FullGC，怎么排查
17. 三个线程1,2,3，想让1,2尽可能多执行，3少执行，有哪些方法
18. notify和notifyAll唤醒线程的顺序是怎样的
19. 同步队列和等待队列

