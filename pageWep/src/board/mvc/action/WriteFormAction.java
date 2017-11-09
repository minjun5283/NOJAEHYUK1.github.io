package board.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;

public class WriteFormAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		int num=0,ref=1,restep=0,relevel=0;  
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		try {
			if(request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				restep = Integer.parseInt(request.getParameter("restep"));
				relevel = Integer.parseInt(request.getParameter("relevel"));
			}
		}catch(Exception e) {e.getStackTrace();}
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("restep", new Integer(restep));
		request.setAttribute("relevel", new Integer(relevel));
		request.setAttribute("memId", id);
		return "/board/writeForm.jsp";
	}	
}
//작성자: 안다인