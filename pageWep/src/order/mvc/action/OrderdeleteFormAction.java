package order.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;

public class OrderdeleteFormAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	    String pageNum = request.getParameter("pageNum");
	    int order_num = Integer.parseInt(request.getParameter("order_num"));
	    String[] check = request.getParameterValues("check");
		int checklength = check.length;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		request.setAttribute("order_num", new Integer(order_num));
		request.setAttribute("checklength", new Integer(checklength));
		return "/order/orderdeleteForm.jsp";
	}

}
