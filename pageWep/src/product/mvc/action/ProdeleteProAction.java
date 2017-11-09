package product.mvc.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;
import product.productDBBean;

public class ProdeleteProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		int product_num = Integer.parseInt(request.getParameter("product_num"));
		 String pageNum = request.getParameter("pageNum"); 
		 String sysname = request.getParameter("sysname"); 
		 
		 String path = "C:\\Users\\qudtj\\Desktop\\새 폴더\\pageWep (1)\\pageWep\\WebContent\\upload2";
		 System.out.println(product_num);
		 productDBBean dpPro = productDBBean.getInstance();
		 try {
			dpPro.deleteData(product_num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		 
		 System.out.println(sysname);
		 
		 
		 File file = new File(path+"\\"+sysname);
			if(file.exists() == true){
			file.delete();
			}	
		 
	    request.setAttribute("pageNum", new Integer(pageNum));
	    return "/product/prodeletePro.jsp";
	 }

}


/* 작성자 : 노재혁  /수정날짜:20171025   */ 
