package my.destiny.util.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class GetRequest extends HttpServletRequestWrapper{

	public GetRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value == null){
			return null;
		}
			try {
				return new String(value.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getParameterMap(){
		Map<String,String[]> map = new HashMap<String,String[]>(super.getParameterMap());
		if(map == null){
			return null;
		}
		for(String name: map.keySet()){
			String[] values = map.get(name);
			for(int i=0; i<values.length; i++){
				try {
					values[0] = new String(values[0].getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				map.put(name, values);
			}
			return map;
		}
		return null;
	}
}
