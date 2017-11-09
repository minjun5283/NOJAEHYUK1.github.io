package board.mvc.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDBBean;
import controller.SuperAction;

public class SearchListAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		String search = request.getParameter("search");
		String ch = request.getParameter("ch");
		
		if(pageNum == null) 
			pageNum = "1";
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1)*pageSize +1; // 한페이지의 시작글번호
		int endRow = currentPage * pageSize; // 한페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		List articleList = null;
		try {
			BoardDBBean dbPro = BoardDBBean.getInstance();
			count = dbPro.getSearchCount(ch,search); // 전체글의수
			
			if (count > 0) {
				articleList = dbPro.getArticles(startRow,endRow,ch, search); //현재페이지에 해당하는 글
			} else {
				articleList = Collections.EMPTY_LIST;
			}
		}catch(Exception e) {e.printStackTrace();}
		
		number = count-(currentPage-1)*pageSize; //글목록에 표시할 글번호
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
        request.setAttribute("memId", id);
        request.setAttribute("ch", ch);
        request.setAttribute("search", search);
		return "/board/searchlist.jsp";
	}
}
//작성자: 안다인