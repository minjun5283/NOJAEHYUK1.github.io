package product.mvc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import product.productDBBean;

public class ProcontentAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		int product_num = Integer.parseInt(request.getParameter("product_num"));
	    int order_count = 1;
	    String sizes = "";
	    String [] sizedata = {};
	    String colors = "";
	    String [] colordata = {};
	    
	    List proList1 = null;
	    productDBBean dpPro = productDBBean.getInstance();
	    proList1 = dpPro.getListContent(product_num);
	    
	    try {
			sizes = dpPro.getSizes(product_num);
			sizedata = sizes.split(",");
	        colors = dpPro.getColors(product_num);
	        colordata = colors.split(",");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    request.setAttribute("proList1", proList1);
	    request.setAttribute("sizes", sizes);
	    request.setAttribute("sizedata", sizedata);
	    request.setAttribute("colors", colors);
	    request.setAttribute("colordata", colordata);
	    request.setAttribute("order_count", new Integer(order_count));
	    request.setAttribute("product_num", new Integer(product_num));
	    
		return "/product/procontent.jsp";	
	}
}

/* 작성자 : 노재혁  /수정날짜:20171025   */ 
