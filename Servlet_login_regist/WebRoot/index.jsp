<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//获得项目的绝对路径
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>index</title>
  </head>
  
  <body>
  	<p align ="center">欢迎来到XX网</p><br>
  	<p align ="center">
  		<a href="login.jsp">登录</a>
  		<a href="regist.jsp">注册</a>
  	</p>
  </body>
 </html>
