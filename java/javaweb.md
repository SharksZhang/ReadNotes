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



### HttpServletResponse

#### HttpServletResponse 类的作用

HttpServletResponse 类和 HttpServletRequest 类一样。 每次请求进来， Tomcat 服务器都会创建一Response 对象传递给 Servlet 程序去使用。 HttpServletRequest 表示请求过来的信息， HttpServletResponse 表示所有响应的信息，我们如果需要设置返回给客户端的信息， 都可以通过 HttpServletResponse 对象来进行设置

#### 两个输出流

```
字节流 getOutputStream(); 常用于下载（传递二进制数据）
字符流 getWriter(); 常用于回传字符串（常用）
```

两个流同时只能使用一个。
使用了字节流， 就不能再使用字符流， 反之亦然， 否则就会报错。

#### 如何往客户端回传数据

```
public class ResponseIOServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
IOException {
// 要求 ： 往客户端回传 字符串 数据。
PrintWriter writer = resp.getWriter();
writer.write("response's content!!!");
}
}
```

#### 响应的乱码解决

```
resp.setContentType("text/html; charset=UTF-8");
```



#### 请求重定向

请求重定向， 是指客户端给服务器发请求， 然后服务器告诉客户端说。 我给你一些地址。 你去新地址访问。 叫请求重定向（因为之前的地址可能已经被废弃） 。

![Screen Shot 2020-06-05 at 1.09.43 PM](Screen%20Shot%202020-06-05%20at%201.09.43%20PM.png)

```
resp.sendRedirect("http://localhost:8080");
```

### Cookie

#### 什么是 Cookie?

1.  Cookie 是服务器通知客户端保存键值对的一种技术。
2.  客户端有了 Cookie 后， 每次请求都发送给服务器。
3.  每个 Cookie 的大小不能超过 4kb

#### 如何创建 Cookie

#### 如何获取cookie

```
        Cookie cookie = new Cookie("key1", "value1");
        Cookie cookie2 = new Cookie("key2", "value2");
        resp.addCookie(cookie);
        resp.addCookie(cookie2);

        resp.getWriter().write("success");

        for (Cookie reqCookie : req.getCookies()) {
            System.out.println("name:" + reqCookie.getName());
            System.out.println("value:" + reqCookie.getValue());
            System.out.println("domain" + reqCookie.getDomain());
            System.out.println("path" + reqCookie.getPath());

        }

```

#### cookie的属性

- name
   代表cookie的名字一个域名下绑定的cookie，name不能相同，相同的name的值会被覆盖掉。
- value
   表示cookie的值，值得注意的是用 JavaScript 操作 Cookie 的时候注意对 value 进行编码处理。

> 由于cookie规定是名称/值是不允许包含分号，逗号，空格的，所以为了不给用户到来麻烦，考虑服务器的兼容性，任何存储cookie的数据都应该被编码.

- domain
   这个是指的域名，这个代表的是，cookie绑定的域名，如果没有设置，就会自动绑定到执行语句的当前域.
- path
   path 指定了一个 URL 路径，这个路径必须出现在要请求的资源的路径中才可以发送 Cookie 首部。path这个属性默认是'/'，这个值匹配的是web的路由，举个例子：比如设置 Path=/blog，其实它会给/blog、/blogabc等等的都会带 Cookie 首部。/test 则不会携带 Cookie 。



```cpp
//默认路径
www.test.com
//blog路径携带Cookie
www.test.com/blog
//不携带
www.test.com/test
```

- expires
   expires 用于设置 Cookie 的过期时间。一般浏览器的Cookie都是默认储存的，当关闭浏览器结束这个会话的时候，这个cookie也就会被删除是**会话性 Cookie**，其值保存在客户端内存中。与会话性 Cookie 相对的是**持久性 Cookie**，持久性 Cookies 会保存在用户的硬盘中，直至过期或者清除 Cookie。而设定的日期和时间只与客户端相关，非服务端。
   举例来说：如果你想要cookie存在一段时间，那么你可以通过设置expires属性为未来的一个时间节点，expires代表当前时间。

- Max-Age
   Max-Age 用于设置在 Cookie 失效之前需要经过的秒数。比如：

  

  ```dart
  Set-Cookie: id=a3fWa; Max-Age=604800;
  ```

  Max-Age 可以为正数、负数、甚至是 0。
   如果 max-Age 属性为正数时，浏览器会将其持久化，即写到对应的 Cookie 文件中。

1. 当 max-Age 属性为负数，则表示该 Cookie 只是一个会话性 Cookie。
2. 当 max-Age 为 0 时，则会立即删除这个 Cookie。
3. 当 expires 和 Max-Age 都存在，Max-Age 优先级更高。
    **不能跨域设置 Cookie**，例如：在test的域名下设置



```dart
Set-Cookie: qwerty=219ffwef9w0f; security; Domain=baidu.com; Path=/; Expires=Wed, 30 Aug 2020 00:00:00 GMT
```

是无效的，因为domain 和path共同定义了 Cookie 的作用域。

### Session

#### 什么是 Session 会话?

1、 Session 就一个接口（HttpSession） 。
2、 Session 就是会话。 它是用来维护一个客户端和服务器之间关联的一种技术。
3、 每个客户端都有自己的一个 Session 会话。
4、 Session 会话中， 我们经常用来保存用户登录之后的信息。

#### 如何创建 Session 和获取(id 号,是否为新)

equest.getSession()
第一次调用是： 创建 Session 会话
之后调用都是： 获取前面创建好的 Session 会话对象。
isNew(); 判断到底是不是刚创建出来的（新的）
true 表示刚创建
false 表示获取之前创建
每个会话都有一个身份证号。 也就是 ID 值。 而且这个 ID 是唯一的。
getId() 得到 Session 的会话 id 值。



#### Session 生命周期控制

public void setMaxInactiveInterval(int interval) 设置 Session 的超时时间（ 以秒为单位） ， 超过指定的时长， Session就会被销毁。
值为正数的时候， 设定 Session 的超时时长。
负数表示永不超时（极少使用）
public int getMaxInactiveInterval()获取 Session 的超时时间
public void invalidate() 让当前 Session 会话马上超时无效



**Session 默认的超时时间长为 30 分钟**

#### 浏览器和 Session 之间关联的技术内幕

![Screen Shot 2020-06-05 at 1.57.19 PM](Screen%20Shot%202020-06-05%20at%201.57.19%20PM.png)



### Filter 

#### 什么是过滤器

1. Filter 过滤器它是 JavaWeb 的三大组件之一。 三大组件分别是： Servlet 程序、 Listener 监听器、 Filter 过滤器
2.  Filter 过滤器它是 JavaEE 的规范。 也就是接口
3.  Filter 过滤器它的作用是： 拦截请求， 过滤响应

#### 如何使用过滤器

filter 过滤器的使用步骤：
1、 编写一个类去实现 Filter 接口
2、 实现过滤方法 doFilter()
3、 到 web.xml 中去配置 Filter 的拦截路径

```
    <filter>
        <!--给 filter 起一个别名-->
        <filter-name>TestFilter</filter-name>
        <!--配置 filter 的全类名-->
        <filter-class>com.ericzhang08.filter.TestFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>TestFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```



#### Filter 的生命周期

Filter 的生命周期包含几个方法
1、 构造器方法
2、 init 初始化方法
第 1， 2 步， 在 web 工程启动的时候执行（Filter 已经创建）
3、 doFilter 过滤方法
第 3 步， 每次拦截到请求， 就会执行
4、 destroy 销毁
第 4 步， 停止 web 工程的时候， 就会执行（停止 web 工程， 也会销毁 Filter 过滤器）



#### FilterChain 过滤器链

![Screen Shot 2020-06-05 at 2.11.21 PM](Screen%20Shot%202020-06-05%20at%202.11.21%20PM.png)

