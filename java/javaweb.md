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
2. jboss
3. GlassFish
4. Resin
5. Weblogic

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

#### servlet 与 springmvc的关系