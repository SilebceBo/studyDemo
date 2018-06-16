package my.destiny.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.destiny.dao.Dao;
import my.destiny.domain.City;
import net.sf.json.JSONArray;

public class CityServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1. 获取名为pid的参数
		 * 2. 使用这个省的id查询数据库，得到List<City>
		 * 3. 转发成json，转发给客户端
		 */
		int pid = Integer.parseInt(request.getParameter("pid"));
		//System.out.println(pid);
		Dao dao = new Dao();
		List<City> cityList = dao.findByProvince(pid);
		String json = JSONArray.fromObject(cityList).toString();
		
		response.getWriter().print(json);
		//System.out.println(json);
	}

}
