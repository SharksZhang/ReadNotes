# springboot2.0深度实践之核心技术篇

## 第一章：系列总览

### 1.1课程导学

![Screen Shot 2020-03-30 at 8.05.48 AM](Screen%20Shot%202020-03-30%20at%208.05.48%20AM.png)

2. 四个问题
   1. Spring Boot是如何基于Spring Famework逐步走向自动装配
   2. springApplication是怎样掌控Spring 应用生命周期？
   3. springboot外部化配置与Spring Environment抽象之间是什么关系
   4. Spring Web MVC向Spring Reactive WebFlux过度的真实价值和意义
3. 五种原则
   1. 场景分析，掌握技术选型
   2. 系统学习，拒绝浅尝辄止
   3. 重视规范，了解发展趋势
   4. 源码解读，理解设计思想
   5. 实战演练，巩固学习成果
4. 课程收获
   1. spring全栈技术和实现原理
   2. spring boot核心技术
   3. BAT大规模微服务基础设施开发与生产实施经验
5. 建议
   1. 多记api，多写代码，对以后的职业发展很有作用。



### 1.2 为什么说spring2.0易学难精

1. spring易学
   1. 组件自动装配：规约大于配置，专注核心业务
   2. 外部化配置：以此构建，按需调配，导出运行
   3. 嵌入式容器：内置容器、无需部署、独立运行
   4. spring Boot Starter：简化依赖，按需装配、自我包含
   5. Production-Ready: 一站式运维、生态无缝整合
2. spring boot 难精
   1. 组件自动装配：模式注解、@Enable模块、条件装配、加载机制
   2. 外部化配置： Environment抽象、生命周期、破坏性变更
   3. 嵌入式容器：Servlet Web 容器、Reactive Web容器
   4. Spring boot Starter:依赖管理、装配条件、装配顺序
   5. Production-Ready: 健康检查、数据指标、@Endpoint管控
3. Spring Boot 与Java EE 规范
   1. Web：Servlet(JSR-315、JSR-340)
   2. SQL:JDBC(JSR=221)
   3. 数据校验：Bean Validation(JSR 303、jSR-349)
   4. 缓存：Java Caching Api(JSR-107)
   5. WebSockets：Java API for WebSocket(JSR-356)
   6. WEB services：JAX-WS(JSR-224
   7. java 管理：JMX（JSR 3）
   8. 消息:JMS(JSR-914)

