package cboard.mvc.action;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cboard.CBoardDBBean;
import cboard.CBoardDataBean;
import controller.SuperAction;
import product.ProductDataBean;

public class ListAction implements SuperAction{
	
	public String action(HttpServletRequest request ,HttpServletResponse response) throws ServletException,java.io.IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum"); //페이지 번호
		
		if(pageNum == null) 
			pageNum = "1";
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1)*pageSize +1; // 한페이지의 시작글번호
		int endRow = currentPage * pageSize; // 한페이지의 마지막 글번호
		int count = 0;
		int number = 0;
		int i = 0;
		List articleList = null;
		List<String>topImg = new ArrayList<String>();
		List<String>bottomImg = new ArrayList<String>();
		List<String>outImg = new ArrayList<String>();
		
		try {
			CBoardDBBean dbPro = CBoardDBBean.getInstance();
			count = dbPro.getArticleCount(); // 전체글의수
			
			if (count > 0) {
				articleList = dbPro.getArticles(startRow, endRow); //현재페이지에 해당하는 글
			} else {
				articleList = Collections.EMPTY_LIST;
			}
			
		for(i = 0 ; i<articleList.size();i++) {
			 CBoardDataBean article = (CBoardDataBean)articleList.get(i);
			 String img = dbPro.getProductImg(article.getProduct_name1());
			 topImg.add(img);
			 String img1 = dbPro.getProductImg(article.getProduct_name2());
			 bottomImg.add(img1);
			 String img2 = dbPro.getProductImg(article.getProduct_name3());
			 outImg.add(img2);
		     request.setAttribute("topImg", topImg);
		     request.setAttribute("bottomImg", bottomImg);
		     request.setAttribute("outImg", outImg);
		}
		System.out.println(articleList.size());
		System.out.println(outImg.get(0));
		
			
		}catch(Exception e) {e.printStackTrace();}
		
		number = count-(currentPage-1)*pageSize; //글목록에 표시할 글번호
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
        request.setAttribute("startRow", new Integer(startRow));
        request.setAttribute("endRow", new Integer(endRow));
        request.setAttribute("count", new Integer(count));
        request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
        request.setAttribute("articleList", articleList);
        request.setAttribute("memId", id);

        
		return "/cboard/list.jsp";
	}
}
//작성자: 안다인