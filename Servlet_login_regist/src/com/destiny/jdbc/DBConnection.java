package com.destiny.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//数据库的连接

public class DBConnection {
	//定义变量
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useUnicode=true&createDatabaseIfNoExist=true";
	private static String username = "root";
	private static String password = "zhaobo..";
	
	static Connection con =null;
	
	public void connection(){
		try {
			Class.forName(driver);
			System.out.println("数据库驱动成功！");
			
			con = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接成功！");
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动失败！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
	}
	
	//断开与数据库的连接
	public void colseConnection(){
		try {
			con.close();
			System.out.println("与数据库断开连接成功");
		} catch (SQLException e) {
			System.out.println("与数据库断开连接失败");
			e.printStackTrace();
		}
	}
}