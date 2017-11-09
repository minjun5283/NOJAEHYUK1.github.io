package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.*;
import controller.SuperAction;

public class InputProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		MemberDTO member= new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		
		member.setAddress(request.getParameter("address"));
		
			MemberDAO manager = MemberDAO.getInstance();
			try {
				manager.insertMember(member);
			}catch(Exception e){
				e.printStackTrace();
			}
		return "/member/inputPro.jsp";
	}
	
}


/* 작성자 : 조문영  /수정날짜:20171018 */