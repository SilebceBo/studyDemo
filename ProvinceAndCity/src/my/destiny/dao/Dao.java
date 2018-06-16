package my.destiny.dao;

import java.sql.SQLException;
import java.util.List;

import my.destiny.domain.City;
import my.destiny.domain.Province;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class Dao {
	
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 查询所有的省
	 * @return
	 */
	public List<Province> findAllProvince(){	
		try {
			String sql ="select * from t_province";
			return qr.query(sql, new BeanListHandler<Province>(Province.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询指定省下的所有市
	 * @param proName
	 * @return
	 */
	public List<City> findByProvince(int pid){
		String sql ="select * from t_city where pid=?";
		try {
			return qr.query(sql, new BeanListHandler<City>(City.class),pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
