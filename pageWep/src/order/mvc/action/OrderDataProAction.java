package order.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;
import order.OrderDBBean;

public class OrderDataProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		String order_state = request.getParameter("order_state");
		OrderDBBean dpPro = OrderDBBean.getInstance();
		try {
			dpPro.getStateUpdate(order_state, order_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("order_num", new Integer(order_num));
		   
		
		return "/order/orderDataPro.jsp";
	}

}
