package product.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;

public class ProwriterFormAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
	    int product_num=0;
	    
	    try{ 
	        if(request.getParameter("product_num")!=null){
	     	   product_num=Integer.parseInt(request.getParameter("product_num"));
	 	   }}catch(Exception e) {
	 		   e.printStackTrace();
	 	   }
	    
	    request.setAttribute("product_num", product_num);
	    return "/product/prowriterForm.jsp";
	 }
}

/* 작성자 : 노재혁  /수정날짜:20171025   */ 
