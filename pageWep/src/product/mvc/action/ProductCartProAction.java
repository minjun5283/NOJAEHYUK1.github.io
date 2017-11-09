package product.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import product.productDBBean;

public class ProductCartProAction implements SuperAction {
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		int product_num =  Integer.parseInt(request.getParameter("product_num"));
		String product_color = request.getParameter("product_color");
		int order_count = Integer.parseInt(request.getParameter("order_count"));
		String product_size = request.getParameter("product_size");
		productDBBean dao = productDBBean.getInstance();
	
		boolean check = false ;
		
		//product_num 가져와서 
		try {
		check = dao.setCart(id,product_num ,product_color ,order_count, product_size);
		} catch (Exception e) {e.printStackTrace();}
		
		request.setAttribute("check", check);
		return "/product/productCartPro.jsp";
	}
}
