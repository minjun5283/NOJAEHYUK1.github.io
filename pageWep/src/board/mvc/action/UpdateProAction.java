package board.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.SuperAction;

public class UpdateProAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		
		BoardDataBean article = new BoardDataBean();
		article.setBoard_num(Integer.parseInt(request.getParameter("num")));
		article.setMember_id(request.getParameter("writer"));
		article.setBoard_subject(request.getParameter("subject"));
		article.setBoard_contents(request.getParameter("content"));
		article.setBoard_pw(request.getParameter("passwd"));
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = 0;
		try {
			check = dbPro.updateArticle(article);
		} catch (Exception e) {e.printStackTrace();}
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/board/updatePro.jsp";
	}
}
//작성자: 안다인