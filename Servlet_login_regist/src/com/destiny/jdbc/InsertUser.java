package com.destiny.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;



public class InsertUser {
	private PreparedStatement pst = null;
	
	public void addUser(String username, String password, String twopassword, String sex, String phone, String email) throws SQLException{
		
		String sql ="insert into user values(?, ?, ?, ?, ?, ?)";
		
		try {
			pst = DBConnection.con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, twopassword);
			pst.setString(4, sex);
			pst.setString(5, phone);
			pst.setString(6, email);
			
			pst.executeUpdate();
			System.out.println("添加内容成功");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("释放资源失败");
				//e.printStackTrace();
			}
		}
	}
}
