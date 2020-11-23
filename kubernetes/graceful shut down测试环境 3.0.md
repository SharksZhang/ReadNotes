# Kubernetes Graceful Shutdown Experiment

## 测试环境

- minikube v1.15.0
- Springboot 2.4.0
- Kubenetes v1.19.4

![graceful-shutdown (graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/graceful-shutdown%20(1).svg)](/Users/yu.zhang2/Downloads/graceful-shutdown%20(1).svg)



## 实验一：测试配置springboot优雅退出后当pod被删除时会等待请求处理完成。

### 实验方法

1. 实现一个api接口，在处理请求时sleep 30s。
2. 调用api接口
3. 在请求过程中删除pod

### 实验场景

| 场景  | 是否配置优雅退出 | 优雅退出最大时长 | 现象                                                         |
| ----- | ---------------- | ---------------- | ------------------------------------------------------------ |
| 场景1 | 否               |                  | pod立即退出，连接断开。postman 直接报错，出现socket hangup。 |
| 场景2 | 是               | 30s              | pod在等待请求处理完成后退出，并输出graceful shutdown complete |
| 场景3 | 是               | 10s              | pod在等待优雅退出最大时长后退出，并输出graceful shutdown aborted with one or more request still active.postman 直接报错，出现socket hangup |

### 总结

如果不在springboot中设置优雅退出，在pod删除时，会导致正在处理的请求立即断开连接，如果当前请求不是幂等性操作，会导致bug发生。

如果配置了优雅退出并且优雅退出等待时长大于当前服务的预期最长请求时长，则能确保所有请求都成功处理后才退出。

*如果优雅退出时长大于k8s等待pod删除的默认最大时长，需要在pod的yaml中重新设置最大等待时长。*

```
    spec:
      containers:
        - name: nginx
          image: graceful-shutdown-test-exit-graceful-30s:latest
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
      terminationGracePeriodSeconds: 60 //需要修改这个字段 
```



### 实验详情

Service and deployment

![Screen Shot 2020-11-19 at 4.11.21 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.11.21%20PM.png)

场景一：springboot不进行任何配置



![Screen Shot 2020-11-19 at 4.19.14 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.19.14%20PM.png)

![Screen Shot 2020-11-19 at 4.17.50 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.17.50%20PM.png)



场景二：springboot 配置优雅退出，优雅退出最大时长为 30s

![Screen Shot 2020-11-19 at 4.23.02 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.23.02%20PM.png)

![Screen Shot 2020-11-19 at 4.21.56 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.21.56%20PM.png)



场景3： springboot配置优雅退出， 优雅退出最大时长为10s

![Screen Shot 2020-11-19 at 4.28.49 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.28.49%20PM.png)

![Screen Shot 2020-11-19 at 4.28.01 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83%203.0.assets/Screen%20Shot%202020-11-19%20at%204.28.01%20PM.png)

