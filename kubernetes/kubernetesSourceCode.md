# kubernetes source code 

## 1. cluster APi

#### 1.1 cluster api的安装，运行，启动流程。

#### ![Screen Shot 2019-07-16 at 3.15.24 PM](Screen Shot 2019-07-16 at 3.15.24 PM.png)

1. cluster api 安装部分代码实现
2. cluster api 控制器部分代码实现，以及Kubernetes整体控制器实现方法。
3. 实现一个kubernetes controller。
4. 实现一个 cluster api provider。
5. cluster api 与底层iaas交互部分实现
6. kubernetes 错误机制以及日志机制。
7. 
8.  



#### kind

export KUBECONFIG="$(kind get kubeconfig-path --name="kind")"

#### 日志

日志科技设置为每个文件添加参数



#### 问题

k8s镜像仓库



#### Finalizers

The first delete request on an object with finalizers sets a value for the `metadata.deletionTimestamp` field but does not delete it. Once this value is set, entries in the `finalizer` list can only be removed

When the `metadata.deletionTimestamp` field is set, controllers watching the object execute any finalizers they handle, by polling update requests for that object. When all finalizers have been executed, the resource is deleted.

The value of `metadata.deletionGracePeriodSeconds` controls the interval between polling updates.

Kubernetes only finally deletes the object if the list of finalizers is empty, meaning all finalizers have been executed.

https://book.kubebuilder.io/reference/using-finalizers.html

Highlights:

- If the object is not being deleted and does not have the finalizer registered, then add the finalizer and update the object in Kubernetes.
- If object is being deleted and the finalizer is still present in finalizers list, then execute the pre-delete logic and remove the finalizer and update the object.
- Ensure that the pre-delete logic is idempotent.