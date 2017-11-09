package cboard.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cboard.CBoardDBBean;
import cboard.CBoardDataBean;
import controller.SuperAction;
import product.ProductDataBean;

public class ContentAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		CBoardDBBean dbPro = CBoardDBBean.getInstance();
		
		CBoardDataBean article=null;
		String topImg = "";
		String bottomImg = "";
		String outImg = "";
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		try {
			article = dbPro.getArticle(num);
			topImg =  dbPro.getProductImg(article.getProduct_name1());
			bottomImg =  dbPro.getProductImg(article.getProduct_name2());
			outImg =  dbPro.getProductImg(article.getProduct_name3());
		} catch (Exception e) {e.printStackTrace();}
	
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("memId", id);

		request.setAttribute("topImg", topImg);
		request.setAttribute("bottomImg", bottomImg);
		request.setAttribute("outImg", outImg);
		return "/cboard/content.jsp";
	}
}
//작성자: 안다인
