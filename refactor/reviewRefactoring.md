defination ： 在不改变软件原有行为的基础上，持续对代码进行小步修改

why should we refactoring :

1. 重构改善设计。refactoring improves designs of software.
   - 不重构的代码腐化的更快(without refactoring,the internal design(the architecture  ) of softwares tends to decay)
2. 重构使代码更易于理解。(refactroing makes software easy to unsterand)
3. 重构使得功能添加更快。(refactroing helps me program faster)
4. 重构帮助寻找bug(refactroing helps me find bugs)

when should we refactoring?

1. the rule of Three.

2. Preparatory Refactoring -making it easier to add a Feature

3. Comprehension Refactoring-making Code easiter to understand

4. litter-pickup refactoring

5. refactoring in code review.

6. planned refactoring

7. Long-term refactroing

   (后两种不推荐)

The problems of refactoring.

slowing down New features

1. 当重构的点比要实现的代码改动量太大时，可以先不重构，后面使用ltter-pick-up重构
2. 对于几乎不会用到的代码和不会经常带来不便的代码先不重构
3. 拒绝没有收益的重构
4. 管理者在团队中应该表现出重视重构





bad smells

1. mysterious Name

   1. **Change Function Declaration**  (124)
   2. **Rename  Variable**  (137)
   3.  **Rename  Field** (244)

2. Duplicate code

   1. **Slide  Statements  (223)** (easy to extract )
   2. **Extract  Function** (106)
   3.  **Pull  Up  Method  (350)**

   - 参数重复
   - 调用性重复
   - 回调型重复

3. Long function 

   1. **Extract  Function** (106)
   2. 如果参数过多，参照long method
   3.  **Replace**  **Function  with  Command  (337)**.

   How  do  you  identify  the  clumps  of  code  to  extract?

   1. comment
   2. if
   3. switch
   4. for

4. Long parameter

   1.  **Replace  Parameter  with  Query  (324)** 
   2.  **Preserve Whole Object (319)** 
   3. **Introduce  Parameter  Object  (140)**
   4. **Remove Flag Argument (314)**
   5.  **Combine  Functions  into  Class  (144)** 

5.  Global Data

   what：**global variables**,class variables and singletons.

   1.  **Encapsulate  Variable  (132),**
   2. 最好变为不可变的，逐渐缩小可见范围。

6. mutable Data

   变量

   1. **Encapsulate Variable (132**

   2.  **Split Variable (240)**

   3. **Separate Query from Modifier (306)** 

   4. **Replace Derived  Variable  with  Query  (248)**

      类

   5. **Remove Setting Method (331)**

   6.  **Combine  Functions into Class (144)** or **Combine Functions into Transform (149)**

   7. **Change  Reference  to  Value  (252).**

7. Divergent Change

   1.  **Split Phase (154)** 
   2.   **Move  Function(198)** 
   3. **Extract  Function  (106)** 
   4.  **Extract  Class  (182)** 

8. shotgun surgery

   1.   **Move  Function  (198)**  
   2.  **Move  Field  (207)**
   3. **Combine Functions into Class (144)** or  **Combine Functions into Transform(149).**
   4. **Split Phase (154)** 
   5. **Inline Function (115)** or **Inline Class (186)**,

9. Feature Envy

   1. Move Function (198)
   2.  **Extract  Function(106)**  ->   **Move  Function  (198)**(移到使用数据最多的类中)

10. Data Clumps

    data  items  together  in  lots  of  places:  **as  ﬁelds in a couple of classes, as parameters in many method signatures.**

    1. **Extract  Class  (182)**  
    2.  **Introduce  Parameter  Object  (140)** or **Preserve  Whole  Object  (319)** 
    3. find feature envy

11. primitie Obssession

    1.  **Replace Primitive with Object (174)**
    2. Replace  Type  Code  with  Subclasses (362)** followed by **Replace  Conditional  with  Polymorphism  (272).**
    3. **Extract  Class  (182)** and **Introduce  Parameter  Object  (140).**

12. Repeated switches

    1. **Replace Conditional with Polymorphism (272)**

13. Loops

    1. **Replace Loop with Pipeline(231)** 

14. Lazy Element

    1. using Inline Function (115) or Inline Class (186)
    2. With inheritance, you can use Collapse  Hierarchy  (380)

15. speculative generality

    1. Collapse Hierarchy (380).
    2. **Inline Function (115)** and **Inline Class(186)**.
    3. **Change  Function Declaration  (124)** 
    4.  **Remove Dead  Code  (237).**

16. temporary field

    Sometimes  you  see  a  class  in  which  a  ﬁeld  is  set  only  in  certain  circumstances.

    1.  **Extract  Class  (182)**  
    2.  **Move Function (198)** 
    3.  **Introduce Special Case(289)**

17. Message Chains

    You see message chains when a client asks one object for another object, which the client then asks for yet another object.

    1.  **Hide Delegate (189).**
    2. Extract Function (106) —>  Move Function (198)

18. middle man

    1. Remove Middle Man (192) —> **Inline  Function  (115)** 
    2.   **Replace  Superclass  with Delegate  (399)** or **Replace  Subclass  with  Delegate  (381)** 

19. Insider trading

    1. **Move  Function  (198)**  and  **Move  Field  (207)**  
    2. create  a  third  module  to  keep  tha**t commonality in a well-regulated  vehicle,** or use **Hide Delegate (189)** to make another module act as an intermediary.
    3. **Replace Subclass with Delegate (381)** or **Replace Superclass with Delegate  (399)**.

20. Large class

    1. **Extract  Class  (182)** 
    2. Extract  Superclass  (375)**  or  **Replace  Type  Code with  Subclasses  (362)** 

21. Alternative Classes with Different Interfaces

    1.  **Change Function Declaration (124)** 
    2. **Move Function (198)** 
    3. **Extract Superclass (375)**

22. data class 

    1. **Encapsulate Record (162)** 
    2. **Remove Setting Method (331)** 
    3.  **Move Function (198)** 
    4. **Extract  Function  (106)** 

23. refused bequest

    1. **Replace  Subclass  with  Delegate  (381)** or **Replace  Superclass  with  Delegate  (399).**

24. comments

    1.  **Extract Function(106)**.
    2.   **Change  Function  Declaration  (124)**  
    3. **Introduce  Assertion(302)**.

1. 



1. A First set of Refactorings
   1. Extract functions
   2. inline functions
   3. Extract variable
   4. Inline Functions
   5. Change Functions Declaration
   6. Encapsulate Variable
   7. Rename variable
   8. introduce Parameter Object
   9. Combine function into class
   10. Combine Function int Transform
   11. split Phase
2. encapsulation
   1. encapsulate record
   2. encapsulate collection
   3. replace primitive with object
   4. replace temp with query
   5. Extract class
   6. inline class
   7. hide delegate
   8. remove middle man
   9. subsitute Algorithm
3. moving features
   1. move function
   2. move field
   3. move statements into function
   4. move statements to callers
   5. replace inline code with function call
   6. slide statements
   7. split loop
   8. replace loop with pipeline
4. organizing data
   1. split variable
   2. rename field
   3. replace derived variable with query
   4. change reference to value 
   5. change value to reference
5. simplifying condition
   1. Dempose conditional
   2. consolidate conditional expression
   3. replace nested conditional with guard clauses
   4. replace conditional with polymorphism
   5. introduce special case
6. refactoring APIs
   1. seperate query from modifier
   2. parameterize function
   3. remove flag argument
   4. preserve whole object
   5. replace parameter with query
   6. replace query with parameter
   7. remove setting method
   8. replace constructor factory
   9. replace function with command
   10. replace command with function
7. dealing with inherantence hierarchy
   1. pull up method
   2. pull up field
   3. pull up constructor body
   4. push down method
   5. replace type code with subcasses
   6. remove subclass
   7. collapse hierarchy
   8. replace subclass with delegate
   9. replace superclass with delegate