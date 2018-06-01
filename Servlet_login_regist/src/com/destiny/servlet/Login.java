package com.destiny.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.destiny.jdbc.DBConnection;
import com.destiny.jdbc.QueryUser;

public class Login extends HttpServlet{
	//创建对象
	DBConnection db = new DBConnection();
	QueryUser qu = new QueryUser();
	
	
	//定义变量
	private String username;
	private String password;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//连接数据库
		db.connection();
		//设置编码
		request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charst=utf-8");
		
		//获取表单中的数据
		username = request.getParameter("username");
		password = request.getParameter("password");
		//System.out.println(username);
		//System.out.println(password);
		
		//接收客戶端浏览器提交上来的验证码
		String clientCheckcode = request.getParameter("validateCode");
		//从服务器端的session中取出验证码
		String serverCheckcode = (String)request.getSession().getAttribute("checkcode");
		System.out.println(clientCheckcode);
		System.out.println(serverCheckcode);
		//PrintWriter out = response.getWriter();
		
		//调用查询函数，看表单中的账户密码是否和数据库中的一样
		qu.getNameAndWord(username, password);
		if(username != null && password !=null && username.equals(qu.name) && password.equals(qu.pwd)){
			if(!clientCheckcode.equals(serverCheckcode)){
				System.out.println("验证码错误!");
				//out.write("<script>alert(\"验证码错误!\");</script>");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				System.out.println("登录成功");
				//out.write("<script>alert(\"登录成功!\");</script>");
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		}else{
			System.out.println("登录失败");
			//out.write("<script>alert(\"账号或密码错误!\");</script>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
		db.colseConnection();
		doGet(request, response);
	}

}
