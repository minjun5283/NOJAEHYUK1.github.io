package order.mvc.action;

import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.CartDBBean;
import cart.CartDataBean;
import controller.SuperAction;
import order.OrderDBBean;
import order.OrderDataBean;
import product.productDBBean;

public class OrderFormProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		int x = 0;
	    String[] check1 = request.getParameterValues("check1");
	    String ch = request.getParameter("ch");
	    HttpSession session =request.getSession();
	    String id = (String)session.getAttribute("memId");
	    List cartList = null;
	    OrderDataBean arti = new OrderDataBean();
	    CartDBBean dnPro = CartDBBean.getInstance();
	    productDBBean dbPro = productDBBean.getInstance();
	    try {
	    if(ch.equals("true")){
	    	for(int i=0; i<check1.length; i++){
	    		cartList = dnPro.getCartListCheck(id,Integer.parseInt(check1[i]));
	    		for(int j=0; j<cartList.size(); j++){ 
	    			  CartDataBean article = (CartDataBean)cartList.get(j);  
	    			  
	     	    arti.setProduct_num(article.getProduct_num());
	    	    arti.setMember_id(request.getParameter("member_id"));
	    	    arti.setOrder_price(article.getProduct_price());
	    	    arti.setOrder_size(article.getProduct_size());
	    	    arti.setOrder_color(article.getProduct_color());
	    	    arti.setOrder_date(new Timestamp(System.currentTimeMillis()));
	    	    arti.setOrder_count(article.getAmount());
	    	    arti.setOrder_phone(request.getParameter("order_phone"));
	    	    arti.setOrder_add(request.getParameter("order_add"));
	    	    arti.setOrder_etc(request.getParameter("order_etc"));
	    	    
	    	    OrderDBBean dpPro = OrderDBBean.getInstance();
	    	    dpPro.insertData(arti);
	    	    
	    	    x = (dbPro.getTotal(article.getProduct_num()) - article.getAmount());
	    	    System.out.println(x);
	    	    System.out.println(article.getProduct_num());
	    	    dbPro.getTotalInsert(x, article.getProduct_num());
	    		
	    }}	
	    }else{
	    arti.setProduct_num(Integer.parseInt(request.getParameter("product_num")));
	    arti.setMember_id(request.getParameter("member_id"));
	    arti.setOrder_price(Integer.parseInt(request.getParameter("order_price")));
	    arti.setOrder_size(request.getParameter("order_size"));
	    arti.setOrder_color(request.getParameter("order_color"));
	    arti.setOrder_date(new Timestamp(System.currentTimeMillis()));
	    arti.setOrder_count(Integer.parseInt(request.getParameter("order_count")));
	    arti.setOrder_phone(request.getParameter("order_phone"));
	    arti.setOrder_add(request.getParameter("order_add"));
	    arti.setOrder_etc(request.getParameter("order_etc"));
	    
	    OrderDBBean dpPro = OrderDBBean.getInstance();
	    dpPro.insertData(arti);
	    
	    x = (dbPro.getTotal(Integer.parseInt(request.getParameter("product_num"))) - Integer.parseInt(request.getParameter("order_count")));
	    System.out.println(x);
	    System.out.println(Integer.parseInt(request.getParameter("product_num")));
	    dbPro.getTotalInsert(x, Integer.parseInt(request.getParameter("product_num")));
	    }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		
		return "/order/orderFormPro.jsp";
	}

}
