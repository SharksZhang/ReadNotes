# how to write a dockerfile

本文从一个简单例子出发解释如何通过dockerfile去构建一个docker镜像。

```dockerfile
FROM maven:3.6.0-jdk-8 as build
COPY . /build
WORKDIR /build
RUN mvn clean && mvn install

FROM openjdk:8-jdk-alphine
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
```



1. 大纲
   1. 简单的dockerfile的例子
   2. 每个命令的作用
   3. context的作用
   4. 进阶
      1. 压缩命令到一个层
      2. 多段构建