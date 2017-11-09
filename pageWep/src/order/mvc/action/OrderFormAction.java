package order.mvc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.CartDBBean;
import cart.CartDataBean;
import controller.SuperAction;
import product.ProductDataBean;
import product.productDBBean;

public class OrderFormAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		String ch = request.getParameter("ch");  
		int order_price = 0;
		int j = 0;
		String[] check = request.getParameterValues("check");	
		List checklist = new ArrayList();
		
		List proList1 = null;
		List cartList = null;
		productDBBean dpPro = productDBBean.getInstance();
		proList1 = dpPro.getListContent(product_num);
		   
		CartDBBean dbPro = CartDBBean.getInstance();
		if(ch.equals("true")){
		for(j=0; j<check.length; j++){
				try {
					cartList = dbPro.getCartListCheck(id,Integer.parseInt(check[j]));
					checklist.add(cartList);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}}else {
			 int order_count = Integer.parseInt(request.getParameter("order_count"));
			 String product_size = request.getParameter("product_size");
			 String product_color = request.getParameter("product_color");
			 request.setAttribute("order_count", new Integer(order_count));
			 request.setAttribute("product_size", product_size);
			 request.setAttribute("product_color", product_color);
			 for(int i=0; i<proList1.size(); i++) {
				 ProductDataBean arti = (ProductDataBean)proList1.get(i);
				 order_price = order_count * arti.getProduct_price();
				 request.setAttribute("order_price",new Integer(order_price));
			 }
			
		}
		
		request.setAttribute("ch", ch);
		request.setAttribute("id", id);
		request.setAttribute("proList1", proList1);
		request.setAttribute("checklist", checklist);
		request.setAttribute("j",new Integer(j));
	
		return "/order/orderForm.jsp";
	}
}
