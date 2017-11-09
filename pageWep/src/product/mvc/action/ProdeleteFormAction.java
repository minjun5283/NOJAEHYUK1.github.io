package product.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import product.productDBBean;

public class ProdeleteFormAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		 int product_num = Integer.parseInt(request.getParameter("product_num"));
		 String pageNum = request.getParameter("pageNum"); 
		 String sysname = "";
		 productDBBean dpPro = productDBBean.getInstance();
		 try {
			sysname = dpPro.selectSys(product_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("product_num", new Integer(product_num));
		request.setAttribute("sysname", sysname);
	    return "/product/prodeleteForm.jsp";
	 }

}

/* 작성자 : 노재혁  /수정날짜:20171025   */ 
