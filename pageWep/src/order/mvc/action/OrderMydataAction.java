package order.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;
import order.OrderDBBean;
import order.OrderDataBean;
import product.productDBBean;

public class OrderMydataAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		
		   request.setCharacterEncoding("UTF-8");
		   int order_num = Integer.parseInt(request.getParameter("order_num"));
		   String order_state = request.getParameter("order_state");
		   int x = 0;
		   
		   OrderDataBean article = null;
		   OrderDBBean dpPro = OrderDBBean.getInstance();
		   productDBBean dbPro = productDBBean.getInstance();
		   try {
		   dpPro.updateState(order_num, order_state);
		   article = dpPro.getData(order_num);
		   		   
		   x = (dbPro.getTotal(article.getProduct_num()) + article.getOrder_count());
		   dbPro.getTotalInsert(x, article.getProduct_num());
		   }catch(Exception e) {	   
			  e.printStackTrace(); 
		   }
		   
		return "/order/orderMydata.jsp";
	}
	}
