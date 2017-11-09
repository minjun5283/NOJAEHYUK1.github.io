package board.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.SuperAction;

public class UpdateFormAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = null ;
		
		try {
			article = dbPro.updateGetArticle(num);
		} catch (Exception e) {e.printStackTrace();}
		
		request.setAttribute("pageNum", new Integer(pageNum) );
		request.setAttribute("article", article);
		return "/board/updateForm.jsp";
	}
}
//작성자: 안다인