package my.destiny.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.destiny.dao.Dao;
import my.destiny.domain.Province;
import net.sf.json.JSONArray;

public class ProvinceServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1. 通过dao得到所有的省
		 * 2. 把List<Province>转换成json
		 * 3. 发送给客户端
		 */
		Dao dao = new Dao();
		List<Province> provinceList = dao.findAllProvince();
		String json = JSONArray.fromObject(provinceList).toString();
		
		response.getWriter().print(json);
		//System.out.println(json);
	}

}
