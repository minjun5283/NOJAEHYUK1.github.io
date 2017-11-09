//���̵� �ߺ�Ȯ�� 

package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDAO;

import controller.SuperAction;

public class ConfirmIdAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		String id = request.getParameter("id");
		MemberDAO manager =MemberDAO.getInstance();
		try {
			int check= manager.confirmId(id);
			request.setAttribute("check", new Integer(check));
			request.setAttribute("id", id);
		}catch(Exception e) {e.printStackTrace();}
		
		return "/member/confirmId.jsp";
	}

}



/* �ۼ��� : ������  /������¥:20171018 */