package my.destiny.util.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//将结果集转换成bean对象的处理器
public class BeanHandler implements ResultSetHandler{
	private Class<?> clazz;

	public BeanHandler(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public Object handler(ResultSet rs){
		
		try {
			if(!rs.next()){
				return null;
			}
			
			Object bean = clazz.newInstance();
			//得到结果集的元数据
			ResultSetMetaData metaData = rs.getMetaData();
			//System.out.println(metaData);
			
			//得到结果集中有几列数据
			int columnCount = metaData.getColumnCount(); 
			
			for(int i=0; i<columnCount; i++){
				//得到每列的列名
				String coulmnName = metaData.getColumnName(i+1);
				Object coulmnData = rs.getObject(i+1);
				//反射出类上列名对应的属性
				Field f = clazz.getDeclaredField(coulmnName);
				f.setAccessible(true);
				f.set(bean, coulmnData);
				//System.out.println("bean"+bean);
			}
			return bean;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
