package order.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;
import order.OrderDBBean;

public class OrderdeleteProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		String pageNum = request.getParameter("pageNum"); 
		String[] check = request.getParameterValues("check");

		 
		 OrderDBBean dpPro = OrderDBBean.getInstance();
		 for(int i=0; i<check.length; i++){
		 try {
			dpPro.deleteData(Integer.parseInt(check[i]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 }
		 
		request.setAttribute("pageNum", pageNum);
		
		return "/order/orderdeletePro.jsp";
	}

}
