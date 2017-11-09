package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.bean.*;
import controller.SuperAction;

public class DeleteProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		 
		HttpSession session =request.getSession();
		String id =(String) session.getAttribute("memId");
		String pw= request.getParameter("pw");
		int check=0;
		MemberDAO manager = MemberDAO.getInstance();
	    try {
			 check = manager.deleteMember(id, pw);
		if(check==1)
			session.invalidate();
			
		} catch (Exception e) {	e.printStackTrace();		}
	    request.setAttribute("check",new Integer(check));
		return "/member/deletePro.jsp";
		}
}
