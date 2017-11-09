package product.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import product.ProductDataBean;
import product.productDBBean;

public class ProupdateFormAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		String pageNum = request.getParameter("pageNum");
		String sizes = "";
		String [] sizedata ={};
		ProductDataBean arti = new ProductDataBean();
		try{
			productDBBean dbpro = productDBBean.getInstance();
			sizes = dbpro.getSizes(product_num);
			sizedata = sizes.split(",");
			arti = dbpro.getdata(product_num);
		}catch(Exception e){
			   e.printStackTrace();
	    }
		
		request.setAttribute("product_num", new Integer(product_num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("arti", arti);
	    return "/product/proupdateForm.jsp";
	 }
}


/* 작성자 : 노재혁  /수정날짜:20171025   */ 