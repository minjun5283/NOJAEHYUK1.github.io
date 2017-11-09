// 데이터 작업  (MVC 패턴 적용을 위한)
//초안 :김민준  2017 - 10 - 17
//수정 :

package product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;
import javax.naming.*;

public class productDBBean {
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		                                            //하나의 매서드만 이용할수 있을때 싱글 인스턴스 이용 
		private static productDBBean dao = new productDBBean(); //싱글 Instance 구조로 객체 선언해서 사용 - new 사용하지 않게 (매서드만 사용할수 있는 형태)
		public static productDBBean getInstance() {       //DAO - 객체를 재사용   ,, DTO - 재사용X
			return dao;
		}
		private productDBBean() {}
		
		
		private Connection getConnection() throws Exception{ //1,2단계는 외부에서 사용할 X
			 Context ctx = new InitialContext(); //context.xml 관리 - 추가 resource
			 Context env = (Context)ctx.lookup("java:comp/env"); //java로 사용된것만 검색 //comp/env 이름의 객체를 Context 형태로  
			 DataSource ds = (DataSource)env.lookup("jdbc/orcl");
			 Connection conn = ds.getConnection();
			 return conn;
			
		}
		
		public int getCount() throws Exception { //product 테이블 count 값 
			int x=0;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from product");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
			
			return x;
			
		}
		
		public List getList(int start, int end) throws Exception{  // list(전체 값에 대한)
			List proList=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_num,product_name,product_price,product_type,product_size,product_color,product_date,product_total,product_img,product_check,r"+
				" from (select product_num,product_name,product_price,product_type,product_size,product_color,product_date,product_total,product_img,product_check,rownum r from product order by product_date desc) where r >= ? and r <= ? order by product_date desc");
				pstmt.setInt(1,start);
				pstmt.setInt(2,end);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					proList = new ArrayList(end);
				do {
					ProductDataBean arti = new ProductDataBean();
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setProduct_name(rs.getString("product_name"));
					arti.setProduct_price(rs.getInt("product_price"));
					arti.setProduct_type(rs.getString("product_type"));
					arti.setProduct_size(rs.getString("product_size"));
					arti.setProduct_color(rs.getString("product_color"));
					arti.setProduct_date(rs.getTimestamp("product_date"));
					arti.setProduct_total(rs.getInt("product_total"));
					arti.setProduct_img(rs.getString("product_img"));
					arti.setProduct_check(rs.getString("product_check"));
					proList.add(arti);					
				}while(rs.next());
				}
				
			}catch(Exception e) {
				e.printStackTrace();			
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}			
			}
			return proList;
			
		}
		
		public void insertPro(ProductDataBean arti) throws Exception{ // prowriterForm 내용을 삽입
			int product_num = arti.getProduct_num();
			int number=0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select max(product_num) from product");
				rs = pstmt.executeQuery();
				if(rs.next())
					number = rs.getInt(1)+1;
				else
					number = 1;
				
				pstmt= conn.prepareStatement("insert into product values(product_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, arti.getProduct_name());
				pstmt.setInt(2, arti.getProduct_price());
				pstmt.setString(3, arti.getProduct_type());
				pstmt.setString(4, arti.getProduct_size());
				pstmt.setString(5, arti.getProduct_color());
				pstmt.setTimestamp(6, arti.getProduct_date());
				pstmt.setInt(7, arti.getProduct_total());
				pstmt.setString(8, arti.getProduct_img());
				pstmt.setString(9, arti.getProduct_check());
				pstmt.executeUpdate();	
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs != null) try { rs.close(); } catch(SQLException e) {}
				if(conn != null)try {conn.close();}catch(SQLException e) {}
				if(pstmt != null)try {pstmt.close();}catch(SQLException e) {}
			}
		}
		
		public int getCountCheck() throws Exception { //product_check='yes' 조건의 count 값
			int x=0;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from product where product_check='yes'");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
			
			return x;
			
		}
		
		public List getListCheck(int start, int end) throws Exception{ //product_check='yes' 조건의 값의 리스트 
			List proList1=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_num,product_name,product_price,product_img,r"+
				" from (select product_num,product_name,product_price,product_type,product_size,product_color,product_date,product_total,product_img,product_check,rownum r from product) where r >= ? and r <= ? and product_check='yes'");
				pstmt.setInt(1,start);
				pstmt.setInt(2,end);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					proList1 = new ArrayList();
				do {
					ProductDataBean arti = new ProductDataBean();
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setProduct_name(rs.getString("product_name"));
					arti.setProduct_price(rs.getInt("product_price"));
					arti.setProduct_img(rs.getString("product_img"));
					proList1.add(arti);					
				}while(rs.next());
				}
				
			}catch(Exception e) {
				e.printStackTrace();			
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}			
			}
			return proList1;
			
		}
		
		
		public ProductDataBean getdata(int product_num) throws Exception{ // 현재 등록된 데이터를 불러오기 위한 
			ProductDataBean arti = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					arti = new ProductDataBean();
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setProduct_name(rs.getString("product_name"));
					arti.setProduct_price(rs.getInt("product_price"));
					arti.setProduct_type(rs.getString("product_type"));
					arti.setProduct_size(rs.getString("product_size"));
					arti.setProduct_color(rs.getString("product_color"));
					arti.setProduct_date(rs.getTimestamp("product_date"));
					arti.setProduct_total(rs.getInt("product_total"));
					arti.setProduct_img(rs.getString("product_img"));
					arti.setProduct_check(rs.getString("product_check"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();			
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
				
			}
			return arti;
		}
		
		public void updateData(ProductDataBean arti) throws Exception{ //변경 - update 위한 (이미지제외)
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from product where product_num=?");
				pstmt.setInt(1, arti.getProduct_num());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				pstmt = conn.prepareStatement("update product set product_name=?,product_price=?,product_type=?,product_size=?,product_color=?,product_total=?,product_check=? where product_num=? ");
	            pstmt.setString(1, arti.getProduct_name());
	            pstmt.setInt(2, arti.getProduct_price());
	            pstmt.setString(3, arti.getProduct_type());
	            pstmt.setString(4, arti.getProduct_size());
	            pstmt.setString(5, arti.getProduct_color());
	            pstmt.setInt(6, arti.getProduct_total());
	            pstmt.setString(7, arti.getProduct_check());
	            pstmt.setInt(8, arti.getProduct_num());
	            pstmt.executeUpdate();			
				}	
			}catch(Exception e) {
				e.printStackTrace();			
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
		}
		public void updateImg(ProductDataBean arti) throws Exception{ // 특정 넘의 이미지를 등록폼
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from product where product_num=?");
				pstmt.setInt(1, arti.getProduct_num());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				pstmt = conn.prepareStatement("update product set product_img=? where product_num=? ");
	            pstmt.setString(1, arti.getProduct_img());
	            pstmt.setInt(2, arti.getProduct_num());
	            pstmt.executeUpdate();			
				}	
			}catch(Exception e) {
				e.printStackTrace();			
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
		}
		
		
		public void deleteData(int product_num) throws Exception{ // 특정 num 의 값을 제거 
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					pstmt = conn.prepareStatement("delete from product where product_num=?");
					pstmt.setInt(1, product_num);
					pstmt.executeUpdate();
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
		}
		
	    public String selectSys(int product_num) throws Exception{ // 특정 num의 이미지 주소 를 얻기 위한 
	    	String sysname = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_img from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
		          sysname = rs.getString("product_img");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
			return sysname;
	    }
	    
	    public int countNum(String getTex) throws Exception{ //특정 num 값에 대한 count
	    	int x=0;
	    	try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from product where product_num like?");
				pstmt.setString(1, "%"+getTex+"%"); 
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x = rs.getInt(1);
				}
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}finally {
	    		if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
	    	}
	    	return x;
	    }
	    
	    public int countName(String getTex) throws Exception{ // 특정 name에 대한 count
	    	int x=0;
	    	try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from product where product_name like?");
				pstmt.setString(1, "%"+getTex+"%"); 
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x = rs.getInt(1);
				}
	    		
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}finally {
	    		if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
	    	}
	    	return x;
	    }
	    
	    public int countType(String getTex) throws Exception{ // 특정 type에 대한 count
	    	int x=0;
	    	try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from product where product_type like?");
				pstmt.setString(1, "%"+getTex+"%"); 
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x = rs.getInt(1);
				}

	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}finally {
	    		if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
	    	}
	    	return x;
	    }
	    
		public List getListNum(String getTex) { //num에 대한 list
			List proList = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from product where product_num like?");
				pstmt.setString(1, "%"+getTex+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					proList = new ArrayList();
					do{ 
						ProductDataBean arti = new ProductDataBean();
						arti.setProduct_num(rs.getInt("product_num"));
						arti.setProduct_name(rs.getString("product_name"));
						arti.setProduct_price(rs.getInt("product_price"));
						arti.setProduct_type(rs.getString("product_type"));
						arti.setProduct_size(rs.getString("product_size"));
						arti.setProduct_color(rs.getString("product_color"));
						arti.setProduct_date(rs.getTimestamp("product_date"));
						arti.setProduct_total(rs.getInt("product_total"));
						arti.setProduct_img(rs.getString("product_img"));
						arti.setProduct_check(rs.getString("product_check"));
						proList.add(arti);	

					}while(rs.next());
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
			return proList;
	}
		
		public List getListName(String getTex) { //Name에 대한 list
			List proList = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from product where product_name like?");
				pstmt.setString(1, "%"+getTex+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					proList = new ArrayList();
					do{ 
						ProductDataBean arti = new ProductDataBean();
						arti.setProduct_num(rs.getInt("product_num"));
						arti.setProduct_name(rs.getString("product_name"));
						arti.setProduct_price(rs.getInt("product_price"));
						arti.setProduct_type(rs.getString("product_type"));
						arti.setProduct_size(rs.getString("product_size"));
						arti.setProduct_color(rs.getString("product_color"));
						arti.setProduct_date(rs.getTimestamp("product_date"));
						arti.setProduct_total(rs.getInt("product_total"));
						arti.setProduct_img(rs.getString("product_img"));
						arti.setProduct_check(rs.getString("product_check"));
						proList.add(arti);	

					}while(rs.next());
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
			return proList;
	}
		
		public List getListType(String getTex) { //Type에 대한 list
			List proList = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from product where product_type like?");
				pstmt.setString(1, "%"+getTex+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					proList = new ArrayList();
					do{ 
						ProductDataBean arti = new ProductDataBean();
						arti.setProduct_num(rs.getInt("product_num"));
						arti.setProduct_name(rs.getString("product_name"));
						arti.setProduct_price(rs.getInt("product_price"));
						arti.setProduct_type(rs.getString("product_type"));
						arti.setProduct_size(rs.getString("product_size"));
						arti.setProduct_color(rs.getString("product_color"));
						arti.setProduct_date(rs.getTimestamp("product_date"));
						arti.setProduct_total(rs.getInt("product_total"));
						arti.setProduct_img(rs.getString("product_img"));
						arti.setProduct_check(rs.getString("product_check"));
						proList.add(arti);	

					}while(rs.next());
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
			return proList;
	}
		
		public List getListMenu(int start, int end, String type) throws Exception{ //메인 메뉴에서의 조건값에 따른 list
			List proList1=null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_num,product_name,product_price,product_img,r"+
				" from (select product_num,product_name,product_price,product_type,product_size,product_color,product_date,product_total,product_img,product_check,rownum r from product) where r >= ? and r <= ? and product_check='yes' and product_type=?");
				pstmt.setInt(1,start);
				pstmt.setInt(2,end);
				pstmt.setString(3, type);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					proList1 = new ArrayList();
				do {
					ProductDataBean arti = new ProductDataBean();
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setProduct_name(rs.getString("product_name"));
					arti.setProduct_price(rs.getInt("product_price"));
					arti.setProduct_img(rs.getString("product_img"));
					proList1.add(arti);					
				}while(rs.next());
				}
				
			}catch(Exception e) {
				e.printStackTrace();			
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}			
			}
			return proList1;
			
		}
		
		public int getCountMenu(String type) throws Exception { //메뉴 파라미터의 대한 count
			int x=0;
			try{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count(*) from product where product_check='yes' and product_type=?");
				pstmt.setString(1, type);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getInt(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
			
			return x;
			
		}
		
		public String getSizes(int product_num) throws Exception{ //특정 num 값에 대한 product_size값 		
			String x="";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_size from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
			    if(rs.next()) {
			    	x=rs.getString(1);
			    }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
			return x;
		}
		
		public String getColors(int product_num) throws Exception{ //특정 num 값에 대한 product_color값 		
			String x="";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_color from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
			    if(rs.next()) {
			    	x=rs.getString(1);
			    }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
			return x;
		}
		
		
		
		
		public List getListContent(int product_num) {  // 조건 num 에 대한 테이블 값 
			List proList = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(
						"select * from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					proList = new ArrayList();
					do{ 
						ProductDataBean arti = new ProductDataBean();
						arti.setProduct_num(rs.getInt("product_num"));
						arti.setProduct_name(rs.getString("product_name"));
						arti.setProduct_price(rs.getInt("product_price"));
						arti.setProduct_size(rs.getString("product_size"));
						arti.setProduct_color(rs.getString("product_color"));
						arti.setProduct_img(rs.getString("product_img"));
						proList.add(arti);	
					}while(rs.next());
				
				}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}
			}
			return proList;
	}
		
		
		public int getTotal(int product_num) throws Exception{ //특정 num 값에 대한 product_total값 		
			int x= 0;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select product_total from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
			    if(rs.next()) {
			    	x=rs.getInt(1);
			    }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
			return x;
		}
		
		public void getTotalInsert(int x, int product_num) throws Exception{ 		
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("select * from product where product_num=?");
				pstmt.setInt(1, product_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					pstmt = conn.prepareStatement("update product set product_total=? where product_num=?");
					pstmt.setInt(1, x);
					pstmt.setInt(2, product_num);
					pstmt.executeUpdate();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException e) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
				if (conn != null) try { conn.close(); } catch(SQLException e) {}	
			}
		}
		   public String getProductImg(int product_num) throws Exception { //product 이미지
		         String product_img = "";
		         try {
		            conn = getConnection();
		            pstmt = conn.prepareStatement("select product_img from product where product_num=?"); 
		            pstmt.setInt(1, product_num);
		            pstmt.executeUpdate();
		            rs = pstmt.executeQuery();
		            if(rs.next()) {
		               product_img = rs.getString(1);
		            }
		         } catch(Exception ex) {
		            ex.printStackTrace();
		         } finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		         }
		         return product_img;
		      }
		   public boolean setCart(String member_id, int product_num , String product_color ,int order_count, String product_size ) throws Exception {
		         ProductDataBean arti = new ProductDataBean();
		         boolean check = false;
		         try{
		            conn = getConnection();
		            pstmt = conn.prepareStatement("select * from product where product_num = ?");
		            pstmt.setInt(1,product_num);
		            rs = pstmt.executeQuery();
		            if(rs.next()) {
		               arti.setProduct_num(rs.getInt("product_num"));
		               arti.setProduct_name(rs.getString("product_name"));
		               arti.setProduct_price(rs.getInt("product_price"));
		               arti.setProduct_type(rs.getString("product_type"));
		               arti.setProduct_size(rs.getString("product_size"));
		               arti.setProduct_color(rs.getString("product_color"));
		               arti.setProduct_date(rs.getTimestamp("product_date"));
		               arti.setProduct_total(rs.getInt("product_total"));
		               arti.setProduct_img(rs.getString("product_img"));
		               arti.setProduct_check(rs.getString("product_check"));
		            }
		            pstmt = conn.prepareStatement("select * from cart where product_num = ? and member_id = ?");
		            pstmt.setInt(1, product_num);
		            pstmt.setString(2, member_id);
		            rs = pstmt.executeQuery();
		            
		            if(rs.next()) { // cart insert 부분
		               return check;
		            }
		            pstmt = conn.prepareStatement("insert into cart values(?,?,?,?,?,?,?,?,?)");
		            pstmt.setString(1,member_id);
		            pstmt.setInt(2,product_num);
		            pstmt.setString(3,arti.getProduct_name());
		            pstmt.setString(4, arti.getProduct_img());
		            pstmt.setInt(5,arti.getProduct_price()*order_count);
		            pstmt.setString(6,arti.getProduct_type());
		            pstmt.setString(7,product_size);
		            pstmt.setString(8,product_color);
		            pstmt.setInt(9,order_count);
		            rs = pstmt.executeQuery();
		            check = true;
		            
		         }catch(Exception e){
		            e.printStackTrace();
		         }finally {
		            if (rs != null) try { rs.close(); } catch(SQLException e) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
		            if (conn != null) try { conn.close(); } catch(SQLException e) {}   
		         }
		         return check;
		      }
			  
}

