package total;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SuperAction;


public class tMainAction implements SuperAction {
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		return "/pagemain/totalMain.jsp";
	}

}
