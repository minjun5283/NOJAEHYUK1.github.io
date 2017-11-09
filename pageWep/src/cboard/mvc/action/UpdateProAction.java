package cboard.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cboard.CBoardDBBean;
import cboard.CBoardDataBean;
import controller.SuperAction;

public class UpdateProAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		
		CBoardDataBean article = new CBoardDataBean();
		article.setCboard_num(Integer.parseInt(request.getParameter("num")));
		article.setMember_id(request.getParameter("writer"));
		article.setCboard_subject(request.getParameter("subject"));
		article.setCboard_contents(request.getParameter("content"));
		article.setCboard_pw(request.getParameter("passwd"));
		article.setProduct_name1(request.getParameter("top"));
		article.setProduct_name2(request.getParameter("bottom"));
		article.setProduct_name3(request.getParameter("out"));
		
		CBoardDBBean dbPro = CBoardDBBean.getInstance();
		int check = 0;
		try {
			check = dbPro.updateArticle(article);
		} catch (Exception e) {e.printStackTrace();}
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/cboard/updatePro.jsp";
	}
}
//작성자: 안다인