package cart.mvc.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.CartDBBean;
import controller.SuperAction;

public class CartAction implements SuperAction {
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		HttpSession session = request.getSession();
		CartDBBean cart = CartDBBean.getInstance();
		int totalPrice = 0;
		List cartList =null;
		boolean ch = true;
		
		String id = (String)session.getAttribute("memId");
		try {
			cartList = cart.getCartList(id);
			totalPrice = cart.totalPrice(id);
		} catch (Exception e) {e.printStackTrace();}
		request.setAttribute("totalPrice",totalPrice);
		request.setAttribute("memId", id);
		request.setAttribute("cartList", cartList);
		request.setAttribute("ch", ch);
		
		return "/cart/cart.jsp";
	}
}
