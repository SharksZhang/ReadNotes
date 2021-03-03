### shell的基本结构



~~~powershell
#!/bin/env bash

# 以下内容是对脚本的基本信息的描述
# Name: 名字
# Desc:描述describe
# Path:存放路径
# Usage:用法
# Update:更新时间

#下面就是脚本的具体内容  
commands
...
~~~

1）**脚本第一行**，魔法字符==**#!**==指定解释器【==必写==】

`#!/bin/bash`  表示以下内容使用bash解释器解析

**注意：**
如果直接将解释器路径写死在脚本里，可能在某些系统就会存在找不到解释器的兼容性问题，所以可以使用:`#!/bin/env 解释器`

2）**脚本第二部分**，注释(#号)说明，对脚本的基本信息进行描述【可选】

3）**脚本第三部分**，脚本要实现的具体代码内容



### shell脚本的执行方法

1. ```
   ./first_shell.sh
   ```

2. 直接在命令行指定解释器执行

   ```
   bash first_shell.sh
   bash -x first_shell.sh
   + echo 'hello world'
   
   -x:一般用于排错，查看脚本的执行过程
   -n:用来查看脚本的语法是否有问题
   ```

3. 使用`source`命令读取脚本文件,执行文件里的代码，相当于当前终端执行

   ```
   source first_shell.sh
   hello world
   hello world
   hello world
   ```

   

### 变量

#### 变量定义的方式

##### 基本方式

#### 交互式变量定义

**语法：**`read [选项] 变量名`

**常见选项：**

| 选项 | 释义                                                       |
| ---- | ---------------------------------------------------------- |
| -p   | 定义提示用户的信息                                         |
| -n   | 定义字符数（限制变量值的长度）                             |
| -s   | 不显示（不显示用户输入的内容）                             |
| -t   | 定义超时时间，默认单位为秒（限制用户输入变量值的超时时间） |



#### 定义有类型的变量

目的：**给变量做一些限制，固定变量的类型，比如：整型、只读

**用法：**`declare 选项 变量名=变量值`

**常用选项：**

| 选项   | 释义                       | 举例                                         |
| ------ | -------------------------- | -------------------------------------------- |
| ==-i== | 将变量看成整数             | declare -i A=123                             |
| ==-r== | 定义只读变量               | declare -r B=hello                           |
| -a     | 定义普通数组；查看普通数组 |                                              |
| -A     | 定义关联数组；查看关联数组 |                                              |
| -x     | 将变量通过环境导出         | declare -x AAA=123456 等于 export AAA=123456 |

**举例说明：**

```powershell
[root@MissHou ~]# declare -i A=123
[root@MissHou ~]# echo $A
123
[root@MissHou ~]# A=hello
[root@MissHou ~]# echo $A
0

[root@MissHou ~]# declare -r B=hello
[root@MissHou ~]# echo $B
hello
[root@MissHou ~]# B=world
-bash: B: readonly variable
[root@MissHou ~]# unset B
-bash: unset: B: cannot unset: readonly variable
```



#### 变量的分类

##### 全局变量（global variable）

变量在当前的整个 Shell 进程中都有效。每个 Shell 进程都有自己的作用域，彼此之间互不影响。在 Shell 中定义的变量，默认就是全局变量。

```
#!/bin/bash
a=99
```

##### 环境变量（environment variable）

全局变量只在当前 Shell 进程中有效，对其它 Shell 进程和子进程都无效。如果使用`export`命令将全局变量导出，那么它就在所有的子进程中也有效了，这称为“环境变量”。

```
#!/bin/bash
export a=99
```

如果希望环境变量在整个系统中生效，可将其定义在一下文件中

| 文件名               | 说明                                    | 备注                                                       |
| -------------------- | --------------------------------------- | ---------------------------------------------------------- |
| $HOME/.bashrc        | 当前用户的bash信息,用户**登录**时读取   | 定义别名、umask、函数等                                    |
| $HOME/.bash_profile  | 当前用户的环境变量，用户**登录**时读取  |                                                            |
| $HOME/.bash_logout   | 当前用户==**退出**==当前shell时最后读取 | 定义用户退出时执行的程序等                                 |
| /etc/bashrc          | 全局的bash信息，所有用户都生效          |                                                            |
| /etc/profile         | 全局环境变量信息                        | 系统和所有用户都生效                                       |
| \$HOME/.bash_history | 用户的历史命令                          | history -w   保存历史记录         history -c  清空历史记录 |

**说明：**以上文件修改后，都需要重新==source==让其生效或者退出重新登录。

##### 局部变量（local variable)

变量只能在函数内部使用

```
#!/bin/bash
#定义函数
function func(){
    local a=99
}
#调用函数
func
#输出函数内部的变量
echo $a
```

Declaration and assignment must be separate statements when the assignment value is provided by a command substitution; as the `local` builtin does not propagate the exit code from the command substitution.

```
my_func2() {
  local name="$1"

  # Separate lines for declaration and assignment:
  local my_var
  my_var="$(my_func)"
  (( $? == 0 )) || return

  …
}
```

```
my_func2() {
  # DO NOT do this:
  # $? will always be zero, as it contains the exit code of 'local', not my_func
  local my_var="$(my_func)"
  (( $? == 0 )) || return

  …
}
```



##### 只读变量（readonly ）

Use the readonly command to make [variables](https://bash.cyberciti.biz/guide/Variables) and [functions](https://bash.cyberciti.biz/guide/Functions) readonly 

```
readonly var
readonly var=value
readonly p=/tmp/toi.txt
# error
p=/tmp/newvale

declare -r a
```

readonly and environment

```
declare -xr ORACLE_SID='PROD'
```

define constant 

```
readonly PATH_TO_FILES='/some/path'
```

Local readonly variable

```
Local -r a
```

##### 内置变量

| 内置变量     | 含义                                                         |
| ------------ | ------------------------------------------------------------ |
| ==$?==       | 上一条命令执行后返回的状态；状态值为0表示执行正常，==非0==表示执行异常或错误 |
| $0           | 当前执行的程序或脚本名                                       |
| ==$#==       | 脚本后面接的参数的==个数==                                   |
| ==$*==       | 脚本后面==所有参数==，参数当成一个整体输出，每一个变量参数之间以空格隔开 |
| ==$@==       | 脚本后面==所有参数==，参数是独立的，也是全部输出             |
| ==\$1\~$9==  | 脚本后面的==位置参数==，$1表示第1个位置参数，依次类推        |
| \${10}\~${n} | 扩展位置参数,第10个位置变量必须用{}大括号括起来(2位数字以上扩起来) |
| ==$$==       | 当前所在进程的进程号，如`echo $$`                            |
| $！          | 后台运行的最后一个进程号 (当前终端）                         |
| !$           | 调用最后一条命令历史中的==参数==                             |

#### Variable naming Convention

##### Variable Names

Lower-case, with underscores to separate words.

##### Constants and Environment Variable Names

All caps, separated with underscores, declared at the top of the file.

Constants and anything exported to the environment should be capitalized.



#### 查看变量

- `env`查看当前用户的环境变量
- `set`查询当前用户的所有变量(临时变量与环境变量) 
- `readonly` 查询当前只读变量

#### 变量的操作

##### 取出一个目录下的目录和文件：`dirname`和 `basename` 

```
# A=/root/Desktop/shell/mem.txt 
# echo $A
/root/Desktop/shell/mem.txt
# dirname $A   取出目录
/root/Desktop/shell
# basename $A  取出文件
mem.txt
```



##### 变量删除

一个“#”代表从左往右去掉一个/key/
两个“##”代表从左往右最大去掉/key/
一个“%”代表从右往左去掉一个/key/
两个“%%”代表从右往左最大去掉/key/

```
# url=www.taobao.com
# echo ${#url}		     14 获取变量的长度
# echo ${url#*.}       taobao.com
# echo ${url##*.}      com
# echo ${url%.*}       www.taobao
# echo ${url%%.*}      www
```

##### 变量替换

/：替换 

//：贪婪替换

```
#替换
echo ${url/ao/AO}     www.tAObao.com
#贪婪替换
echo ${url//ao/AO}    www.tAObAO.com
```

变量替代

1. ${变量名-新的变量值} 或者 ${变量名=新的变量值}
   变量没有被赋值：会使用“新的变量值“ 替代
   变量有被赋值（包括空值）： 不会被替代

   ```
   echo ${abc-123}
   abc=hello
   echo ${abc-123}
   abc=
   echo ${abc-123}
   
   *******
   ```

   

2. ${变量名:-新的变量值} 或者 ${变量名:=新的变量值}
   变量没有被赋值或者赋空值：会使用“新的变量值“ 替代
   变量有被赋值： 不会被替代

   ```
   echo ${b:-123}
   b=hello
   echo ${b:-123}
   b=
   echo ${b:-123}
   ```

3. ${变量名+新的变量值}
   变量没有被赋值或者赋空值：不会使用“新的变量值“ 替代
   变量有被赋值： 会被替代

   ```
    unset abc
    echo ${abc+123}
    abc=hello
    echo ${abc+123}
    abc=
    echo ${abc+123}
    echo "======="
   ```

   

4. ${变量名:+新的变量值}
   变量没有被赋值：不会使用“新的变量值“ 替代
   变量有被赋值（包括空值）： 会被替代

   ```
   echo 变量名:+新的变量值
    unset abc
    echo ${abc:+123}
   
    abc=hello
    echo ${abc:+123}
    abc=
    echo ${abc:+123}
     echo "======="
   ```

   

5. ${变量名?新的变量值}   shell exit with exit code 1
   变量没有被赋值:提示错误信息

   ```
   unset abc
   echo ${abc:?error}
   echo ${abc:?error}
   abc=
   echo ${abc:?error}
   ```

   

6. ${变量名:?新的变量值}
   变量没有被赋值或者赋空值时:提示错误信息
   说明：?主要是当变量没有赋值提示错误信息的，没有赋值功能

```
unset abc
echo ${abc?123}

abc=hello
echo ${abc?123}
abc=
echo ${abc?123}
```

####  数组

##### 数组分类

- 普通数组：只能使用整数作为数组索引(元素的下标)
- 关联数组：可以使用字符串作为数组索引(元素的下标)

##### 普通数组定义

1. 数组名[索引下标]=值

   ```
   declare -a array
   
   array[0]=v1
   array[1]=v2
   array[2]=v3
   array[3]=v4
   ```

   

2. 数组名=(值1 值2 值3 ...)

   ```
   declare -a array
   
   array=(var1 var2 var3 var4)
   
   array1=(`cat /etc/passwd`)			将文件中每一行赋值给array1数组
   array2=(`ls /root`)
   array3=(harry amy jack "Miss Hou")
   array4=(1 2 3 4 "hello world" [10]=linux)
   ```

3. 定义index为字符串的数组

   ```
   # asso_array2=([name1]=harry [name2]=jack [name3]=amy [name4]="Miss Hou")
   ```

4. seq 2 3 10 生成序列

   ```
   seq 1 5
   1
   2
   3
   4
   5
   ```

   

##### 数组的读取

${数组名[元素下标]}

```
echo ${array[0]}			获取数组里第一个元素
echo ${array[*]}			获取数组里的所有元素
echo ${#array[*]}			获取数组里所有元素个数
echo ${!array[@]}    	获取数组元素的索引下标
echo ${array[@]:1:2}    访问指定的元素；1代表从下标为1的元素开始获取；2代表获取后面几个元素
```

##### 查看普通数组信息

```
declare -a
```



##### 关联数组定义

```
declare -A asso_array1
declare -A asso_array2
declare -A asso_array3

一次赋一个值
# asso_array1[linux]=one
# asso_array1[java]=two
# asso_array1[php]=three

一次赋多个值
# asso_array2=([name1]=harry [name2]=jack [name3]=amy [name4]="Miss Hou")
```

##### 查看关联数组

```
declare -A
```

##### 获取关联数组值

```
# echo ${asso_array1[linux]}
one
# echo ${asso_array1[php]}
three
# echo ${asso_array1[*]}
three two one
# echo ${!asso_array1[*]}
php java linux
# echo ${#asso_array1[*]}
3
# echo ${#asso_array2[*]}
4
# echo ${!asso_array2[*]}
name3 name2 name1 name4
```

#### 简单四则运算

算术运算：默认情况下，shell就只能支持简单的==整数==运算

运算内容：加(+)、减(-)、乘(*)、除(/)、求余数（%）

| 表达式  | 举例                            |
| ------- | ------------------------------- |
| $((  )) | echo $((1+1))                   |
| $[ ]    | echo $[10-5]                    |
| expr    | expr 10 / 5                     |
| let     | n=1;let n+=1  等价于  let n=n+1 |

##### 命令：let

let 命令是 BASH 中用于计算的工具，用于执行一个或多个表达式，变量计算中不需要加上 $ 来表示变量。如果表达式中包含了空格或其他特殊字符，则必须引起来。

### 条件判断

#### 条件判断语法结构

说明：表达式两边要有空格，比较符两边也要有空格。

- 格式1： **test** 条件表达式

  ```
   test 1 = 2
   ✘  echo $?  注意：等号两边要有空格
  ```

  

- 格式2： **[** 条件表达式 ]   不推荐使用

  ```
  #!/usr/bin/env bash
  
  [ 1 = 2 ]; echo $? 
  
  
  ```

- 格式3： **[[** 条件表达式 ]]  支持正则 =~  

  ```
  [[ 1 = 2 ]]; echo $?
  ```

  

- 格式4：((条件表达式 )) 数学表达式增强版，不需要再将表达式里面的大小于符号转义。

```
(( 1 > 2 )); echo $?
```



#### 条件判断相关参数

##### 判断文件

1. 判断文件类型

| 判断参数 | 含义                                         |
| -------- | -------------------------------------------- |
| ==-e==   | 判断文件是否存在（任何类型文件）             |
| -f       | 判断文件是否存在==并且==是一个普通文件       |
| -d       | 判断文件是否存在并且是一个目录               |
| -L       | 判断文件是否存在并且是一个软连接文件         |
| -b       | 判断文件是否存在并且是一个块设备文件         |
| -S       | 判断文件是否存在并且是一个套接字文件         |
| -c       | 判断文件是否存在并且是一个字符设备文件       |
| -p       | 判断文件是否存在并且是一个命名管道文件       |
| ==-s==   | 判断文件是否存在并且是一个非空文件（有内容） |

2. 判断文件权限

| 判断参数 | 含义                       |
| -------- | -------------------------- |
| -r       | 当前用户对其是否可读       |
| -w       | 当前用户对其是否可写       |
| -x       | 当前用户对其是否可执行     |
| -u       | 是否有suid，高级权限冒险位 |
| -g       | 是否sgid，高级权限强制位   |
| -k       | 是否有t位，高级权限粘滞位  |

3. 判断文件新旧

说明：这里的新旧指的是==文件的修改时间==。

| 判断参数         | 含义                                                         |
| ---------------- | ------------------------------------------------------------ |
| file1 -nt  file2 | 比较file1是否比file2新                                       |
| file1 -ot  file2 | 比较file1是否比file2旧                                       |
| file1 -ef  file2 | 比较是否为同一个文件，或者用于判断硬连接，是否指向同一个inode |

##### 判断整数

| 判断参数 | 含义     |
| -------- | -------- |
| -eq      | 相等     |
| -ne      | 不等     |
| -gt      | 大于     |
| -lt      | 小于     |
| -ge      | 大于等于 |
| -le      | 小于等于 |

##### 判断字符串

| 判断参数           | 含义                                            |
| ------------------ | ----------------------------------------------- |
| -z                 | 判断是否为==空==字符串，字符串长度为0则成立     |
| -n                 | 判断是否为==非空==字符串，字符串长度不为0则成立 |
| string1 = string2  | 判断字符串是否相等                              |
| string1 != string2 | 判断字符串是否相不等                            |
| string =~ string2  | 判断字符串1包含字符串2                          |

#### 多重条件判断

| 判断符号   | 含义   | 举例                                                  |
| ---------- | ------ | ----------------------------------------------------- |
| -a 和 &&   | 逻辑与 | [ 1 -eq 1 -a 1 -ne 0 ]     [ 1 -eq 1 ] && [ 1 -ne 0 ] |
| -o 和 \|\| | 逻辑或 | [ 1 -eq 1 -o 1 -ne 1 ]                                |

**==特别说明：==**

&&	前面的表达式**为真**，才会执行后面的代码

||	 前面的表达式**为假**，才会执行后面的代码

如果&&和||一起出现，从左往右依次看，按照以上原则

```
#!/usr/bin/env bash
echo "条件判断的四种方式"
test 1 = 2; echo $?

test 1 = 1; echo $?

[ 1 = 2 ]; echo $?

[[ 1 = 2 ]]; echo $?


(( 1 == 2 )); echo $?

echo "判断文件存在"
test -e array1.sh; echo $?

echo "判断文件权限"

test -r array.sh; echo $?

echo "判断文件新旧"
test array.sh -nt test.sh; echo $?

echo "判断整数相等"

test 1 -eq 1; echo $?

echo "判断字符串"

test 1a = 1; echo $?


echo "多重&&条件判断"

test 1 -eq 1 -a 1 -ne 0; echo $?

test 1 -eq 1 && test 1 -ne 0; echo $?

echo "多重||条件判断"

test 1 -eq 1 -o 1 -ne 1; echo $?

test 1 -eq 1 || test 1 -ne 1; echo $?



```

### 流程控制语句

#### if语句

##### if 语句

```
if [ condition ];then
		command
		command
fi

[ 条件 ] && command
```

##### if -else 语句

```
if [ condition ];then
		command1
	else
		command2
fi

[ 条件 ] && command1 || command2
```

##### if-elseif-else

```

if [ condition1 ];then
		command1  	结束
	elif [ condition2 ];then
		command2   	结束
	else
		command3
fi



```

#### for语句

##### 列表循环

```
for variable in {list}
     do
          command 
          command
          …
     done
或者
for variable in a b c
     do
         command
         command
     done
     
     
for var in {1..10..2};
    do
        echo ${var}
    done


for var in 1 2 3 4 5 6 7 8 9 10;
    do
        echo ${var}
    done     
```

##### 不带列表的循环

不带列表的for循环执行时由**用户指定参数和参数的个数**

```
for variable
    do
        command 
        command
        …
   done
   
   echo "遍历输入参数"

for var1
    do
       echo ${var1}
    done


for var in 1 2 3 4 5 6 7 8 9 10;
    do
        echo ${var}
    done
```

##### 类C风格的for循环

```
for(( expr1;expr2;expr3 ))
	do
		command
		command
		…
	done
for (( i=1;i<=5;i++))
	do
		echo $i
	done
	
	
echo "类c风格循环"
for (( i=1;i<5;i++ ))
    do
    echo ${i}
    done

```

**循环体：** ==do....done==之间的内容

- continue：继续；表示==循环体==内下面的代码不执行，重新开始下一次循环
- break：打断；马上停止执行本次循环，执行==循环体==后面的代码
- exit：表示直接跳出程序

#### While 循环

```
while 表达式
	do
		command...
	done
	
while  [ 1 -eq 1 ] 或者 (( 1 > 2 ))
  do
     command
     command
     ...
 done
```





### 函数

#### 如何定义函数

方法1

```
函数名()
{
  函数体（一堆命令的集合，来实现某个功能）   
}
```

方法2

```
function 函数名()
{
   函数体（一堆命令的集合，来实现某个功能）
   echo hello
   echo world
}
```

#### 函数的返回值

1. return默认返回函数中最后一个命令状态值，也可以给定参数值，范围是0-256之间。
2. 如果没有return命令，函数将返回最后一个指令的退出状态值。





### 其它

1. 生成随机数

   ```
   1. Echo $RANDOM  %10   + 10                 
   ```

   