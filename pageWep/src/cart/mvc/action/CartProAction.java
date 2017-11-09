package cart.mvc.action;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.CartDBBean;
import cart.CartDataBean;
import controller.SuperAction;

public class CartProAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {

		String [] check = request.getParameterValues("check");
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		CartDataBean cart = null;
		List cartList = new ArrayList();
		CartDBBean dao = CartDBBean.getInstance();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		boolean ch = true;
		try {
			for(String num : check) {
				cart = new CartDataBean();
				cart = dao.getCart(num, id);
				cartList.add(cart);
			}
		}catch (Exception e) {e.printStackTrace();}
		request.setAttribute("cartList", cartList);
		request.setAttribute("ch", ch);
		request.setAttribute("product_num", new Integer(product_num));
		return "/cart/cartPro.jsp";
	}
}
