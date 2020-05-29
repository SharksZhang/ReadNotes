[toc]

### javaWeb

#### 1. Java web的概念

##### 1.1 什么是javaweb

javaweb是指，所有通过语言编写可以通过浏览器访问的程序的总称

javaweb是基于请求和响应来开发的

##### 1.2.什么是请求和响应

请求是指客户端给服务器发送数据叫请求Request

响应是至服务器给客户端数据，叫响应Response.

#### 2.Web资源的分类

静态资源：html，css，js，TXT，

动态资源：jsp页面，servlet程序

#### 3.常用的web服务器

1. tomcat

#### 4. tomcat的使用

##### 1.目录结构

bin 专门用来存放 Tomcat 服务器的可执行程序
conf 专门用来存放 Tocmat 服务器的配置文件
lib 专门用来存放 Tomcat 服务器的 jar 包
logs 专门用来存放 Tomcat 服务器运行时输出的日记信息
temp 专门用来存放 Tomcdat 运行时产生的临时数据
webapps 专门用来存放部署的 Web 工程。
work 是 Tomcat 工作时的目录， 用来存放 Tomcat 运行时 jsp 翻译为 Servlet 的源码， 和 Session 钝化的目录。

##### 2.如何启动 Tomcat 服务器

找到 Tomcat 目录下的 bin 目录下的 startup.sh 文件， 执行

##### 3. 如何配置tomcat端口

在Tomcat 目录下的conf下的server.xml

```
 <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

##### 4.如何部署web工程到tomcat服务器

1. 第一种部署方法： 只需要把 web 工程的目录拷贝到 Tomcat 的 webapps 目录下
   即可。
2. 找到 Tomcat 下的 conf 目录\Catalina\localhost\ 下,创建如下的配置文件：

##### 5.web工程目录介绍

![Screen Shot 2020-05-25 at 1.10.44 PM](Screen%20Shot%202020-05-25%20at%201.10.44%20PM.png)

#### 

##### 6.如何在idea中配置tomcat以及web工程

 [idea配置tomcat.pdf](idea配置tomcat.pdf) 



#### 





### servlet技术

#### 1. 什么是servlet

1. Servlet 是 JavaEE 规范之一。 规范就是接口

   ```
   Defines methods that all servlets must implement.
   
   A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol.
   
   To implement this interface, you can write a generic servlet that extends javax.servlet.GenericServlet or an HTTP servlet that extends javax.servlet.http.HttpServlet.
   ```

   ```
    void	destroy()
             Called by the servlet container to indicate to a servlet that the servlet is being taken out of service.
    ServletConfig	getServletConfig()
             Returns a ServletConfig object, which contains initialization and startup parameters for this servlet.
    java.lang.String	getServletInfo()
             Returns information about the servlet, such as author, version, and copyright.
    void	init(ServletConfig config)
             Called by the servlet container to indicate to a servlet that the servlet is being placed into service.
    void	service(ServletRequest req, ServletResponse res)
             Called by the servlet container to allow the servlet to respond to a request.
   ```

   

2.  Servlet 就 JavaWeb 三大组件之1. 一。 三大组件分别是： Servlet 程序、 Filter 过滤器、 Listener 监听

3.  Servlet 是运行在服务器上的一个 java 小程序， 它可以接收客户端发送过来的请求， 并响应数据给客户端。

#### 2 如何实现一个Servlet

1. 编写一个类去实现 Servlet 接口
2.  实现 service 方法， 处理请求， 并响应数据
3.  到 web.xml 中去配置 servlet 程序的访问地址

#### 3. url 到servlet的访问路径

![Screen Shot 2020-05-29 at 1.59.34 PM](Screen%20Shot%202020-05-29%20at%201.59.34%20PM.png)

#### 4. servlet的生命周期

1、 执行 Servlet 构造器方法
2、 执行 init 初始化方法
第一、 二步， 是在第一次访问， 的时候创建 Servlet 程序会调用。
3、 执行 service 方法
第三步， 每次访问都会调用。
4、 执行 destroy 销毁方法
第四步， 在 web 工程停止的时候调用



#### 5. servlet的请求分发处理

​	都是用service处理

#### 6.Servlet 类的继承体系

![Screen Shot 2020-05-29 at 2.26.20 PM](Screen%20Shot%202020-05-29%20at%202.26.20%20PM.png)

### ServletConfig类

ServletConfig 类从类名上来看， 就知道是 Servlet 程序的配置信息类。
Servlet 程序和 ServletConfig 对象都是由 Tomcat 负责创建， 我们负责使用。
Servlet 程序默认是第一次访问的时候创建， ServletConfig 是每个 Servlet 程序创建时， 就创建一个对应的 ServletConfig 对
象。

#### 1. ServletConfig 类的三大作用

1、 可以获取 Servlet 程序的别名 servlet-name 的值
2、 获取初始化参数 init-param
3、 获取 ServletContext 对象



### ServletContext 类

##### 1.什么是 ServletContext?

1、 ServletContext 是一个接口， 它表示 Servlet 上下文对象
2、 一个 web 工程， 只有一个 ServletContext 对象实例。
3、 ServletContext 对象是一个域对象。
4、 ServletContext 是在 web 工程部署启动的时候创建。 在 web 工程停止的时候销毁。



### HttpServletRequest 类

##### 1.HttpServletRequest 类有什么作用

每次只要有请求进入 Tomcat 服务器， Tomcat 服务器就会把请求过来的 HTTP 协议信息解析好封装到 Request 对象中。
然后传递到 service 方法（ doGet 和 doPost） 中给我们使用。 我们可以通过 HttpServletRequest 对象， 获取到所有请求的
信息。

##### 2. HttpServletRequest 类的常用方法

i. getRequestURI() 获取请求的资源路径
ii. getRequestURL() 获取请求的统一资源定位符（绝对路径）
iii. getRemoteHost() 获取客户端的 ip 地址
iv. getHeader() 获取请求头
v. getParameter() 获取请求的参数
vi. getParameterValues() 获取请求的参数（多个值的时候使用）
vii. getMethod() 获取请求的方式 GET 或 POST
viii. setAttribute(key, value); 设置域数据
ix. getAttribute(key); 获取域数据
x. getRequestDispatcher() 获取请求转发对象



##### 3. 如何获取请求参数

##### 4. 请求的转发

什么是请求的转发?
请求转发是指， 服务器收到请求后， 从一次资源跳转到另一个资源的操作叫请求转发。





