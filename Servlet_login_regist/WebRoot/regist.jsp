<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>regist</title>
  </head>
  <body>
  	<p align="center">欢迎注册</p>
    <form action="<%=request.getContextPath()%>/servlet/Regist" method="post">
		<table align="center" border="0" width="500">  
		    <tr>  
		        <td align="right" width="30%">账户：</td>  
		        <td><input type="text" name="username"></td>  
		    </tr>  
		    <tr>  
		        <td align="right">密 码：</td>  
		        <td><input type="password" name="password"></td>  
		    </tr>  
		    <tr>  
		        <td align="right">确认密码：</td>  
		        <td><input type="password" name="twopassword"></td>  
		    </tr>  
		    <tr>   
		        <td align="right">性 别：</td>  
		        <td>  
		            <input type="radio" name="sex" value="0" checked="checked">男  
		            <input type="radio" name="sex" value="1">女  
		        </td>  
		    </tr>  
		    <tr>  
		        <td align="right">手机号：</td>  
		        <td><input type="text" name="phone"></td>  
		    </tr>   
		    <tr>  
		        <td align="right">邮 箱：</td>  
		        <td><input type="text" name="email"></td>  
		    </tr>  
		    <tr>  
		        <td colspan="2" align ="center" height="40">  
		            <input type="submit" value="注 册">  
		            <input type="reset" value="重 置">  
		        </td>  
		    </tr>  
		</table> 
  	</form> 
  </body>
</html>
