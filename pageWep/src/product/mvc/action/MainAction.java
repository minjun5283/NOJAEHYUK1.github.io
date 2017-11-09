package product.mvc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;
import product.productDBBean;

public class MainAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	request.setCharacterEncoding("UTF-8");
	int pageSize = 10;	
	String pageNum = request.getParameter("pageNum");
	String type =request.getParameter("type");
     if(type == null){
    	type = "";
     }
    
     if (pageNum == null) {
         pageNum = "1";
     }
     
    int currentPage = Integer.parseInt(pageNum);
    int startRow = (currentPage - 1) * pageSize + 1;
    int endRow = currentPage * pageSize;
    int count = 0;
    int number=0;
    List proList1 = null;
    
    productDBBean dpPro = productDBBean.getInstance();
    
    try {
    if(type.equals("")){
    	count = dpPro.getCountCheck();
        if (count > 0) {
    	    proList1 = dpPro.getListCheck(startRow, endRow);
    }	
    }else if(type.equals("TOP")){
       
       count = dpPro.getCountMenu(type);
       if (count > 0) {
    	   proList1 = dpPro.getListMenu(startRow, endRow, type);
    }
    
    }else if(type.equals("BOTTOM")){
    	count = dpPro.getCountMenu(type);
        if (count > 0) {
        	proList1 = dpPro.getListMenu(startRow, endRow, type);
        }
    	
    }else if(type.equals("OUT")){
    	count = dpPro.getCountMenu(type);
        if (count > 0) {
        	proList1 = dpPro.getListMenu(startRow, endRow, type);
        }
    } 
    }catch(Exception e) {
    	e.printStackTrace();
    }
    
    number=count-(currentPage-1)*pageSize;
    
    if (count > 0) { //글목록 있을경우 
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 
        int startPage = (int)(currentPage/10)*10+1;
		int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;
        request.setAttribute("startPage", new Integer(startPage));
        request.setAttribute("pageBlock", new Integer(pageBlock));
        request.setAttribute("endPage", new Integer(endPage));
    }
    
    
    request.setAttribute("number", new Integer(number));
    request.setAttribute("count", new Integer(count));
    request.setAttribute("pageNum", new Integer(pageNum));
    request.setAttribute("proList1", proList1);
    request.setAttribute("type", type);
	
    return "/product/main.jsp";
	}
}

/* 작성자 : 노재혁  /수정날짜:20171025   */ 
