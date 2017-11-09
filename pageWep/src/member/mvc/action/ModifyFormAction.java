package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.bean.*;
import controller.SuperAction;

public class ModifyFormAction implements SuperAction {
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		    MemberDAO manager = MemberDAO.getInstance();
		    try {
				MemberDTO c = manager.getMember(id);
				request.setAttribute("c", c);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		return "/member/modifyForm.jsp";
		}
}


/* 작성자 : 조문영  /수정날짜:20171018 */