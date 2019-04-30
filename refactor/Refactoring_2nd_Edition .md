# Refactoring_ Improving the Design of Existing Code 2nd Edition - 2019

[TOC]

## Preface

### What’s in This Book?

**Chapter  1**  

- takes  a  small  program  with some common design ﬂaws and refactors it into a program that’s **easier to understand and  change.**

**Chapter 2**

- **I** cover more of the general principles of refactoring, some deﬁnitions,  and  the  reasons  for  doing  refactoring.  I outline  some  of  the  challenges with  refactoring.  

**Chapter  3**

-  bad smells in code 
-  how to clean them up with refactorings

**Chapter 4** 

-  how to build tests into code

**Chapter5** 

- The heart of the book—the catalog of refactorings—takes up the rest of its volume.

## Chapter1 Refactoring:A First Example

#### Decomposing the statement Function

- When  refactoring  a  long  function  like  this,  I  mentally  try  to  identify  points  that separate different parts of the overall behavior. 

## Chapter2 Principles in Refactoring

### 2.1 Defining Refactoring 

**Refactoring  (noun):**  a  change  made  to  the  internal  structure  of  software  to make  it  easier  to  understand  and  cheaper  to  modify  without  changing  its observable behavior.

**Refactoring (verb):** to restructure software by applying a series of refactorings without changing its observable behavior.

I use “**restructuring**” as a general term to  mean  any  kind  of  reorganizing  or cleaning  up  of  a  code  base,  and  see refactoring as a particular kind of restructuring.

**Refactoring**  is  always  done  to  make  the  code “**easier to understand and cheaper to modify**.” 

### 2.2 The Two Hats 

 **Two  Hats**: adding functionality and refactoring

**adding functionality** :When  I  refactor,  I  make  a  point  of  not  adding functionality;  I  only  restructure  the  code.  I  don’t  add  any  tests  (unless  I  ﬁnd  a case I missed earlier); I only change tests when I have to accommodate a changein an interface.
As I develop 

### 2.3 Why Should We Refactor?

 It  is  no  “silver bullet".Refactoring is a tool that can—and should—be used for several purposes
  - Refactoring Improves the Design of Software
      - Without  refactoring,  the  internal  design—the  architecture—of  software  tends  to decay.
      - Poorly  designed  code  usually  takes  more  code  to  do  the  same  things.
- Refactoring Makes Software Easier to Understand
- Refactoring Helps Me Find Bugs
- Refactoring Helps Me Program Faster

### 2.4 When Should We Refactor?

- The Rule of Three
- Preparatory Refactoring—Making It Easier to Add a Feature
- Comprehension Refactoring: Making Code Easier to Understand
- Litter-Pickup Refactoring
- Planned and Opportunistic Refactoring
  - You have to refactor when you run into ugly code—but excellent code needs plenty of refactoring too.
- Long-Term Refactoring
- Refactoring in a Code Review

### 2.5 Problems with Refactoring

#### 2.5.1 Slowing Down New Features

- The whole purpose of refactoring   is   to   make   us  program
  faster,  producing  more  value with less effort.
- 当重构的点相对于要实现新的代码改动量太大时，会权衡不重构而是只增加新功能
- I’m more likely to not refactor if it’s part of the code I rarely touch and the cost of the inconvenience isn’t something I feel very often
- 管理者应该表现出很在意重构
-   We  refactor  because  it  makes  us  faster—faster  to add features, faster to ﬁx bugs.

#### 2.5.2 Code Ownership

- 代码边界会导致无法重构客户端接口代码，导致重构后会有很多遗留代码

- allow team ownership of code

#### 2.5.3 Branches

-  Many of us have seen feature-branching teams that ﬁnd refactorings so exacerbate merge problems that they stop refactoring
- 使用ci可以小步提交，可以减小冲突的概率，解决次问题

#### 2.5.4 Testing

#### 2.5.5 Legacy Code

- Refactoring can be a fantastic tool to help understand a legacy system.
- The  best  advice  I  can  give  is  to get a copy of Working Effectively with Legacy Code [Feathers] and follow its guidance.
- Even  when  I  do  have  tests,  I  don’t  advocate  trying  to  refactor  a  complicated legacy  mess  into  beautiful  code  all  at  once. 
- If this  is  a  large  system,  I’ll  do  more  refactoring  in  areas  I  visit  frequently

### 2.6 Refactoring, Architecture, and Yagni

### 2.7 Refactoring and the Wider Software Development Process

### 2.8 Refactoring and Performance

### 2.9 Where Did Refactoring Come From?

### 2.10 Automated Refactorings

### 2.11 Going Further

- Refactoring Workbook

  I suggest Bill Wake’s Refactoring Workbook [Wake] that contains many exercises to practice refactoring.

- Refactoring to Patterns 

  which looks at the most valuable patterns from the hugely inﬂuential “Gang of Four” book [gof] and shows how to use refactoring to evolve towards them.

-  Working  Effectively  with  Legacy  Code  [Feathers]

  which  is  primarily  a book  about  how  to  think  about  refactoring  an  older  codebase  with  poor  test
  coverage

## 3. Bad Smells in Code

### 3.1 Mysterious Name

Sadly, however, naming is one of the two hard things [mf-2h] in programming.So, perhaps the most common refactorings we do are the renames: **Change Function Declaration**  (124) (to rename a function), **Rename  Variable**  (137), and **Rename  Field** (244).

### 3.2 Duplicated Code

- **when you have the same expression in  two  methods  of  the  same  class**.Then  all  you  have  to  do  is  **Extract  Function**
  (106) and invoke the code from both places.

- **If you have code that’s similar, but not  quite  identical,**  see  if  you  can  use  **Slide  Statements  (223)**  to  arrange  the  code
  so the similar items are all together for **easy extraction.**

  - 参数重复

  - 调用性重复

  - 回调型重复

    [^正交设计]: 额外添加正交设计关于重复部分

    

- **If the duplicate fragments are  in  subclasses  of  a  common  base  class**,  you  can  use  **Pull  Up  Method  (350)**  to avoid calling one from another.

- 如果两个毫不相关的类出现Duplicate Code，应该将重复代码提取出来放到合适的类中，可以是这两个类，也可以时一个独立的第三个类。

<!--此处讲的关于类的也可以引申到微服务，包，文件。在此处，本质上都是模块化的对象-->



### 3.3 Long Function

- Ninety-nine  percent  of  the  time,  all  you  have  to  do  to  shorten  a  function  is **Extract  Function** (106). Find parts of the function that seem to go nicely together and make a new one.
- If  you  have  a  function  with  lots  of  parameters  and  temporary  variables,  you  end  up passing  so  many  parameters  to  the  extracted  method  that  the  result  is  scarcely more readable than the original.You can often use **Replace Temp with Query (178)** to  eliminate  the  temps.  Long  lists  of  parameters  can  be  slimmed  down  with Introduce  **Parameter  Object  (140)** and **Preserve  Whole  Object  (319)**.
- If you’ve tried that and you still have too many temps and parameters, it’s time to get out the heavy artillery: **Replace**  **Function  with  Command  (337)**.

**How  do  you  identify  the  clumps  of  code  to  extract?**

-  A block of code with a comment that tells you what it is doing can be replaced by a method
  whose  name  is  based  on  the  **comment**
- **Conditionals** and loops also give signs for extractions. Use **Decompose Conditional (260)**  to  deal  with  conditional  expressions.
- A  big  **switch  statemen**t  should  have its legs turned into single function calls with **Extract Function** (106), If there’s more than  one  switch  statement  switching  on  the  same  condition,  you  should  apply **Replace  Conditional  with  Polymorphism  (272).**
- **With loops**, extract the loop and the code within the loop into its own method, If  you  ﬁnd  it  hard  to  give  an  extracted  loop  a  name, that  may  be  because  it’s doing  two  different  things—in  which  case  don’t  be  afraid  to  use  **Split  Loop  (227)** to break out the separate tasks

### 3.4 Long Parameter list 

- If  you  can  **obtain  one  parameter  by  asking  another  parameter**  for  it,  you  can use  **Replace  Parameter  with  Query  (324)**  to  remove  the  second  parameter
- **Rather than  pulling  lots  of  data  out  of  an  existing  data  structure**,  you  can  use  **Preserve Whole Object (319)** to pass the original data structure instead.
- If several parameters always  ﬁt  together,  combine  them  with  **Introduce  Parameter  Object  (140)**
-  If  a  parameter is used as a ﬂag to dispatch different behavior, use **Remove Flag Argument (314)**

-  When multiple  functions  share  several  parameter  values.  Then,  you  can use **Combine  Functions  into  Class  (144)** to capture those common values as ﬁelds

### 3.5 Global Data

The  problem  with  global  data  is  that  it  can  be modiﬁed from anywhere in the code base, and there’s no mechanism to discover which bit of code touched it. 

The most obvious form of global data is **global variables**, but we also see this problem with **class variables and singletons.**

- Our  key  defense  here  is  **Encapsulate  Variable  (132),**  which  is  always  our  ﬁrst
  move when confronted with data that is open to contamination by any part of a program.
-  Then,  it’s  good  to  limit  its scope as much as possible by moving it within a class or module where only that module’s code can see it.

### 3.6 Mutable Data

  I can update some data here, not realizing that another part of the software expects something  different  and  now  fails—a  failure  that’s  particularly  hard  to  spot  if  it only happens under rare conditions. 

- You can use **Encapsulate Variable (132)** to ensure that all updates occur through narrow functions that can be easier to monitor and evolve

- If a variable is being updated to store different things, use **Split Variable (240)** both to keep them sepa-
  rate  and  avoid  the  risky  update

-  Try  as  much  as  possible  to  move  logic  out  of code that processes the update by using **Slide Statements (223)** and **Extract Function (106)** to separate the side-effect-free code from anything that performs the update.

- In APIs, use **Separate Query from Modifier (306)** to ensure callers don’t need to call code that has side effects unless they really need to. 

- We like to use **Remove Setting Method (331)** as soon as we can—sometimes, just trying to ﬁnd clients of a setter helps spot opportunities to reduce the scope of a variable

- Mutable data that can be calculated elsewhere is particularly pungent.  **Replace Derived  Variable  with  Query  (248)**

- Mutable  data  isn’t  a  big  problem  when  it’s  a  variable  whose  scope  is  just  a couple  of  lines—but  its  risk  increases  as  its  scope  grows.  Use  **Combine  Functions into Class (144)** or **Combine Functions into Transform (149)** to limit how much code needs to update a variable.

-  If a variable contains some data with internal structure, it’s  usually  better  to  replace  the  entire  structure  rather  than  modify  it  in  place, using **Change  Reference  to  Value  (252).**

  

### 3.7 Divergent Change

Divergent change occurs when one module is often changed in different ways for different reasons. 

The database interaction and ﬁnancial processing problems are separate contexts, and we can make our programming life better by **moving such contexts into separate modules**. That way, when we have a change to one context, we only have to understand that one context and ignore the other

context  boundaries  are usually unclear in the early days of a program and continue to shift as a software
system’s capabilities change.

- If  the  two  aspects  naturally  form  a  sequence—for  example,  you  get  data  from the database and then apply your ﬁnancial processing on it—then **Split Phase (154)** separates the two with a clear data structure between them.
- If there’s more back-and-forth  in  the  calls,  then  create  appropriate  modules  and  use  **Move  Function(198)**  to  divide  the  processing  up.
- If  functions  mix  the  two  types  of  processing within themselves, use **Extract  Function  (106)** to separate them before moving
- If the  modules  are  classes,  then  **Extract  Class  (182)**  helps  formalize  how  to  do  the split

```
不符合单一职责，分离关注点
```

### 3.8 Shotgun Surgery

You whiff this when, **every time you make a change, you have to make a lot of little edits to a lot  of  different  classes.**

- In  this  case,  you  want  to  use  **Move  Function  (198)**  and  Move  Field  (207)  to  put all the changes into a single module.
- If you have a bunch of functions operating on similar data, use **Combine Functions into Class (144)**
- If you have functions that are transforming or enriching a data structure, use **Combine Functions into Transform(149).**
- **Split Phase (154)** is often useful here if the common functions can combine their output for a consuming phase of logic.
- A useful tactic for shotgun surgery is to use inlining refactorings, such as **Inline Function (115)** or **Inline Class (186)**, to pull together poorly separated logic, then  use  extractions  to
  break  it  up  into  more  sensible  pieces.

### 3.9 Feature Envy

 A  classic  case  of  Feature  Envy  occurs  when  a  function  in  one  module spends  more  time  communicating  with  functions  or  data  inside  another  module than it does within its own module. 

- The  function  clearly wants to be with the data, so use **Move Function (198)** to get it there.
- Sometimes,only  a  part  of  a  function  suffers  from  envy,  in  which  case  use  **Extract  Function(106)** on the jealous bit, and **Move  Function  (198)** to give it a dream home.

  Often,  a  function  uses  features  of several  modules,  so  which  one  should  it  live  with?

-  This step is often made easier if you use **Extract Function (106)** to break the function into pieces that go into different places.

**The fundamental rule of thumb is to put things together thatchange together.** 

Data and the behavior that references that data usually change together—but  there  are  exceptions.  When  the  exceptions  occur,  we  move  the behavior  to  keep  changes  in  one  place.  Strategy  and  Visitor  allow  you  to change  behavior  easily  because  they  isolate  the  small  amount  of  behavior  that needs to be overridden, at the cost of further indirection.

### 3.10 Data Clumps

特征：

1. you’ll  see  the  same  three  or  four  data  items  together  in  lots  of  places:  **as  ﬁelds in a couple of classes, as parameters in many method signatures.**
2. A good test is to consider deleting one of the data values. If you did this, would the others make any sense? If they don’t, it’s a sure sign that you have an object that’s dying to be born.

action：

1.  The  ﬁrst  step is  to  look  for  where  the  clumps  appear  as  ﬁelds.  Use  **Extract  Class  (182)**  on  the ﬁelds  to  turn  the  clumps  into  an  object
2. Then  turn  your  attention  to  method signatures using **Introduce  Parameter  Object  (140)** or **Preserve  Whole  Object  (319)** to slim them down.
3. You’ll notice that we advocate creating a class here, not a simple record structure.  You can now look for cases of **feature envy**,which will suggest behavior that  can  be  moved  into  your  new  classes.  We’ve  often  seen  this  as  a  powerful dynamic  that  creates  useful  classes  and  can  remove  a  lot  of  duplication  and  accelerate  future  development.

### 3.11 Primitive Obsession

1. You  can  move  out  of  the  primitive  cave  into  the  centrally  heated  world  of meaningful types by using **Replace Primitive with Object (174)**
2. If the primitive is a type code controlling conditional behavior, use **Replace  Type  Code  with  Subclasses (362)** followed by **Replace  Conditional  with  Polymorphism  (272).**
3. Groups  of  primitives  that  commonly  appear  together  are  data  clumps  and should be civilized with **Extract  Class  (182)** and Introduce  Parameter  Object  (140).

### 3.12 Repeated Switches

 So  we  now  focus  on  the  repeated  switch,  where  the  same  conditional switching logic (either in a switch/case statement or in a cascade of if/else statements)  pops  up  in  different  places.  

**The  problem  with  such  duplicate switches  is  that,  whenever  you  add  a  clause,  you  have  to  ﬁnd  all  the  switches and  update  them**

Against  the  dark  forces  of  such  repetition,  polymorphism provides an elegant weapon for a more civilized codebase.

### 3.13 Loops

 We  ﬁnd  that  pipeline  operations,  such  as ﬁlter and map, help us quickly see the elements that are included in the processing and what is done with them.

-  Replace Loop with Pipeline(231) 

### 3.14 Lazy Element 

It may be a function that’s named the same as its body code reads, or a class that is essentially one simple function.

1.  using Inline Function (115) or Inline Class (186)

2. With inheritance, you can use Collapse  Hierarchy  (380)

### 3.15 Speculative Generality

1. If you have abstract classes that aren’t doing much, use **Collapse Hierarchy (380).**
2. Unnecessary delegation can be removed with **Inline Function (115)** and Inline Class(186).
3. Functions  with  unused  parameters  should  be  subject  to  **Change  Function Declaration  (124)**  to  remove  those  parameters.  You  should  also  apply  **Change Function  Declaration  (124)**  to  remove  any  unneeded  parameters,  which  often  get tossed in for future variations that never come to pass.
4. Speculative generality can be spotted when the only users of a function or class are test cases. If you ﬁnd such an animal, delete the test case and apply **Remove Dead  Code  (237).**

### 3.16 Temporary Field

Sometimes  you  see  a  class  in  which  a  ﬁeld  is  set  only  in  certain  circumstances.Such code is difﬁcult to understand, because you expect an object to need all ofits  ﬁelds.  Trying  to  understand  why  a  ﬁeld  is  there  when  it  doesn’t  seem  to  be used can drive you nuts.

1. Use  **Extract  Class  (182)**  to  create  a  home  for  the  poor  orphan  variables
2. Use **Move Function (198)** to put all the code that concerns the ﬁelds into this new class.
3. You may also be able to eliminate conditional code by using **Introduce Special Case(289)** to create an alternative class for when the variables aren’t valid.

### 3.17 Message Chains

You see message chains when a client asks one object for another object, which the client then asks for yet another object, which the client then asks for yet another  another  object,  and  so  on.  You  may  see  these  as  a  long  line  of  getThis methods,  or  as  a  sequence  of  temps.

 Navigating  this  way  means  the  client  is coupled  to  the  structure  of  the  navigation.  Any  change  to  the  intermediate relationships causes the client to have to change.

- The move to use here is **Hide Delegate (189).** You can do this at various points in the chain. In principle, you can do this to every object in the chain, but doing this often turns every intermediate object into a middle man.
- 
  1. Often, a better alternative is to see what the resulting object is used for. See whether you can use **Extract  Function  (106)**  to  take  a  piece  of  the  code  that  uses  it  and  then  **Move Function  (198)**  to  push  it  down  the  chain. 
  2.  If  several  clients  of  one  of  the  objects in the chain want to navigate the rest of the way, add a method to do that.

### 3.18 Middle Man

 You look at a class’s interface and ﬁnd half the methods are delegating to this other class

1. After a while, it is time to use **Remove Middle Man (192)** and talk to the object that really knows what’s going on.
2. If only a  few  methods  aren’t  doing  much,  use  **Inline  Function  (115)**  to  inline  them  into the  caller. 
3.  If  there  is  additional  behavior,  you  can  use  Replace  Superclass  with Delegate  (399) or Replace  Subclass  with  Delegate  (381) to fold the middle man into the  real  object.  That  allows  you  to  extend  behavior  without  chasing  all  that delegation.

### 3.19 Insider Trading

 To  make  things work, some trade has to occur, but **we need to reduce it to a minimum and keep**
**it all above board**.

1. Modules that whisper to each other by the coffee machine need to be separated by  using  **Move  Function  (198)**  and  **Move  Field  (207)**  to  reduce  the  need  to  chat
2. If modules  have  common  interests,  try  to  create  a  third  module  to  keep  that commonality in a well-regulated  vehicle, or use **Hide Delegate (189)** to make another module act as an intermediary.

3. Inheritance  can  often  lead  to  collusion.  Subclasses  are  always  going  to  know more about their parents than their parents would like them to know. If it’s time to leave home, apply **Replace Subclass with Delegate (381)** or **Replace Superclass with Delegate  (399)**.

### 3.20 Large Class

When a class is trying to do too much, it often shows up as too many ﬁelds. When a class has too many ﬁelds, duplicated code cannot be far behind.

1. You can **Extract  Class  (182)** to bundle a number of the variables. More generally, common preﬁxes or sufﬁxes for some subset of the variables in  a  class  suggest  the  opportunity  for  a  component.
2. If  the  component  makes sense  with  inheritance,  you’ll  ﬁnd  **Extract  Superclass  (375)**  or  **Replace  Type  Code with  Subclasses  (362)** (which essentially is extracting a subclass) are often easier.
3. **Sometimes  a  class  does  not  use  all  of  its  ﬁelds  all  of  the  time**.  If  so,  you  may be able to do these extractions many times.
4. As with a class with too many instance variables, a class with too much code is a prime breeding ground for duplicated code, chaos, and death. **The simplest solution  (have  we  mentioned  that  we  like  simple  solutions?)  is  to  eliminate  redundancy  in  the  class  itself**.  If  you  have  ﬁve  hundred-line  methods  with  lots  of code in common, you may be able to turn them into ﬁve ten-line methods with another ten two-line methods extracted from the original.
5. The  clients  of  such  a  class  are  often  the  best  clue  for  splitting  up  the  class.**Look  at  whether  clients  use  a  subset  of  the  features  of  the  class**.  Each  subset  is a possible separate class. Once you’ve identiﬁed a useful subset, use **Extract Class (182)**,  **Extract  Superclass  (375)**,  or  **Replace  Type  Code  with  Subclasses  (362)**  to  break
   it out.

### 3.21 Alternative Classes with Different Interfaces

One  of  the  great  beneﬁts  of  using  classes  is  the  support  for  substitution,  allowing one class to swapin for another in times of need. But this only works if their interfaces are the same.

1. But this only works if their interfaces are the same. Use **Change  Function  Declaration  (124)** to make functions match  up.
2.  keep  using  **Move  Function  (198)**  to move behavior into classes until the protocols match. 
3. If this leads to duplication, you may be able to use Extract  Superclass  (375) to atone.

### 3.22 Data Class

These are classes that have ﬁelds, getting and setting methods for the ﬁelds, and nothing else. Such classes are dumb data holders and are often being manipulate in  far  too  much  detail  by  other  classes. 

1.  In  some  stages,  these  classes  may  have public ﬁelds. If so, you should immediately apply **Encapsulate Record (162)** before anyone notices. Use **Remove Setting Method (331)** on any ﬁeld that should not be changed.

2. Look  for  where  these  getting  and  setting  methods  are  used  by  other  classes. Try to use **Move Function (198)** to move behavior into the data class. If you can’t move  a  whole  function,  use  **Extract  Function  (106)**  to  create  a  function  that  can be moved.

A  key characteristic  of  such  a  result  record  is  that  it’s  immutable  (at  least  in  practice).Immutable   ﬁelds   don’t   need   to   be   encapsulated   and   information   derived from immutable data can be represented as ﬁelds rather than getting methods.

### 3.23 Refused Bequest

Subclasses get to inherit the methods and data of their parents. But what if they don’t want or need what they are given? They are given all these great gifts and pick just a few to play with.

1. The  traditional  story  is  that  this  means  the  hierarchy  is  wrong.  You  need  to create a new sibling class and use **Push  Down  Method  (359)** and **Push  Down  Field (361)** to push all the unused code to the sibling. 
2.  We don’t mind refusing implementations—but refusing interface gets us on our high horses. In this case,  however,  don’t  ﬁddle  with  the  hierarchy;  you  want  to  gut  it  by  applying **Replace  Subclass  with  Delegate  (381)** or **Replace  Superclass  with  Delegate  (399).**

### 3.24 Comments

The reason we mention comments here is that comments are often used as a deodorant.  It’s  surprising  how  often  you  look  at  thickly  commented  code  and  notice that the comments are there because the code is bad.

**When   you   feel   the   need   to write  a  comment,  ﬁrst  try  to refactor  the  code  so  that  any comment becomes superﬂuous.**

1. If you need a comment to explain what a block of code does, try **Extract Function(106)**.
2. If the method is already extracted but you still need a comment to explain what  it  does,  use  **Change  Function  Declaration  (124)**  to  rename  it.
3. If  you  need  to state  some  rules  about  the  required  state  of  the  system,  use  **Introduce  Assertion(302)**.

## 4. Building Tests

### 4.1 The Value of Self-Testing Code

1.  Some time is spent ﬁguring out what ought to be going on, some time is spent designing, but most time is spent debugging.
2.  If  I  added  a  bug  that was  caught  by  a  previous  test,  it  would  show  up  as  soon  as  I  ran  that  test.  The test  had  worked  before,  so  I  would  know  that  the  bug  was  in  the  work  I  had done since I last tested. 

#### 4.2 Test Example

- Always  make  sure  a  test  will fail when it should.通过测试失败保证测试一定能够跑到待覆盖的代码。
- My focus is to test the areas that I’m most worried about going wrong.
-  building a fresh ﬁxture every  time 
- Most of the updates are simple setters, and I don’t usually bother to test those as there’s little chance they will be the source of a bug.
- There  is  an  implicit  fourth  phase  that’s  usually  not mentioned:  teardown.
-   it’s  wise  to  have  only  a  single  verify  statement  in  each  it clause，  I  feel  the  two  are  closely  enough  connected  that  I’m  happy  to  have  them in  the  same  test.
- Think  of  the  boundary  conditions under which things might go wrong and concentrate your tests there.
-  but  test  coverage  analysis  is only good for identifying untested areas of the code, not for assessing the quality of a test suite.
- When  you  get  a  bug  report, start by writing a unit test that exposes the bug.

### 5. Introducing the Catalog

- I  emphasize  the  safe  way  of  doing  the  refactoring—which is to take very small steps and test after every one. At work, I usually take larger steps  than  some  of  the  baby  steps described,  but  if  I  run  into  a  bug,  I  back  out the last step and take the smaller steps. 
-  It’s  likely  you’ll  vary  them  as you get more practice in refactoring, and that’s ﬁne. Just remember that the key is to take small steps—and the trickier the situation, the smaller the steps.

#### 5.1 The Choice of Refactorings





### 6. A First Set of Refactorings

 a set of refactorings that I consider the most useful to learn ﬁrst.

1. Probably the **most common refactoring** I do is extracting code into a function(**Extract  Function  (106)**)  or  a  variable  (**Extract  Variable  (119)**).  the inverses of those two (**Inline  Function  (115)** and **Inline  Variable  (123))**
2. Extraction is all about **giving names,** and I often need to change the names as I  learn.  **Change  Function  Declaration  (124)**  changes  names  of  functions;  I  also use that refactoring to add or remove a function’s arguments. For variables, I use **Rename Variable (137)**, which relies on **Encapsulate Variable (132)**. When changing function  arguments,  I  often  ﬁnd  it  useful  to  combine  a  common  clump  of arguments into a single object with **Introduce  Parameter  Object  (140)**.
3. Forming  and  naming  functions  are  essential  low-level  refactorings—but,  once created, it’s necessary to group functions into higher-level modules. I use **Combine Functions into Class (144)** to group functions, together with the data they operate on, into a class.  Another path I take is to combine them into a transform **(Combine Functions  into  Transform  (149)),**  **which  is  particularly handy  with  read-only  data.**At a step further in scale, I can often form these modules into **distinct processing phases** using **Split  Phase  (154).**

#### 6.1 Extract Function

##### 6.1.1 Motivation

  The  argument  that  makes  most  sense  to  me,  however,  is **the separation between intention and implementation**.

1.  If you have to spend effort looking  at  a  fragment  of  code  and  ﬁguring  out  what  it’s  doing,  then  you  should extract it into a function and name the function after the “what.”
2.  A  comment  is  often  a  good  hint  for  the  name  of  the function when I extract that fragment.

##### 6.1.2 Mechanics

1. Create  a  new  function,  and  name  it  after  the  intent  of  the  function  (name it by what it does, not by how it does it).
2. Copy  the  extracted  code  from  the  source  function  into  the  new  target function.
3. Scan the extracted code for references to any variables that are local in scope to  the  source  function  and  will  not  be  in  scope  for  the  extracted  function. Pass them as parameters.

   - If  a  variable  is  only  used  inside  the  extracted  code  but  is  declared  outside,  move the declaration into the extracted code.
   - Any  variables  that  are  assigned  to  need  more  care  if  they  are  passed  by  value.  If there’s  only  one  of  them,  I  try  to  treat  the  extracted  code  as  a  query  and  assign the result to the variable concerned.
   - ,I ﬁnd that too many local variables are being assigned by the extracted code.  It’s  better  to  abandon  the  extraction  at  this  point.  When  this  happens,  I consider other refactorings such as Split  Variable  (240) or Replace  Temp  with  Query (178) to simplify variable usage and revisit the extraction later.
4. Compile after all variables are dealt with
5. Replace  the  extracted  code  in  the  source  function  with  a  call  to  the  target function.
6. Test.



#### 6.2 Inline Function

##### 6.2.1 Motivation

- the  code  into  something  that  is  just  as  clear  as  the  name
-  I have a group of functions that seem badly factored. I can inline them all into one big function and then reextract the functions the way I prefer
- I  commonly  use  Inline  Function  when  I  see  code  that’s  using  too  much indirection



#### 6.3 Extract Variable

##### 6.3.1 Motivation

 **they give me an ability to name a part of a more complex piece of logic**

If I’m considering Extract Variable, it means I want to add a name to an expression  in  my  code. **If it’s only meaningful within the function I’m working on, then Extract Variable is a good choice**—but if it makes sense in a broader context, I’ll  consider  making  the  name  available  in  that  broader  context,  usually  as  a function. 



The  downside  of  promoting  the  name  to  a  broader  context  is  extra  effort.  If it’s  signiﬁcantly  more  effort,  I’m  likely  to  leave  it  till  later  when  I  can  use **Replace  Temp  with  Query  (178).** But if it’s easy, I like to do it now so the name is immediately  available  in  the  code.  As  a  good  example  of  this,  if  I’m  working  in a class, then **Extract  Function  (106)** is very easy to do.



##### 6.3.2 Mechanics

- Ensure that the expression you want to extract does not have side effects.
- Declare an immutable variable. Set it to a copy of the expression you want to name.
- Replace the original expression with the new variable.
- Test.

#### 6.4 Inline Variable

##### 6.4.1 Motivation

1.  But sometimes, the name doesn’t really communicate more than  the  expression  itself.

2.  you  may  ﬁnd  that  a  variable  gets  in the  way  of  refactoring  the  neighboring  code.

##### 6.4.2 Mechanics

- Check that the right-hand side of the assignment is free of side effects.
- If the variable isn’t already declared immutable, do so and test.
- This checks that it’s only assigned to once.
- Find  the  ﬁrst  reference  to  the  variable  and  replace  it  with  the  right-handside of the assignment.
- Test.
- Repeat replacing references to the variable until you’ve replaced all of them.
- Remove the declaration and assignment of the variable.
- Test.

#### 6.5  Change Function Declaration

##### 6.5.1 Motivation 

1. The  most  important  element  of  such  a  joint  is  the  name  of  the  function.  **A good name allows me to understand what the function does** when I see it called, without seeing the code that deﬁnes its implementation.

2. 有时候需要保持整個函數接口的穩定性需要把整个参数对象传入，有时候为了减小依赖只传递单独的字段。

##### 6.5.2 Mechanics

**Simple Mechanics**

- If you’re removing a parameter, ensure it isn’t referenced in the body of the function.
- Change the method declaration to the desired declaration.
- Find  all  references  to  the  old  method  declaration,  update  them  to  the new one.
- Test.

**Migration Mechanics**

- If  necessary,  refactor  the  body  of  the  function  to  make  it  easy  to  do  the following extraction step.
- Use Extract  Function  (106) on the function body to create the new function.
  - If the new function will have the same name as the old one, give the new function a temporary name that’s easy to search for.
- If  the  extracted  function  needs  additional  parameters,  use  the  simple mechanics to add them.
- Test.
- Apply Inline  Function  (115) to the old function.
- If  you  used  a  temporary  name,  use  Change  Function  Declaration  (124)  again to restore it to the original name.
- Test.

自动化工具可以实现

#### 6.6 Encapsulate Variable

##### 6.6.1 Motivation 

1. 函数可以在不改变其引用位置的情况下重构，而数据不可以。So  if  I  want  to  move  widely  accessed  data,  often  the  best  approach  is  to  ﬁrst encapsulate  it  by  routing  all  its  access  through functions.
2.  It provides a clear point to monitor changes and use of the data; I can easily add validation or consequential logic  on  the  updates. 
3.   It  is  my  habit  to  make  all  mutable  data  encapsulated  like this  and  only  accessed  through  functions  if  its  scope  is  greater  than  a  single function.Whenever I see a public ﬁeld, I consider using Encapsulate  Variable  (in  that  case  often  called  Encapsulate  Field)  to  **reduce  its visibility.**

##### 6.6.2 Mechanics

- Create encapsulating functions to access and update the variable.
- Run static checks.
- For  each  reference  to  the  variable,  replace  with  a  call  to  the  appropriate encapsulating function. Test after each replacement.
- Restrict the visibility of the variable.
  - Sometimes  it’s  not  possible  to  prevent  access  to  the  variable.  If  so,  it  may  be useful to detect any remaining references by renaming the variable and testing.
- Test.
- If the value of the variable is a record, consider Encapsulate  Record  (162).

#### 6.7 Rename Variable

##### 6.7.1 Motivation

##### 6.7.2 Mechanics

- If the variable is used widely, consider Encapsulate  Variable  (132).
- Find all references to the variable, and change every one.
  - If there are references from another code base, the variable is a published variable, and you cannot do this refactoring.
  - If the variable does not change, you can copy it to one with the new name, then change gradually, testing after each change.
- Test.

#### 6.8 Introduce Parameter Object 

##### 6.8.1 Motivation

1. Grouping  data  into  a  structure  is  valuable  because  it  makes  explicit  the  relationship  between  the  data  items.

2. It  reduces  the  size  of  parameter  lists  for  any function that uses the new structure.
3. It helps consistency since all functions that use the structure will use the same names to get at its elements.
4. 可以进一步讲方法和数据绑定起来成为新的类

##### 6.8.2 Mechanics

- If there isn’t a suitable structure already, create one.
  - I prefer to use a class, as that makes it easier to group behavior later on. I usually like to ensure these structures are value objects [mf-vo].
- Test.
- Use  **Change  Function  Declaration  (124)**  to  add  a  parameter  for  the  new structure.
- Test.
- Adjust each caller to pass in the correct instance of the new structure. Test after each one.
- For  each  element  of  the  new  structure,  replace  the  use  of  the  original parameter with the element of the structure. Remove the parameter. Test.