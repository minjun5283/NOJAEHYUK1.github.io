package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.SuperAction;

public class Controller extends HttpServlet{
	private Map map = new HashMap();
	public void init(ServletConfig config) throws ServletException{
		String pro = config.getInitParameter("properties");
		FileInputStream fis = null;
		Properties p = new Properties(); 
		try {
			fis = new FileInputStream(pro);
			p.load(fis);
			Enumeration enu  = p.keys();
			while(enu.hasMoreElements()) {
				String key = (String)enu.nextElement();
				String value = p.getProperty(key);
				Class c = Class.forName(value);
				Object obj = c.newInstance();
				map.put(key, obj); 
			}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String root = request.getContextPath(); 
		String uri = request.getRequestURI().substring(root.length());
		SuperAction sa = (SuperAction)map.get(uri);
		String view =sa.action(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request,response);
	}
}
