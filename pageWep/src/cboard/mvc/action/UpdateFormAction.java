package cboard.mvc.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cboard.CBoardDBBean;
import cboard.CBoardDataBean;
import controller.SuperAction;

public class UpdateFormAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		List productList1 = null;
		List productList2 = null;
		List productList3 = null;
		
		CBoardDBBean dbPro = CBoardDBBean.getInstance();
		CBoardDataBean article = null ;
		
		try {
			article = dbPro.updateGetArticle(num);
			productList1 = dbPro.getProductList("TOP");
			productList2 = dbPro.getProductList("BOTTOM");
			productList3 = dbPro.getProductList("OUT");
		} catch (Exception e) {e.printStackTrace();}
		

		
		request.setAttribute("pageNum", new Integer(pageNum) );
		request.setAttribute("article", article);
		request.setAttribute("productList1", productList1);
		request.setAttribute("productList2", productList2);
		request.setAttribute("productList3", productList3);
		return "/cboard/updateForm.jsp";
	}
}
//작성자: 안다인