微服务设计模式 book



#### 微服务理想模型

- 内部松耦合的若干服务组成

- 服务之间进行异步同行

- 同步协议用于服务与外部其他应用程序通讯



#### 微服务间通讯

HTTP

消息机制

- 消息通道



#### api 演化

##### restapi

/v1.., /v2/

##### 消息机制

包括版本号

#### Rest 挑战

1. 一个请求获取多个资源
   1. graphQL, NetFlix Falcor.
   2. 
2. 操作映射为http动词
   1. 取消订单，修改订单，订单是资源
   2. 替代技术：grpc