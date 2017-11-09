package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.bean.MemberDAO;
import controller.SuperAction;

public class LoginProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO manager = MemberDAO.getInstance();
		int check=0;
		try {
			check= manager.userCheck(id, pw);
			if(check==1) {
				HttpSession session=request.getSession();
				session.setAttribute("memId",id);}
		}catch(Exception e) {e.printStackTrace();}
		request.setAttribute("check", new Integer(check));
		return "/member/loginPro.jsp";
		//return "/product/main.jsp";
	}

}

/* 작성자 : 조문영  /수정날짜:20171018 */