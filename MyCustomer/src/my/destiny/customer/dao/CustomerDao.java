package my.destiny.customer.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.destiny.customer.daomain.Customer;

import my.destiny.util.jdbc.BeanHandler;
import my.destiny.util.jdbc.BeanListHandler;
import my.destiny.util.jdbc.JdbcUtils;

public class CustomerDao {
	//private JdbcUtils ju = new JdbcUtils();
	
	//添加客户
	public void add(Customer c){
		String sql = "insert into t_customer values(?,?,?,?,?,?,?)";
		Object[] params ={c.getCid(), c.getCname(), c.getGender() 
				,c.getBirthday(), c.getCellphone(), c.getEmail()
				,c.getDescription()};
		JdbcUtils.update(sql, params);
	}
	
	//查询所有
	@SuppressWarnings("unchecked")
	public List<Customer> findAll(){
		String sql = "select * from t_customer";
		Object[] params ={};
		try {
			return (List<Customer>) JdbcUtils.query(sql, params, new BeanListHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//加载客户
	public Customer load(String cid) {
		String sql = "select * from t_customer where cid=?";
		Object[] params ={cid};
		try {
			return (Customer) JdbcUtils.query(sql, params, new BeanHandler(Customer.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//编辑客户
	public void edit(Customer c){
		String sql = "update t_customer set cname=?,gender=?,birthday=?,"+
						"cellphone=?,email=?,description=? where cid=?";
		Object[] params = {c.getCname(), c.getGender(),
				c.getBirthday(), c.getCellphone(), c.getEmail(),
				c.getDescription(), c.getCid()};
		JdbcUtils.update(sql, params);
	}
	//删除客户
	public void delete(String cid){
		String sql = "delete from t_customer where cid=?";
		Object[] params = {cid};
		JdbcUtils.update(sql, params);
	}
	//多条件组合查询
	@SuppressWarnings("unchecked")
	public List<Customer> query(Customer criteria){
		/*
		 * 1. 给出sql模板
		 * 2. 给出参数
		 * 3. 调用query方法，使用结果集处理器：BeanListHandler
		 */
		/*
		 * 一、　给出sql模板
		 * 二、　给出参数！
		 */
		/*
		 * 1. 给出一个sql语句前半部
		 */
		StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
		/*
		 * 2. 判断条件，完成向sql中追加where子句
		 */
		/*
		 * 3. 创建一个ArrayList，用来装载参数值
		 */
		List<Object> params = new ArrayList<Object>();
		String cname = criteria.getCname();
		if(cname != null && !cname.trim().isEmpty()){
			sql.append(" and cname like ?");
			params.add("%" + cname + "%");
		}
		
		String gender = criteria.getGender();
		if(gender != null && !gender.trim().isEmpty()) {
			sql.append(" and gender=?");
			params.add(gender);
		}
		
		String cellphone = criteria.getCellphone();
		if(cellphone != null && !cellphone.trim().isEmpty()) {
			sql.append(" and cellphone like ?");
			params.add("%" + cellphone + "%");
		}
		
		String email = criteria.getEmail();
		if(email != null && !email.trim().isEmpty()) {
			sql.append(" and email like ?");
			params.add("%" + email + "%");
		}
		
		/*
		 * 三、执行query
		 */
		try {
			return (List<Customer>) JdbcUtils.query(sql.toString(), params.toArray(), new BeanListHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
