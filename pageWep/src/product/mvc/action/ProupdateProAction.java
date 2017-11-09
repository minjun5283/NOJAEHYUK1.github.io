package product.mvc.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.SuperAction;
import product.ProductDataBean;
import product.productDBBean;

public class ProupdateProAction implements SuperAction{
	public String action(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	    MultipartRequest mr = null;
	    String sysName = null;
		//String path = request.getRealPath("upload2"); //�ٷ� ���� ��Ͻ� ��� 
		//String path = "D:\\829~\\am\\WebContent\\upload\\"; // \�ϳ��϶� Ư�����ڷ� �ν� 
		String path = "C:\\Users\\qudtj\\Desktop\\�� ����\\pageWep (1)\\pageWep\\WebContent\\upload2\\";
		System.out.println(path);
		int max = 1024*1024*10; //10MB
		String enc = "UTF-8";  //���ε� ���� ���ڵ�
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();//������ ����
		try{
		mr = new MultipartRequest(request,path,max,enc,dp);
		//MultipartRequest�ȿ� request�� ����� �ִ�. 
		
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
		String pageNum = request.getParameter("pageNum");
        String product_type = mr.getParameter("product_type");
        String product_size[] = mr.getParameterValues("product_size");
        StringBuffer sizes = new StringBuffer();
        for(String s : product_size){
        	sizes.append(s).append(",");
        }
        sizes.deleteCharAt(sizes.length()-1);//������ ���� ���� 
        
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
    	arti.setProduct_total(Integer.parseInt(mr.getParameter("product_total")));
    	arti.setProduct_check(mr.getParameter("product_check"));
    	arti.setProduct_img("..\\upload2\\"+sysName);
    	arti.setProduct_num(Integer.parseInt(mr.getParameter("product_num")));
	    
    	productDBBean dpPro = productDBBean.getInstance();
    	try {
    		if(!(sysName ==null)){
			dpPro.updateImg(arti);
    		}
    		dpPro.updateData(arti);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	request.setAttribute("pageNum", pageNum);
	
	    return "/product/proupdatePro.jsp";
	 }

}


/* �ۼ��� : ������  /������¥:20171025   */ 
