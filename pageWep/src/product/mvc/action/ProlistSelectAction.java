package product.mvc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import product.productDBBean;

public class ProlistSelectAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		int pageSize = 10;
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		String pageNum = request.getParameter("pageNum");
	    String getTex = request.getParameter("getTex"); //검색 text
	    String selectPro = request.getParameter("selectPro"); //product_num/name/type
	    
	     if (pageNum == null) {
	         pageNum = "1";
	         }

	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    int count = 0;
	    int number=0;
	    List proList = null;
	    
	    productDBBean dpPro = productDBBean.getInstance();
	    
	    if(selectPro.equals("product_num")){
	    	try {
				count = dpPro.countNum(getTex);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	if (count > 0) {
	    		proList = dpPro.getListNum(getTex);
	    	}
	    }else if(selectPro.equals("product_name")){
	    	try {
				count = dpPro.countName(getTex);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	if (count > 0) {
	    		proList = dpPro.getListName(getTex);
	    	}
	    }else if(selectPro.equals("product_type")){
	    	try {
				count = dpPro.countType(getTex);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	if (count > 0) {
	    		proList = dpPro.getListType(getTex);
	    	}
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
	    request.setAttribute("proList", proList);
        request.setAttribute("selectPro", selectPro);
        request.setAttribute("getTex", getTex);
	    return "/product/prolistSelect.jsp";
	 }

}

/* 작성자 : 노재혁  /수정날짜:20171025   */ 
