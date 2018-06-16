package my.destiny.util.jdbc;

import java.sql.ResultSet;

//结果集处理器接口
public interface ResultSetHandler {
     
	//结果集处理方法
    public Object handler(ResultSet rs);
}
