package com.destiny.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.destiny.jdbc.DBConnection;
import com.destiny.jdbc.InsertUser;
import com.destiny.jdbc.QueryUser;

public class Regist extends HttpServlet{
	//创建对象
	DBConnection db = new DBConnection();
	QueryUser qu = new QueryUser();
	InsertUser iu = new InsertUser();
	
	//定义变量
	private String username;
	private String password;
	private String twopassword;
	private String sex;
	private String phone;
	private String email;
	
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
		twopassword = request.getParameter("twopassword");
		sex = request.getParameter("sex");
		phone = request.getParameter("phone");
		email = request.getParameter("email");
		
		//System.out.println(username);
		//System.out.println(sex);
		//PrintWriter out = response.getWriter();
		
		//定义手机号和邮箱的正则表达式
		String regex1 = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
		String regex2 = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]{2,6}(\\.[a-zA-Z_0-9]{2,3})+";
		
		//调用查询函数，看表单中的账户密码是否和数据库中的一样
		qu.getNameAndWord(username, password);
		if(username != null && username.equals(qu.name)){
			System.out.println("用户名重复");
			//out.write("<script>alert(\"用户名重复!\");</script>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}else if(!password.equals(twopassword)){
			System.out.println("确认密码输入错误");
			//out.write("<script>alert(\"确认密码输入错误!\");</script>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}else if(!phone.matches(regex1)){
			System.out.println("手机号不符合规则");
			//out.write("<script>alert(\"手机号不符合规则!\");</script>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}else if(!email.matches(regex2)){
			System.out.println("邮箱不符合规则");
			//out.write("<script>alert(\"邮箱不符合规则!\");</script>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}else{
			try {
				iu.addUser(username, password, twopassword, sex, phone, email);
				System.out.println("注册成功");
				//out.write("<script>alert(\"注册成功!\");</script>");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} catch (SQLException e) {
				System.out.println("注册失败");
				e.printStackTrace();
			}
		}
		
		
		db.colseConnection();
		doGet(request, response);
	}

}
