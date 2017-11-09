package cboard;
import java.sql.*;
import javax.sql.*;

import product.ProductDataBean;

import javax.naming.*;
import java.util.*; 

public class CBoardDBBean {

	private static CBoardDBBean instance = new CBoardDBBean();
	
	public static CBoardDBBean getInstance() {
		return instance;
	
	}
	private CBoardDBBean() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	public List getProductList(String product_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List productList =null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from product where product_type = ? and product_check='yes'"); 
			pstmt.setString(1, product_type);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				productList = new ArrayList(); 
				do{ 
					ProductDataBean product = new ProductDataBean();
					product.setProduct_num(rs.getInt("product_num"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_type(rs.getString("product_type"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_date(rs.getTimestamp("product_date"));
					product.setProduct_total(rs.getInt("product_total"));
					product.setProduct_img(rs.getString("product_img"));
					product.setProduct_check(rs.getString("product_check"));
					productList.add(product); 
				}while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return productList; 
	}
	public ProductDataBean getProduct(String product_name) throws Exception { ////
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductDataBean product =null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from product where product_name = ?"); 
			pstmt.setString(1, product_name);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
					product.setProduct_num(rs.getInt("product_num"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_type(rs.getString("product_type"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_date(rs.getTimestamp("product_date"));
					product.setProduct_total(rs.getInt("product_total"));
					product.setProduct_img(rs.getString("product_img"));
					product.setProduct_check(rs.getString("product_check"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return product; 
	}
	
	public String getProductImg(String product_name) throws Exception { //product 이미지
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_img = "";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select product_img from product where product_name=?"); 
			pstmt.setString(1, product_name);
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
	
	
	
	public void insertArticle(CBoardDataBean article) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cboard_num=article.getCboard_num();
		int cboard_ref=article.getCboard_ref();
		int cboard_restep=article.getCboard_restep();
		int cboard_relevel=article.getCboard_relevel();
		int number=0;
		String sql="";
		try {
			conn = getConnection(); 
			pstmt = conn.prepareStatement("select max(cboard_num) from cboard"); //최대값 찾기.
			rs = pstmt.executeQuery();
			if (rs.next()) 
				number=rs.getInt(1)+1;	 //최대값 + 1
			else
				number=1; 
			if (cboard_num!=0) 
			{ 
				sql="update cboard set cboard_restep=cboard_restep+1 where cboard_ref= ? and cboard_restep> ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cboard_ref);
				pstmt.setInt(2, cboard_restep);
				pstmt.executeUpdate();
				cboard_restep=cboard_restep+1;
				cboard_relevel=cboard_relevel+1;
			}else{ 
				cboard_ref=number;
				cboard_restep=0;
				cboard_relevel=0;
			} 
 
			sql = "insert into cboard(cboard_num, member_id ,cboard_subject ,cboard_pw ,product_name1,product_name2,cboard_reg_date ,cboard_contents,";
			sql+="cboard_ref ,cboard_restep ,cboard_relevel,cboard_readcount,product_name3) values(cboard_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,0,?)";  
				pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getMember_id());
			pstmt.setString(2, article.getCboard_subject());
			pstmt.setString(3, article.getCboard_pw());
			pstmt.setString(4, article.getProduct_name1());
			pstmt.setString(5, article.getProduct_name2());
			pstmt.setTimestamp(6, article.getCboard_reg_date());
			pstmt.setString(7, article.getCboard_contents());
			pstmt.setInt(8, cboard_ref);
			pstmt.setInt(9, cboard_restep);
			pstmt.setInt(10, cboard_relevel);
			pstmt.setString(11, article.getProduct_name3());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	public int getArticleCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from cboard"); // 레코드의 수
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; 
	}
	
	public int getArticleCount(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from cboard where  member_id =?"); // 레코드의 수
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; 
	}

	public List getArticles(int start, int end) throws Exception { //수정
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList=null;
		try { // select (select (select) ) 
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select cboard_num,member_id,cboard_subject,cboard_pw,product_name1,product_name2,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount,product_name3,r "+
					"from (select cboard_num,member_id,cboard_subject,cboard_pw,product_name1,product_name2,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount,product_name3,rownum r " +
					"from (select cboard_num,member_id,cboard_subject,cboard_pw,product_name1,product_name2,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount,product_name3 " +
					"from cboard order by cboard_ref desc, cboard_restep asc) order by cboard_ref desc, cboard_restep asc ) where r >= ? and r <= ? ");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							CBoardDataBean article= new CBoardDataBean();
							article.setCboard_num(rs.getInt("cboard_num"));
							article.setMember_id(rs.getString("member_id"));
							article.setCboard_subject(rs.getString("cboard_subject"));
							article.setCboard_pw(rs.getString("cboard_pw"));
							article.setProduct_name1(rs.getString("product_name1"));
							article.setProduct_name2(rs.getString("product_name2"));
							article.setCboard_pw(rs.getString("cboard_pw"));
							article.setCboard_reg_date(rs.getTimestamp("cboard_reg_date"));
							article.setCboard_readcount(rs.getInt("cboard_readcount"));
							article.setCboard_ref(rs.getInt("cboard_ref"));
							article.setCboard_restep(rs.getInt("cboard_restep"));
							article.setCboard_relevel(rs.getInt("cboard_relevel"));
							article.setCboard_contents(rs.getString("cboard_contents"));
							article.setProduct_name3(rs.getString("product_name3"));
							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return articleList;
	}
	public List getArticles(int start, int end , String ch, String search) throws Exception {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List articleList=null;
	      try { // select (select (select) ) 
	         conn = getConnection();
	         pstmt = conn.prepareStatement(
	               "select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount,r "+
	                     "from (select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount,r "+
	                     "from (select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount,rownum r " +
	                     "from (select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount " +
	                     "from cboard order by cboard_ref desc, cboard_restep asc) order by cboard_ref desc, cboard_restep asc ) where r >= ? and r <= ?)  where  "+ch+"  like '%"+search+"%' order by cboard_reg_date");
	               pstmt.setInt(1, start); 
	               pstmt.setInt(2, end); 
	               rs = pstmt.executeQuery();
	               if (rs.next()) {
	                  articleList = new ArrayList(end); 
	                  do{ 
	                     CBoardDataBean article= new CBoardDataBean();
	                     article.setCboard_num(rs.getInt("cboard_num"));
	                     article.setMember_id(rs.getString("member_id"));
	                     article.setCboard_subject(rs.getString("cboard_subject"));
	                     article.setCboard_pw(rs.getString("cboard_pw"));
	                     article.setCboard_reg_date(rs.getTimestamp("cboard_reg_date"));
	                     article.setCboard_readcount(rs.getInt("cboard_readcount"));
	                     article.setCboard_ref(rs.getInt("cboard_ref"));
	                     article.setCboard_restep(rs.getInt("cboard_restep"));
	                     article.setCboard_relevel(rs.getInt("cboard_relevel"));
	                     article.setCboard_contents(rs.getString("cboard_contents"));
	                     articleList.add(article); 
	                  }while(rs.next());
	               }
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	         if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	      }
	      return articleList;
	   }
	public List getArticles(int start, int end , String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList=null;
		try { // select (select (select) ) 
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from("+
							"select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount, r "+
							"from (select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount, rownum r " +
							"from (select cboard_num,member_id,cboard_subject,cboard_pw,cboard_reg_date,cboard_ref,cboard_restep,cboard_relevel,cboard_contents,cboard_readcount " +
							"from cboard order by cboard_ref desc, cboard_restep asc) order by cboard_ref desc, cboard_restep asc ) where r >= ? and r <= ? )  where  member_id = ? order by cboard_reg_date");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					pstmt.setString(3, id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							CBoardDataBean article= new CBoardDataBean();
							article.setCboard_num(rs.getInt("cboard_num"));
							article.setMember_id(rs.getString("member_id"));
							article.setCboard_subject(rs.getString("cboard_subject"));
							article.setCboard_pw(rs.getString("cboard_pw"));
							article.setCboard_reg_date(rs.getTimestamp("cboard_reg_date"));
							article.setCboard_readcount(rs.getInt("cboard_readcount"));
							article.setCboard_ref(rs.getInt("cboard_ref"));
							article.setCboard_restep(rs.getInt("cboard_restep"));
							article.setCboard_relevel(rs.getInt("cboard_relevel"));
							article.setCboard_contents(rs.getString("cboard_contents"));
							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		
		return articleList;
	}	
	public CBoardDataBean getArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CBoardDataBean article=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"update cboard set cboard_readcount=cboard_readcount+1 where cboard_num = ?"); 
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
			"select * from cboard where cboard_num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article= new CBoardDataBean();
				article.setCboard_num(rs.getInt("cboard_num"));
				article.setMember_id(rs.getString("member_id"));
				article.setCboard_subject(rs.getString("cboard_subject"));
				article.setCboard_pw(rs.getString("cboard_pw"));
				article.setCboard_reg_date(rs.getTimestamp("cboard_reg_date"));
				article.setCboard_readcount(rs.getInt("cboard_readcount"));
				article.setCboard_ref(rs.getInt("cboard_ref"));
				article.setCboard_restep(rs.getInt("cboard_restep"));
				article.setCboard_relevel(rs.getInt("cboard_relevel"));
				article.setCboard_contents(rs.getString("cboard_contents"));
				article.setProduct_name1(rs.getString("product_name1"));
				article.setProduct_name2(rs.getString("product_name2"));
				article.setProduct_name3(rs.getString("product_name3"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return article;
	}
	
	
	
	public CBoardDataBean updateGetArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CBoardDataBean article=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select * from cboard where cboard_num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article= new CBoardDataBean();
				article.setCboard_num(rs.getInt("cboard_num"));
				article.setMember_id(rs.getString("member_id"));
				article.setCboard_subject(rs.getString("cboard_subject"));
				article.setCboard_pw(rs.getString("cboard_pw"));
				article.setCboard_reg_date(rs.getTimestamp("cboard_reg_date"));
				article.setCboard_readcount(rs.getInt("cboard_readcount"));
				article.setCboard_ref(rs.getInt("cboard_ref"));
				article.setCboard_restep(rs.getInt("cboard_restep"));
				article.setCboard_relevel(rs.getInt("cboard_relevel"));
				article.setCboard_contents(rs.getString("cboard_contents"));
				article.setProduct_name1(rs.getString("product_name1"));
				article.setProduct_name2(rs.getString("product_name2"));
				article.setProduct_name3(rs.getString("product_name3"));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return article;
	}
	

	public int updateArticle(CBoardDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		String sql="";
		int x=-1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select cboard_pw from cboard where cboard_num = ?");
			pstmt.setInt(1, article.getCboard_num());
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd= rs.getString("cboard_pw"); 
				if(dbpasswd.equals(article.getCboard_pw())){
					sql="update cboard set member_id=?,cboard_subject=?,cboard_pw=?";
					sql+=",cboard_contents=? ,product_name1 =?,product_name2=?,product_name3=?where cboard_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getMember_id());
					pstmt.setString(2, article.getCboard_subject());
					pstmt.setString(3, article.getCboard_pw());
					pstmt.setString(4, article.getCboard_contents());
	                pstmt.setString(5, article.getProduct_name1());
	                pstmt.setString(6, article.getProduct_name2());
	                pstmt.setString(7, article.getProduct_name3());
	                pstmt.setInt(8, article.getCboard_num());
					pstmt.executeUpdate();
					x= 1;
				} else {
					x= 0;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	

	public int deleteArticle(int num, String passwd) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		int x=-1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select cboard_pw from cboard where cboard_num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd= rs.getString("cboard_pw");
				if(dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement("delete from cboard where cboard_num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					x= 1; 
				}else
					x= 0; 
			}
		} catch(Exception ex) {ex.printStackTrace();} 
		finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	public int getSearchCount(String ch , String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			String sql = " where " + ch + " like '%"+search+"%'";
			pstmt = conn.prepareStatement("select count(*) from cboard"+sql); // 레코드의 수
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1); 
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; 
	}
	public List getSearchArticles(String ch, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList=null;
		try { // select (select (select) ) 
			conn = getConnection();
			
			String sql = " where " + ch + " like '%"+search+"%' order by cboard_reg_date";
			pstmt = conn.prepareStatement("select * from board"+sql); // 레코드의 수
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(); 
						do{ 
							CBoardDataBean article= new CBoardDataBean();
							article.setCboard_num(rs.getInt("num"));
							article.setMember_id(rs.getString("member_id"));
							article.setCboard_subject(rs.getString("cboard_subject"));
							article.setCboard_pw(rs.getString("cboard_pw"));
							article.setCboard_reg_date(rs.getTimestamp("cboard_reg_date"));
							article.setCboard_readcount(rs.getInt("cboard_readcount"));
							article.setCboard_ref(rs.getInt("cboard_ref"));
							article.setCboard_restep(rs.getInt("cboard_restep"));
							article.setCboard_relevel(rs.getInt("cboard_relevel"));
							article.setCboard_contents(rs.getString("cboard_contents"));
							articleList.add(article); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return articleList;
	}
}

//작성자: 안다인 boardDAO
/*
*/
