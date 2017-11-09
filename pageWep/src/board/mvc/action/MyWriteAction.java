package board.mvc.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDBBean;
import controller.SuperAction;

public class MyWriteAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		String pageNum = request.getParameter("pageNum");
		
		int pageSize = 10;
	    if (pageNum == null) {
	        pageNum = "1";
	    }

	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1; //(1-1)*10+1 = 1
	    int endRow = currentPage * pageSize; // 1*10 = 10;
	    int count = 0;
	    int number=0;

	    HttpSession session = request.getSession();
	    String id = (String)session.getAttribute("memId");
	    
	    List articleList = null;
	    BoardDBBean dbPro = BoardDBBean.getInstance();
	    try {
			count = dbPro.getArticleCount(id);
			if (count > 0) 
		        articleList = dbPro.getArticles(startRow, endRow, id);
		        
		} catch (Exception e) {e.printStackTrace();}

		number=count-(currentPage-1)*pageSize; 
		request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
        request.setAttribute("memId", id);
		return "/board/myWrite.jsp";
	}
}
//작성자: 안다인