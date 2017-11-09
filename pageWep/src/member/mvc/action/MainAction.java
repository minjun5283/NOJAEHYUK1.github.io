package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;

public class MainAction implements SuperAction {
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		return "/member/main.jsp";
	}
}


/* 작성자 : 조문영  /수정날짜:20171018 */