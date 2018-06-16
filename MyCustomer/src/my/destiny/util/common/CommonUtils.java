package my.destiny.util.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

public class CommonUtils {
	
	//返回一个uuid
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	//创建一个bean，把数据封装到bean中返回
	public static <T> T tobean(Map datas, Class<T> clazz){
		//依赖commons-beanutils
		
		try {
			//1. 通过clazz创建bean实例
			T bean = clazz.newInstance();
			//注册类型转换器，用来把字符串转换成Date类型
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			//2. 把datas封装到bean中
			BeanUtils.populate(bean, datas);
			//3.返回Bean
			return bean;
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
