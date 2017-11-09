package member.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.naming.resources.ResourceAttributes;

import member.bean.*;

import controller.SuperAction;

public class SearchListAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response)
			throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String search = request.getParameter("search");
		String ch = request.getParameter("ch");
		MemberDAO manager = MemberDAO.getInstance();
		try {
			MemberDTO c = manager.getMember(search);
			request.setAttribute("c", c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/member/searchlist.jsp";
	}
}
