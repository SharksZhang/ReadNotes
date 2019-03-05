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
- Long-Term Refactoring
- Refactoring in a Code Review





