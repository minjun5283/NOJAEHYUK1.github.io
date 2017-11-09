package board.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;

public class DeleteFormAction implements SuperAction {
	public String action(HttpServletRequest request ,HttpServletResponse resonse) throws ServletException,java.io.IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		
		return "/board/deleteForm.jsp";
	}
}
//작성자: 안다인