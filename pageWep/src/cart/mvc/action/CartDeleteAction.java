package cart.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.CartDBBean;
import controller.SuperAction;

public class CartDeleteAction implements SuperAction {
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		HttpSession session = request.getSession();
		CartDBBean cart = CartDBBean.getInstance();
		String id = (String)session.getAttribute("memId");
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		
		try {
			cart.cartDelete(id,product_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/cart/cartDelete.jsp";
	}
}
