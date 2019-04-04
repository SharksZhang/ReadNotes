# 深入剖析kubernetes

### 开篇词









#### 问题

- 为什么容器里只能跑“一个进程”？

- 为什么我原先一直在用的某个 JVM 参数，在容器里就不好使了？

- 为什么 Kubernetes 就不能固定 IP 地址？容器网络连不通又该如何去debug？

- kubernetes中 StatefulSet 和Operator到底什么区别？PV和pvc这些概念又该怎么用？

我把专栏划分成了 4 大模块：

- 白话”容器技术基础

- Kubernetes 集群的搭建与实践

- 容器编排与 Kubernetes 核心特性剖析

- Kubernetes 开源社区与生态



### 1. 小鲸鱼大事记（一）：初出茅庐

- paas项目被大家接纳的一个主要原因，是它提供了一种名叫“用用托管”的能力，像Cloud Foundry 这样的paas项目，最核心的组件就是一套应用打包的分发机制。
- 由于虚机上启动很多不同用户的应用，Cloud Foundry会调用操作系统的Cgroups和Namespace机制为每一个应用单独创建一个称作“沙盒”的隔离环境，然后在“沙盒”中启动这些应用进程。这正是paas最核心的能力。
- docker项目在大部分功能和实现原理和Cloud Foundry都是一样的，唯一不同是Docker 镜像，docker镜像解决了打包问题，提供了本地环境和云端环境一致的能力。

总结：

docker项目提供了一种非常便利的打包机制，这种机制直接打包了应用运行所需要的整个操作系统，从而保证了本地环境和云端环境的高度一致，避免了用户通过“试错”来匹配不通运行环境之间差异的痛苦过程。

这种机制使得Docker公司脱颖而出。

### 2. 小鲸鱼大事记（二）：崭露头角

docker迅速崛起的三个重要原因：

1. Docker 镜像通过技术手段解决了PaaS的根本性问题
2. Docker容器通开发者之间有着与生俱来的密切关系；
3. PaaS概念已经深入人心的完美契机。

### 3.小鲸鱼大事记（三）：群雄并起

- 2014年底CoreOS推出了Rocket
- 2014年12月Dokcer为了扩展自己的平台能力，发布了Swarm

- Fig项目第一次提出了容器编排（Container Orchestration）的概念，docker收购fig
- mesos拼接超大集群管理经验提出Marathon
- 2014年6月Google提出了Kubernetes项目。

### 04 | 预习篇 · 小鲸鱼大事记（四）：尘埃落定

- 2015年6月22日，有Docker牵头，CoreOS，Google，RedHat公司共同宣布，Docker 公司将libcontainer捐出，并改名为RunC项目，交由中立基金会管理。并以Runc为依据，大家共同制定一套容器和镜像的标准和规范。
- OCI的提出，意在将容器运行时和镜像的实现从Docker项目中完全剥离出来。
- Google,RedHat共同牵头发起了一个名为CNCF(Cloud Native Computing Fundation)的基金会。它希望，已kubernetes项目为基础，建立一个有开源基础设施领域厂商主导的、按照独立基金会方式运营的平台及社区，来对抗以Docker为核心的容器商业生态。
- 要对抗docker，CNCF需要确保两件事情
  1. kubernetes项目必须能够在容器编排领域取得足够大的竞争优势；
  2. CNCF社区必须以kubernetes项目为核心，覆盖足够多的场景

我们先来看看 CNCF 社区如何解决 Kubernetes 项目在编排领域的竞争力问题：

- Kubernetes 项目早期的 GitHub Issue 和Feature大多来自Borg和Omega系统的内部特性，这些特性落到kubernetes项目上，就是Pod，Sidecar等功能。kubernetes项目的基础特性，并不是工程师拍脑袋想出来的，而是Google公司在容器化基础设施领域多年来实践经验的沉淀和升华。这是kubernetes避免通swarm和Mesos同质化的重要手段。
- RedHat凭借对开源设计运作和项目研发真谛的理解，使kubernetes在开源社区落地，并培育出认同这些理念的生态。

如何覆盖足够多的场景：

Kubernetes 的应对策略则是反其道而行之，开始在整个社区推进“民主化”架构，即：从API到容器运行时的每一层，kubernetes项目都为开发者暴露除了可以扩展的插件机制，鼓励用户通过代码的方式介入到kubernetes的每一个项目。

- 2017年开始，Docker公司将Docker项目的容器运行时部分Containerd捐赠给CNCF社区，标志着Docker项目全面升级成为一个PaaS平台；
- 紧接着，Docker公司宣布将Docker项目改名为Moby,然后交给社区维护。
- 2017年10月在docker企业版中内置kubernetes项目
- 2018年1月30 RedHat收购CoreOS。



### 05 | 白话容器基础（一）：从进程说开去

通过前四篇，能理解如下事实：

- 容器技术性起源于PaaS技术的普及；
- Docker公司发布的Docker项目具有里程碑式的意义；
- Docker项目通过“容器镜像”，解决了应用打包这个根本性难题。
- 容器本身没有价值看，有价值的是“容器编排”

容器，到底是怎么一回事儿？

- 容器技术的核心功能，就是通过约束和修改进程的动态表现，从而为其创造出一个“边界”。
- Cgroups技术用来制造约束的主要手段，而Namespace技术则是用来修改进程视图的主要方法。

容器实际上时在创建容器进程时，指定了这个进程所需要启用的一组Namespace参数。这样容器就只能看到当前Namespace所限定的资源、文件、设备、状态、或配置。所以说，容器，其实是一种特殊的进程而已。



### 06 | 白话容器基础（二）：隔离与限制

![contanervsvm](contanervsvm.png)

在这个图里，我们应该把Docker画在跟应用同级别并且靠边的位置，用户运行在容器里的应用进程，跟宿主机操作系统的其他进程一样，都有宿主机操作系统统一管理，只不过这些被隔离的进程拥有额外设置的Namespace参数。而Docker项目在这里扮演的角色，更多的是旁路式的辅助和管理工作。



### 





























































