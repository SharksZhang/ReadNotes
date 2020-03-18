[toc]

## 1. 如何在本地安装k8s

1. minikube

   ```
   https://kubernetes.io/docs/tasks/tools/install-minikube/
   ```

2. Docker-destop

3. 两者的区别

## 2. 如何部署一个服务

1. 使用docker for mac 部署一个服务

   1. ```
      kubectl create deployment hello --image=hello-spring:build-1\\
      kubectl get deploy hello -o yaml
      ```

2. 导出一个 service

   ```
   kubectl expose deployment hello  --port=8080 --target-port=8080
   ```

3. 如何导出一个服务

![ingress-controller](/Users/yu.zhang2/WorkSpace/ReadingNotes/kubernetes/ingress-controller-3761524.png)

2. 安装 ingress controller
   ```
   
   1. minikube
   kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/mandatory.yaml
   
   minikube addons enable ingress
   
   2. docker desktop
   	1. 部署ingress-controller
   kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/mandatory.yaml
     2. 暴露ingress-controller
   	kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/provider/cloud-generic.yaml
   	
   ```



## 3. ingress的分类

1. default

   ```
   apiVersion: networking.k8s.io/v1beta1
   kind: Ingress
   metadata:
     name: test-ingress
   spec:
     backend:
       serviceName: testsvc
       servicePort: 80
   ```

2. Fan out

   ```
   apiVersion: networking.k8s.io/v1beta1
   kind: Ingress
   metadata:
     name: simple-fanout-example
     annotations:
       nginx.ingress.kubernetes.io/rewrite-target: /
   spec:
     rules:
     - host: foo.bar.com
       http:
         paths:
         - path: /foo
           backend:
             serviceName: service1
             servicePort: 4200
         - path: /bar
           backend:
             serviceName: service2
             servicePort: 8080
   ```

3. Name based virtual hosting

   1. 删除default，无法访问
   2. apply host ingrss 仍然无法访问
   3. 修改host文件

   ```
   foo.bar.com --|                 |-> foo.bar.com service1:80
                 | 178.91.123.132  |
   bar.foo.com --|                 |-> bar.foo.com service2:80
   
   apiVersion: networking.k8s.io/v1beta1
   kind: Ingress
   metadata:
     name: name-virtual-host-ingress
   spec:
     rules:
     - host: foo.bar.com
       http:
         paths:
         - backend:
             serviceName: service1
             servicePort: 80
     - host: bar.foo.com
       http:
         paths:
         - backend:
             serviceName: service2
             servicePort: 80
   ```

   

## 4. service的分类。

1. clusterIp	

   

2. nodeport

   ```
   kubectl expose deployment hello --type NodePort  --port 9000 --target-port=8080
   ```

   ![nodeport](/Users/yu.zhang2/Desktop/nodeport.png)

3. Loadbalancer

   ![loadbalancer](/Users/yu.zhang2/WorkSpace/ReadingNotes/kubernetes/loadbalancer-3764563.png)



## 5. servise的实现原理

1. service的实现原理是使用iptables规则，当你配置cluster ip 当访问该cluster ip会被iptables转发到对应的pod ip。

## 6.总结

1. 本地安装的两种方式 minikube 以及docker-desktop

2. Service 有三种类型

   1. cluster ip
   2. node port
   3. loadbalancer

3. Ingress 三种类型

   1. default
   2. Fan out
   3. Name based virtual hosting
   4. Ingress 使用必须有ingressController.

4. 访问k8s集群中的服务的方式

   1. 集群外 

      1. nodeport    
      2. ingress

   2. 集群内

      1. culster ip

         

minikube在本地存在的问题



