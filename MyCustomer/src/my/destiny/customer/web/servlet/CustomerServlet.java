package my.destiny.customer.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.destiny.customer.dao.CustomerDao;
import my.destiny.customer.daomain.Customer;
import my.destiny.util.common.CommonUtils;
import my.destiny.util.servlet.BaseServlet;

public class CustomerServlet extends BaseServlet{
	private CustomerDao customerDao = new CustomerDao();
	
	//添加客户
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取表单数据，封装到Customer中
		 * 2. 为Customer指定cid
		 * 3. 调用dao完成添加
		 * 4. 在request中添加成功信息
		 * 5. 转发到msg.jsp显示
		 */
		Customer customer = CommonUtils.tobean(request.getParameterMap(), Customer.class);
		customer.setCid(CommonUtils.uuid());
		customerDao.add(customer);
		request.setAttribute("msg", "添加客户成功！");
		return "f:/msg.jsp";
	}
	//查询所有
	public String findAll (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 调用dao获取List<Customer>
		 * 2. 保存到request中
		 * 3. 转发到list.jsp显示
		 */
		List<Customer> customerList = customerDao.findAll();
		request.setAttribute("customerList", customerList);
		return "f:/list.jsp";
	}
	//编辑之前的加载工作
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid
		 * 2. 使用cid来调用service方法，得到Customer对象
		 * 3. 把Customer保存到request域中
		 * 4. 转发到edit.jsp显示在表单中
		 */
		String cid = request.getParameter("cid");
		Customer customer = customerDao.load(cid);
		request.setAttribute("customer", customer);
		return "f:/edit.jsp";
	}
	
	// 编辑方法
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Customer对象中
		 * 2. 调用service方法完成修改
		 * 3. 保存成功信息到request域
		 * 4. 转发到msg.jsp显示成功信息
		 */
		Customer c = CommonUtils.tobean(request.getParameterMap(), Customer.class);
		customerDao.edit(c);
		request.setAttribute("msg", "编辑客户成功");
		return "f:/msg.jsp";
	}
	
	//删除方法
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid
		 * 2. 调用dao完成删除
		 * 3. 保存成功信息到request中
		 * 4. 转发到msg.jsp显示
		 */
		String cid = request.getParameter("cid");
		customerDao.delete(cid);
		request.setAttribute("msg", "删除客户成功");
		return "f:/msg.jsp";
	}
	
	//按条件查询
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Customer对象中，它只有四个属性（cname、gender、cellphone、email）
		 *   它就是一个条件
		 * 2. 使用Customer调用service方法，得到List<Customer>
		 * 3. 保存到request域中
		 * 4. 转发到list.jsp
		 */
		Customer criteria = CommonUtils.tobean(request.getParameterMap(), Customer.class);
		List<Customer> customerList = customerDao.query(criteria);
		request.setAttribute("customerList", customerList);
		return "f:/list.jsp";
	}
}
