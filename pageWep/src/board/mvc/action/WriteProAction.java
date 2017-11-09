package board.mvc.action;

import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.SuperAction;

public class WriteProAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");//한글 인코딩
		BoardDataBean article = new BoardDataBean();
		article.setBoard_num(Integer.parseInt(request.getParameter("num")));
		article.setMember_id(request.getParameter("writer"));
		article.setBoard_subject(request.getParameter("subject"));
		article.setBoard_pw(request.getParameter("passwd"));
		article.setBoard_reg_date(new Timestamp(System.currentTimeMillis()));
		article.setBoard_ref(Integer.parseInt(request.getParameter("ref")));
		article.setBoard_restep(Integer.parseInt(request.getParameter("restep")));
		article.setBoard_relevel(Integer.parseInt(request.getParameter("relevel")));
		article.setBoard_contents(request.getParameter("content"));
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		try {
			dbPro.insertArticle(article);
		} catch (Exception e) {e.printStackTrace();}
		
		return  "/board/writePro.jsp";
	}
}
//작성자: 안다인