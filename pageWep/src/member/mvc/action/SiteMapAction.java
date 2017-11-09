package member.mvc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.SuperAction;

public class SiteMapAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		HttpSession session =request.getSession();
		String id = (String)session.getAttribute("memId");
		request.setAttribute("memId", id);
		return "/sitemap.jsp";
	}
}
