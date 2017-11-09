package product.mvc.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.SuperAction;
import product.ProductDataBean;
import product.productDBBean;

public class ProwriterProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
	    MultipartRequest mr = null;
	    String sysName = null;
		//String path = request.getRealPath("upload2"); //바로 서버 등록시 사용 
		//String path = "D:\\829~\\am\\WebContent\\upload\\"; // \하나일때 특수문자로 인식 
	    String path = "C:\\Users\\qudtj\\Desktop\\새 폴더\\pageWep (1)\\pageWep\\WebContent\\upload2\\";
		//자기 pageWep에 있는 업로드 폴더로 설정
		System.out.println(path);
		int max = 1024*1024*10; //10MB
		String enc = "UTF-8";  //업로드 파일 인코딩
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();//덮어씌우기 방지
		try{
		mr = new MultipartRequest(request,path,max,enc,dp);
		//MultipartRequest안에 request의 기능이 있다. 
		
		Enumeration files = mr.getFileNames();
		
		while(files.hasMoreElements())
		{
		String name = (String)files.nextElement();
		
	    sysName = mr.getFilesystemName(name);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    ProductDataBean arti = new ProductDataBean();
	    String product_type = mr.getParameter("product_type");
        String product_size[] = mr.getParameterValues("product_size");
        StringBuffer sizes = new StringBuffer();
        for(String s : product_size){
        	sizes.append(s).append(",");
        }
        sizes.deleteCharAt(sizes.length()-1);//마지막 글자 제거 


    	arti.setProduct_num(Integer.parseInt(mr.getParameter("product_num")));
    	arti.setProduct_name(mr.getParameter("product_name"));
    	arti.setProduct_price(Integer.parseInt(mr.getParameter("product_price")));
    	if(product_type.equals("TOP")){
    	arti.setProduct_type(product_type);
    	}else if(product_type.equals("BOTTOM")){
    	arti.setProduct_type(product_type);	
    	}else{
    	arti.setProduct_type(product_type);		
    	}
    	arti.setProduct_size(sizes.toString());
    	arti.setProduct_color(mr.getParameter("product_color"));
    	arti.setProduct_date(new Timestamp(System.currentTimeMillis()));
    	arti.setProduct_total(Integer.parseInt(mr.getParameter("product_total")));
    	arti.setProduct_check(mr.getParameter("product_check"));
    	arti.setProduct_img("..\\upload2\\"+sysName);
	
    	productDBBean dpPro = productDBBean.getInstance();
    	try {
			dpPro.insertPro(arti);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	    return "/product/prowriterPro.jsp";
	 }

}

/* 작성자 : 노재혁  /수정날짜:20171025   */ 
