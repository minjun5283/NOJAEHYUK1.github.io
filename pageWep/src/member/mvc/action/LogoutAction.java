package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.SuperAction;

public class LogoutAction implements SuperAction{ 
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		HttpSession session= request.getSession();
		session.invalidate();
		return "/member/logout.jsp";
	}
}

/* 작성자 : 조문영  /수정날짜:20171018 */