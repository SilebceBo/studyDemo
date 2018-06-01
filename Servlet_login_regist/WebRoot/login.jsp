<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login.jsp</title>
  </head>
  
  <body>
  	<p align="center">欢迎登录</p>
    <form action="<%=request.getContextPath()%>/servlet/Login" method="post">
	    <table align="center" border="0" width="500"> 
	        <tr>
	        	<td align="right">账号:</td>
	        	<td align="right"><input type="text" name="username" /></td>
	        </tr>
	        <tr>
	        	<td align="right">密码:</td>
	        	<td align="right"><input type="password" name="password" /></td>
	        </tr>
	        <tr>	
			 	<td align="right">验证码:</td>
			 	<td align="right">
			 		<input type="text" name="validateCode" />
			 	</td>
			 	<td> 
			 		<img alt="验证码看不清，换一张" id="validateCodeImg" src="${pageContext.request.contextPath}/servlet/RandomImage" onclick="changeImg()">
					<a href="javascript:void(0)" onclick="changeImg()">看不清，换一张</a>
			 	</td>
		 	</tr>
	        <tr>
	        	<td colspan="2" align ="center" height="40">
	        		<input type="submit" value="登录" />
	        		<input type="reset" value="重置" />
	        	</td>
	        </tr>
	    </table>
     </form>
     <script type="text/javascript">
		//刷新验证码
		function changeImg() {
			document.getElementById("validateCodeImg").src = "${pageContext.request.contextPath}/servlet/RandomImage?"
					+ Math.random();
		}
  	 </script>    
  </body>
</html>
