## Preface

- Part I is an informal **introduction for new users.**
-  Part II documents the **SQL query language environment, including data types and functions, as well as user-level performance tuning. Every PostgreSQL user should read this.**
- Part III describes the installation and administration of the server. Everyone who runs a PostgreSQL. server, be it for private use or for others, should read this part.
- Part IV describes the programming interfaces for PostgreSQL client programs.
- Part V contains information for advanced users about the extensibility capabilities of the server.
  Topics **include user-defined data types and functions.**
- Part VI contains reference information about SQL commands, client and server programs. **This part**
  **supports the other parts with structured information sorted by command or program.**
- Part VII contains assorted information that might be of use to PostgreSQL developers

### What is PosgresSQL

PostgreSQL is an object-relational database management system (ORDBMS) based on POSTGRES,
Version 4.21, developed at the University of California at Berkeley Computer Science Department.



PostgreSQL is an open-source descendant of this original Berkeley code

It supports a large part of the SQL standard and offers many modern features:

- complex queries
- foreign keys
- triggers
- updatable views
-  transactional integrity
-  multiversion concurrency control

Also, PostgreSQL can be extended by the user in many ways, for example by adding new

- data types
- functions
- operators
- aggregate functions
- index methods
- procedural languages

### History of PostgreSQL

The object-relational database management system now known as PostgreSQL is derived from the
POSTGRES package written at the University of California at Berkeley.

#### The Berkeley POSTGRES Project

The implementation of POSTGRES began in 1986.

The size of the external user community nearly doubled during **1993**. It became **increasingly obvious**
**that maintenance of the prototype code and support was taking up large amounts of time that should have been devoted to database research.** In an effort to reduce this support burden, the BerkeleyPOSTGRES project officially ended with **Version 4.2**.



#### Postgres95

In 1994, Andrew Yu and Jolly Chen added an SQL language interpreter to POSTGRES. Under a new
name, Postgres95 was subsequently released to the web to find its own way in the world as an **opensource descendant** of the original POSTGRES Berkeley code.



#### PostgreSQL

By 1996, it became clear that the name “Postgres95” would not stand the test of time. We chose a new
name, PostgreSQL, to reflect the relationship between the original POSTGRES and the more recent
versions with SQL capability. At the same time, we set the version numbering to start at 6.0, putting
the numbers back into the sequence originally begun by the Berkeley POSTGRES project.



### Conventions

The following conventions are used in the synopsis of a command: **brackets ([ and ]) indicate optional**
**parts**. (In the synopsis of a Tcl command, question marks (?) are used instead, as is usual in Tcl.)
Braces ({ and }) and vertical lines (|) indicate that you must choose one alternative. Dots (...) mean
that the preceding element can be repeated.



## Tutorial

The following few chapters are intended to give a simple introduction to PostgreSQL, relational database concepts, and the SQL language to those who are new to any one of these aspects.

### Getting started

#### Architectural Fundamentals

A server process, which manages the database files, accepts connections to the database from client
applications, and performs database actions on behalf of the clients. The database server program
is called postgres.

The user's client (frontend) applicatio that wants to perform database operations. 

- a client could be a text-oriented tool
- a graphical application
- a web server that accesses the database to display web pages
- specialized database maintenance tool

As is typical of client/server applications, the client and the server can be on different hosts. In that
case they communicate over a TCP/IP network connection.

#### Install

1. down load edb package

2. export bin to path. 

   ```
   /Library/PostgreSQL/13/bin
   ```

   

3. use command all pgadmin



#### Creating a Database



before create db, you should create a user all you can use postgres username

```
createdb mydb -U postgres


createuser  yu.zhang2 -s -P  -U postgres  //create user with the same name as  os user
createdb // use default user name as db name
```



#### Accessing a Database

​	Once you have created a database, you can access it by:

1. Running the PostgreSQL interactive terminal program, called psql, which allows you to
   interactively enter, edit, and execute SQL commands

   ```
   psql mydb
   ```

   

2. Using an existing graphical frontend tool like pgAdmin or an office suite with ODBC or JDBC
   support to create and manipulate a database. 

3. Writing a custom application, using one of the several available language bindings. These
   possibilities are discussed further in Part IV

#### The SQL Language

1. The \i command reads in commands from the specified file
2. Two dashes (“--”) introduce comments
3. PostgreSQL supports the standard SQL types int, smallint, real, double precision,
   char(N), varchar(N), date, time, timestamp, and interval,  as well as other types of
   general utility and a rich set of geometric types. 
4. Constants that are not simple numeric valuesusually must be surrounded by single quotes (')
5. It is important to understand the interaction between aggregates and SQL's WHERE and HAVING
   clauses
   - WHERE selects input rows before groups and aggregates are computed (thus, it controls which rows go into the aggregatecomputation), 
   - whereas HAVING selects group rows after groups and aggregates are computed
   - Thus,the WHERE clause must not contain aggregate functions; 



#### Advanced Features

##### Views

ou do not want to type the query each time
you need it. You can create a view over the query, which gives a name to the query that you can refer
to like an ordinary table:

##### Foreign Keys

Transactions are a fundamental concept of all database systems. The essential point of a transaction is
that it bundles multiple steps into a single, all-or-nothing operation. 

The intermediate states between
the steps are not visible to other concurrent transactions, and if some failure occurs that prevents the
transaction from completing, then none of the steps affect the database at all

##### transaction

- We need a guarantee that if something goes wrong partway through the operation, none of the steps executed so far will take effect. Grouping the updates into a transaction gives us this guarantee.
- We also want a guarantee that once a transaction is completed and acknowledged by the database
  system, it has indeed been permanently recorded and won't be lost even if a crash ensues shortly
  thereafter.
- PostgreSQL actually treats every SQL statement as being executed within a transaction. If you do not
  issue a BEGIN command, then each individual statement has an implicit BEGIN and (if successful)
  COMMIT wrapped around it.
- Savepoints allow you to selectively discard parts of the transaction, while committing the
  rest. After defining a savepoint with SAVEPOINT, you can if needed roll back to the savepoint with
  ROLLBACK TO.

