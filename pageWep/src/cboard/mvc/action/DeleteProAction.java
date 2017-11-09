package cboard.mvc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cboard.CBoardDBBean;
import controller.SuperAction;

public class DeleteProAction implements SuperAction{
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException  {
		request.setCharacterEncoding("UTF-8");

        int num = Integer.parseInt(request.getParameter("num"));
        String pageNum = request.getParameter("pageNum");
        String passwd = request.getParameter("passwd");
	    
        CBoardDBBean dbPro = CBoardDBBean.getInstance();
        int check = 0;
        
        try {
			check = dbPro.deleteArticle(num, passwd);
		} catch (Exception e) {e.printStackTrace();}

        //해당 뷰에서 사용할 속성
        request.setAttribute("pageNum", new Integer(pageNum));
        request.setAttribute("check", new Integer(check));
		return "/cboard/deletePro.jsp";
	}
}
//작성자: 안다인