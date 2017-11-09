package cboard.mvc.action;

import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cboard.CBoardDBBean;
import cboard.CBoardDataBean;
import controller.SuperAction;

public class WriteProAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");//한글 인코딩
		
		CBoardDataBean article = new CBoardDataBean();
		article.setCboard_num(Integer.parseInt(request.getParameter("num")));
		article.setMember_id(request.getParameter("writer"));
		article.setCboard_subject(request.getParameter("subject"));
		article.setCboard_pw(request.getParameter("passwd"));
		article.setCboard_reg_date(new Timestamp(System.currentTimeMillis()));
		article.setCboard_ref(Integer.parseInt(request.getParameter("ref")));
		article.setCboard_restep(Integer.parseInt(request.getParameter("restep")));
		article.setCboard_relevel(Integer.parseInt(request.getParameter("relevel")));
		article.setCboard_contents(request.getParameter("content"));
		article.setProduct_name1(request.getParameter("top"));
		article.setProduct_name2(request.getParameter("bottom"));
		article.setProduct_name3(request.getParameter("out"));
		
		
		CBoardDBBean dbPro = CBoardDBBean.getInstance();
		
		try {
			dbPro.insertArticle(article);
		} catch (Exception e) {e.printStackTrace();}
		
		return  "/cboard/writePro.jsp";
	}
}
//작성자: 안다인