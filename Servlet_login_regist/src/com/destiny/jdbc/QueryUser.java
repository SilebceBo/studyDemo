package com.destiny.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//数据库的查询

public class QueryUser {
	//定义变量
	private PreparedStatement pst = null;
	public String name;
	public String pwd;
	
	
	//查找账户
	public void getNameAndWord(String username, String password){
		//User user =null;
		ResultSet rs;
		String sql ="select * from user where username =? and password =?";
		
		try {
			pst = DBConnection.con.prepareStatement(sql);
			pst.setString(1, username);
	        pst.setString(2, password);
			
			rs = pst.executeQuery();
			if(rs.next()){
				name = rs.getString("username");
				pwd = rs.getString("password");
				
			}
			rs.close();
			//System.out.println(name);
			//System.out.println(pwd);
			System.out.println("数据库查询成功");
		} catch (SQLException e) {
			System.out.println("数据库查询失败");
			e.printStackTrace();
		}finally {
			try {
				//释放资源
				pst.close();
			} catch (SQLException e) {
				System.out.println("释放资源失败");
				e.printStackTrace();
			}
		}
	}
}