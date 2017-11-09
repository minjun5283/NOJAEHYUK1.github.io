package board.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.SuperAction;

public class ContentAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article=null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		try {
			article = dbPro.getArticle(num);
		} catch (Exception e) {e.printStackTrace();}
		Boolean check  = false;
		
		if (id.equals("admin"))
			check = true;
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("memId", id);
		request.setAttribute("check", check);
		return "/board/content.jsp";
	}
}
//작성자: 안다인
