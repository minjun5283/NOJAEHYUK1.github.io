package order.mvc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import controller.SuperAction;
import order.OrderDBBean;
import order.OrderDataBean;

public class OrderListAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		int pageSize = 10;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		if(id.equals("admin")){
			
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
		   pageNum = "1";
		}
		
		
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    int count = 0;
	    int number=0;
	    int i = 0;
	    List orderList = null;
	    
	    OrderDBBean dpPro = OrderDBBean.getInstance();
	    try {
			count = dpPro.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    System.out.println(count);
	    
	    if (count > 0) {
	    	orderList = dpPro.getList();
	    }

		number=count-(currentPage-1)*pageSize;
		OrderDataBean arti = null;
		
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
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("orderList", orderList);
		request.setAttribute("count", new Integer(count));
		}
		
		
		return "/order/orderList.jsp";
	}

}
