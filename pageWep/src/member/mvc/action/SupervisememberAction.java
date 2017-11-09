package member.mvc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;
import member.bean.MemberDAO;

public class SupervisememberAction implements SuperAction {
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
	    List allList = null;
	    MemberDAO dbPro = MemberDAO.getInstance();// dbPro에 모든 값을 넣는다.
	    try {
			allList = dbPro.getAllMember();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    request.setAttribute("allList", allList);
	    
		return "/member/supervisemember.jsp";
	}
}