package my.destiny.util.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//将结果集转换成bean对象的list集合的处理器
public class BeanListHandler<T> implements ResultSetHandler {
    private Class<?> clazz;
    
    public BeanListHandler(Class<?> clazz){
        this.clazz = clazz;
    }
    
    public Object handler(ResultSet rs) {
        try{
            List<Object> list = new ArrayList<Object>();
            while(rs.next()){
                Object bean = clazz.newInstance();
                
              	//得到结果集的元数据
                ResultSetMetaData  metadata = rs.getMetaData();
                
                //得到结果集中有几列数据
                int count = metadata.getColumnCount();
                
                for(int i=0;i<count;i++){
                	//得到每列的列名
                    String name = metadata.getColumnName(i+1);
                    Object value = rs.getObject(name);
                    //System.out.println(value);
                    
                    //反射出类上列名对应的属性
                    Field f = bean.getClass().getDeclaredField(name);
                    f.setAccessible(true);
                    f.set(bean, value);
                }
                list.add(bean);
            }
            return list.size()>0?list:null;
        
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
