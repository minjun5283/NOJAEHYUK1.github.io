package order.mvc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import order.OrderDBBean;
import order.OrderDataBean;
import product.productDBBean;

public class OrderMypageAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");   
		HttpSession session = request.getSession();
		   String id = (String)session.getAttribute("memId");
		   String img = null; 
		   int count = 0;
		   List orderList = null;
		   List imgList = new ArrayList();
		   OrderDataBean arti = null;
		   if(id != null){
			   OrderDBBean dpPro = OrderDBBean.getInstance();
			   productDBBean dbPro = productDBBean.getInstance();
		       count = dpPro.getListIdCount(id);
			   if (count > 0) {
			   orderList = dpPro.getListId(id);
			   }
			   
			   try {
				if(count !=0) {
				for(int i=0; i<orderList.size(); i++){  
			    arti = (OrderDataBean)orderList.get(i);
			    img = dbPro.getProductImg(arti.getProduct_num());
				imgList.add(img);
				}}
			} catch (Exception e) {
				e.printStackTrace();
			}
			   }
		   request.setAttribute("imgList", imgList);
		   request.setAttribute("orderList", orderList);
		   request.setAttribute("count", new Integer(count));
		return "/order/orderMypage.jsp";
	}
}
