# 10. simplifying Condition

#### 10.1 Decompose Conditional

##### 	10.1.1 motivations

As with any large block of code, I can make my intention clearer by decomposing it and replacing each chunk of code with a function call named after the intention of that chunk.

#### 10.2 Consolidate Conditional Expression

Sometimes, I run into a series of conditional checks where each check is **different yet the resulting action is the same**. When I see this, I use and and or operators to consolidate them into a single conditional check with a single result.

#### 10.3 Replace Nested Conditional with Guard Clauses

I often find that conditional expressions come in two styles. In the first style,
both legs of the conditional are part of normal behavior, while in the second
style, one leg is normal and the other indicates an unusual condition.
**These kinds of conditionals have different intentions—and these intentions should come through in the code**. If both are part of normal behavior, I use a
condition with an if and an else leg. If the condition is an unusual condition, I
check the condition and return if it’s true. This kind of check is often called a
guard clause.
**The key point of Replace Nested Conditional with Guard Clauses is emphasis.**
If I’m using an if-then-else construct, I’m giving equal weight to the if leg and
the else leg. This communicates to the reader that the legs are equally likely and
important. Instead, the guard clause says, “This isn’t the core to this function,
and if it happens, do something and get out.”

- **These kinds of conditionals have different intentions—and these intentions should come through in the code**.
- **The key point of Replace Nested Conditional with Guard Clauses is emphasis.**
  If I’m using an if-then-else construct, I’m giving equal weight to the if leg and
  the else leg.
- This communicates to the reader that the legs are equally likely and
  important. Instead, the guard clause says, “This isn’t the core to this function,



#### 10.4 Replace Conditional with Polymorphism

#### 10.5 introduce special case

##### 