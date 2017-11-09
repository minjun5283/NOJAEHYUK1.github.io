package order.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;
import order.OrderDBBean;
import order.OrderDataBean;

public class OrderDataAction  implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		OrderDataBean arti = null;  
		OrderDBBean dpPro = OrderDBBean.getInstance();
		try {
			arti = dpPro.getData(order_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("arti", arti);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("order_num", new Integer(order_num));
		return "/order/orderData.jsp";
	}

}
