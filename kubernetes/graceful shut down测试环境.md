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

如果配置了优雅退出并且优雅退出等待时长大于当前服务的最长请求时长，则能确保所有请求都成功处理后才退出。

如果优雅退出时长大于k8s 等待pod删除的默认时长，需要在pod中重新设置等待时长。实验详情



![Screen Shot 2020-11-18 at 11.11.38 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%2011.11.38%20AM.png)

![Screen Shot 2020-11-18 at 11.13.56 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%2011.13.56%20AM.png)



场景一：springboot不进行任何配置

![Screen Shot 2020-11-18 at 11.24.41 AM](/Users/yu.zhang2/Desktop/Screen%20Shot%202020-11-18%20at%2011.24.41%20AM.png)



![Screen Shot 2020-11-18 at 11.24.05 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%2011.24.05%20AM.png)

https://www.w3.org/Protocols/rfc2616/rfc2616-sec8.html#sec8.2.4

![Screen Shot 2020-11-18 at 1.05.53 PM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%201.05.53%20PM.png)



场景二：springboot 配置优雅退出，优雅退出最大时长为 30s

![Screen Shot 2020-11-18 at 11.30.10 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%2011.30.10%20AM.png)

![Screen Shot 2020-11-18 at 11.30.28 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%2011.30.28%20AM.png)

场景3： springboot配置优雅退出， 优雅退出最大时长为10s

![Screen Shot 2020-11-18 at 11.40.20 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-18%20at%2011.40.20%20AM.png)



## 实验二：测试在删除pod时如果不设置prestophook，可能会出现pod已退出但iptables扔未更新，导致请求依然路由到被删除的pod

### 试验方法

1. 实现一个api， 打印log，并给程序设置gracefulshutdown，readiness以排除其它因素
2. 使用postman发请求，循环发送1000个请求，间隔3ms
3. 删除掉正在处理请求的pod，查看是否有失败的请求

| 场景  | prestophook                            | rediness | 结论           |
| ----- | -------------------------------------- | -------- | -------------- |
| 场景1 | 无                                     | 有       | 出现错误请求   |
| 场景2 | command: ["/bin/sh", "-c", "sleep 15"] | 有       | 未出现错误请求 |
|       |                                        |          |                |

场景一：

![Screen Shot 2020-11-19 at 10.31.06 AM](graceful%20shut%20down%E6%B5%8B%E8%AF%95%E7%8E%AF%E5%A2%83.assets/Screen%20Shot%202020-11-19%20at%2010.31.06%20AM.png)