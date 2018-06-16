package my.destiny.util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {
	
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	
	
	/*static{
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useUnicode=true&createDatabaseIfNoExist=true";
		username = "root";
		password = "root";
		
		//加载数据库驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	static{
		try {
			//读取db.properties文件中的数据库连接信息
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			System.out.println(in);
			Properties prop = new Properties();
			System.out.println("prop"+prop);
			prop.load(in);
			System.out.println("prop"+prop);
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			//加载数据库驱动
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获取数据库连接对象
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	//释放资源
	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs){
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//万能更新
	public static void update(String sql, Object params[]) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			
			for(int i=0; i<params.length; i++){
				pst.setObject(i+1, params[i]);
			}
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(conn, pst, rs);
		}
	}
	
	//万能查询
	public static Object query(String sql,Object params[],ResultSetHandler rsh) throws SQLException{
		 Connection conn = null;
		 PreparedStatement pst = null;
		 ResultSet rs = null;
		 
		 try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			
			for(int i=0; i<params.length; i++){
				pst.setObject(i+1, params[i]);
			}
			
			rs = pst.executeQuery();
			//System.out.println(rs);
			//自定义结果集,用户只要实现了ResultSetHandler接口，那么query方法内部就知道用户要如何处理结果集了
			return rsh.handler(rs);
		}finally{
			closeConnection(conn, pst, rs);
		}
	}
}
