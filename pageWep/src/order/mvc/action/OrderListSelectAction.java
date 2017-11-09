package order.mvc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import order.OrderDBBean;

public class OrderListSelectAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		int pageSize = 10;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
	    if(id.equals("admin")){
	        String pageNum = request.getParameter("pageNum");
	        String getTex = request.getParameter("getTex"); //검색 text
	        String selectPro = request.getParameter("selectPro"); //product_num/name/type
	        int i = 0;
	        
	        if (pageNum == null) {
	            pageNum = "1";
	            }

	        int currentPage = Integer.parseInt(pageNum);
	        int startRow = (currentPage - 1) * pageSize + 1;
	        int endRow = currentPage * pageSize;
	        int count = 0;
	        int number=0;
	        List orderList = null;
	        
	        OrderDBBean dpPro = OrderDBBean.getInstance();
	        try {
	        if(selectPro.equals("order_num")){
	        	count = dpPro.countOrder_num(getTex);
	        	if (count > 0) {
	        		orderList = dpPro.getListOrder_num(getTex);
	        	}
	        }else if(selectPro.equals("product_num")){
	        	count = dpPro.countProduct_num(getTex);
	        	if (count > 0) {
	        		orderList = dpPro.getListProduct_num(getTex);
	        	}
	        }else if(selectPro.equals("member_id")){
	        	count = dpPro.countMember_id(getTex);
	        	if (count > 0) {
	        		orderList = dpPro.getListMember_id(getTex);
	        	}
	        }else if(selectPro.equals("order_state")){
	        	count = dpPro.countOrder_state(getTex);
	        	if (count > 0) {
	        		orderList = dpPro.getListOrder_state(getTex);
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

	    	
	    	request.setAttribute("pageNum", pageNum);
	    	request.setAttribute("count", new Integer(count));
	    	request.setAttribute("orderList", orderList);
	        request.setAttribute("selectPro", selectPro);
	        request.setAttribute("getTex", getTex);
	    	
	    }
		return "/order/orderListSelect.jsp";
	}

}
