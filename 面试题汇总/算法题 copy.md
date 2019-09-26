##### 算法题

[TOC]



##### 算法复习思路梳理

1. 本周刷完面试简单部分题
2. 对薄弱和困难方面进行专项突破
   1. 困难部分
      1. dp
      2. 回溯
   2. 薄弱部分加强
      1. 排序
      2. 数学部分
3. 学习两个课程，9.11 

##### 常见基础题

1. 最大公约数
2. 最小公倍数
3. 随机生成一个数
4. 求根号2
5. 分解质因数
6. 判定质数
7. 求素数
8. 最大公共子串
9. 任意一天是周几
10. 迷宫

## dp问题汇总



### 背包问题

#### 01背包

##### AcWing 2. 01背包问题 √

#### 完全背包问题

##### AcWing 3. 完全背包问题√

##### 279. Perfect Squares(错数次，需要再联系)√

##### 322. Coin Change(错数次，需要再联系)

1. 初始化0位置表示 用前0个的到0块钱，所以应该是0
2. 应该从第一个硬币开始遍历

#### 多重背包问题	

##### AcWing 4. 多重背包问题√

##### AcWing 5. 多重背包问题 II (优化枚举方法)

#### 分组背包问题

##### AcWing 9. 分组背包问题√

### 线性dp

#### 1. fibinocci数列类型

##### 70 Climbing Stairs (做错一次)√

​	在交换数字时赋值错误

##### 198. House Robber√

##### Decode Ways

枚举每一步的所用状态非常重要，并且要注意所有状态的条件。

#### 2.最长上升子序列问题

##### Best Time to Buy and Sell Stock√

##### AcWing 895. 最长上升子序列√

#### 3. 最大连续子数组

##### 53. Maximum Subarray√

求一个数组中的最大最小值，可以把初始值设为数组中的第一个。

##### Maximum Product Subarray

注意需要枚举多个状态时，有可能要保存多个前一个状态

#### 4. 数字三角形模型

##### AcWing 898. 数字三角形√

##### 62. Unique Paths√

##### 63. Unique Paths II√

##### 64. Minimum Path Sum√

##### 174. Dungeon Game

#### 5. 最长公共子序列

##### AcWing 897. 最长公共子序列√

##### AcWing 902. 最短编辑距离√

##### 

### 记忆化搜索

##### Word Break

##### 

迷宫算法



### 链表

#####  Delete Node in a Linked List

##### Remove Nth Node From End of ListSolution

增加头指针使得头结点的删除一致

##### Reverse Linked List

1. 找到链表的重点
2. 逆序后半部分
3. 比较

##### Merge k Sorted Lists

### tree

##### Validate Binary Search TreeSolution

1. 递归时保存的是当前节点的值得范围
2. 更新最大最小值时，如果是左子树，应该只缩小其最大值的范围，最小值应该继承父节点，有子树类似

##### Symmetric Tree

1. 做错重新做

### arrays

1. 遇到问题需要考虑边界情况
2. 很多数组问题都可以先进行排序再去解决
3. 数组问题 需要移动的或者删除的， 都可以用双指针去挪动元素。一个指针用来写结果，一个指针用来遍历
4. 数组题可以通过画图找规律来解决
5. 对于串问题需要对称的，可以分别用两个指针从串的两端移动。for(int i =0, int j = s.length-1; i< j; i++, j--){

##### 26. Remove Duplicates from Sorted Array

1. 这个题可以扩展到删除一个数组中的重复元素，只要把其交换都数组开始的位置，就可以删除重复的。

**删除重复元素都可以使用移动的方式。**

2. 自己的做法：遍历数组，和前一个对比，不同则累加。相同则跳过
3. 标准答案：双指针,第一个指针指向结果位，第二个指针进行后续的遍历，不同这结果指针+1，并赋值

##### 80. Remove Duplicates from Sorted Array II

1. 同样使用双指针，但是在不同时需要检查下一个是否相同，相同则移动到初始位置。
2. 注意重复元素要特殊处理

##### Best Time to Buy and Sell Stock II

1. 自己的做法：

无股票时统计只要上涨就买入，下跌就卖出。

**注意，有无股票需要用-1标记，因为有可能有0的股票。**

2. 最优化做法：

   仅需要统计所有上涨的点就可以了，每个位置的数字与前一个对比，只要大于前一个，就累加入结果队列

##### Rotate Array

1. 自己的做法：利用额外空间。遇到的问题，未考虑边界情况，导致数组越界
2. 答案：使用reverse的方法，进行三次reverse，空间复杂度未o(1)；

##### Contains Duplicate

1. 自己的做法：使用set，查看重复。o(n) 0(n)
2. 也可以使用排序，然后检查相邻的是否重复。o(nlogn) o(1)

扩展：去重类似的问题，都可以考虑使用排序后处理

##### Single Number

1. 自己的做法：位运算 最优
2. 其它做法：使用 hashSet统计，有存在的就删除

##### Intersection of Two Arrays II

1. 先排序，然后使用两个指针操作
2. 使用集合

##### Move Zeroes

使用 双指针将不是0的元素全部挪到前面，然后给后面的值赋值

##### Rotate Image

1. 对矩阵进行两次对称，第一次是对脚线对称，第二次是y轴对称
2. 矩阵对称的写法：for(int i = 0, j = matrix.length -1; i<j; i++,j--){
3. 对于矩阵变换问题，可以分为多次进行变换

##### Reverse String

需要对称操作的序列都可以用 两个指针从前后开始进行交换

##### Reverse Integer

1. 处理整数问题可能会溢出，注意不要溢出

   ```
    if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE / 10 && x > 7)) return 0;
    if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE / 10 && x < -8)) return 0;
   ```

   判断溢出的方法

##### Valid Anagram

1. 排序
2. hash +加减数目。第一次hash增加数目，第二次减少。
3. 大多数字符串问题都可以使用hash解决。注意判断字符是否相等问题可以使用加减hash后的数目，然后判断是否为0。

#####  String to Integer (atoi)

注意：处理int溢出问题可以使用long存储去判断，这样容易判断是否越界

判断越界

```
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && (tmp > 7 && sign == 1 || tmp > 8 && sign == -1))) 
```

##### Implement strStr()

```
比较字符串相等
haystack.substring(i, i+needle.length()).equals(needle)
haystack.startsWith(needle, i);
```

##### Count and Say

```
双指针统计重复字符个数。注意最后下标的变化
```

#####  Longest Common Prefix

```
!strs[j].startsWith(tmp)
indexOf()的区别
```

