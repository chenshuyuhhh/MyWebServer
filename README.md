# MyWeb

`JAVA 网络编程` `Web 服务器` `GET` `HEAD` `Maven`


### 1. 程序使用说明

#### 1.1 程序功能
* 编程语言：JAVA
> 完成一个简单的 WEB 服务器的设计与实现，支持 HTTP GET、HEAD 方法。
>
> 启动程序之后可以在本地浏览器中通过 localhost:port 访问本地文件

#### 1.2 程序执行方式
1. 找到 Main 函数后，点击运行，根据提示在控制台输入自己想要连接的端口号

2. 当出现本地服务器开始启动时，可以通过在浏览器（建议使用 chrome 浏览器）输入 localhost:port/...(port 开始输入的端口号）访问服务器中的文件

>`ps` 本地服务器中存有的文件为 happy.html，index.html，当访问不存在的文件是会返回 error.html,当没有访问某个文件时，展示默认文档 index.html

### 2. 程序设计说明

#### 2.1 代码架构

**Main**
程序入口

**MyServer/WebServer**
内部有端口号作为成员变量，WebServer 内的 start() 方法启动服务器，通过实例化的 handler 对象，来处理套接字 serverSocket

**Handle/Handler** 
继承 handle 接口，实现这个接口的 doGet 和 doPost 方法，持有 HttpResponse 和 HttpRequest 的实例化对象作为成员变量

**Request/HttpRequest**
http 请求报文对应的实体类，可以将来自客服端的 http 请求报文的信息提取出来

**Response**

**/HttpResponse**
http 相应报文对应的实体类，具有获得 http 相应报文头部的方法，以及对应内容的方法。有一个 getHttp 抽象方法由子类实现，不同的子类对应不同的 http 请求方式 -- GET、HEAD

**/GetHttpResponse**
继承自 HttpResponse,实现了得到 http GET 方法对应的 http 响应报文方法

**/HeadHttpResponse**
继承自 HttpResponse,实现了得到 http HEAD 方法对应的 http 响应报文方法