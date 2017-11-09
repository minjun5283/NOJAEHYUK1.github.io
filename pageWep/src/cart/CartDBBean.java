package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import product.ProductDataBean;

public class CartDBBean {
	private static CartDBBean instance = new CartDBBean();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;	
	
	public static CartDBBean getInstance() {
		return instance;
	}
	private CartDBBean() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	public CartDataBean getCart(String num , String member_id) throws Exception{
		CartDataBean dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from cart where member_id = ? and product_num = ?"); 
			pstmt.setString(1, member_id);
			pstmt.setString(2, num);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				do {
					dto = new CartDataBean();
					dto.setMember_id(rs.getString("member_id"));
					dto.setProduct_color(rs.getString("product_color"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setProduct_name(rs.getString("product_name"));
					dto.setProduct_num(rs.getInt("product_num"));
					dto.setProduct_price(rs.getInt("product_price"));
					dto.setProduct_size(rs.getString("product_size"));
					dto.setProduct_type(rs.getString("product_type"));
					dto.setAmount(rs.getInt("amount"));
				}while (rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return dto;
	}
	
	public List getCartList(String member_Id) throws Exception{
		List cartList= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from cart where member_id = ?"); 
			pstmt.setString(1, member_Id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cartList = new ArrayList(); 
				do{ 
					CartDataBean product = new CartDataBean();
					product.setMember_id(rs.getString("member_id"));
					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_img(rs.getString("product_img"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_num(rs.getInt("product_num"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_type(rs.getString("product_type"));
					product.setAmount(rs.getInt("amount"));
					cartList.add(product);
				}while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return cartList;
	}
	public void cartDelete(String member_Id , int product_num)throws Exception {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from cart where member_id = ? and product_num = ?"); 
			pstmt.setString(1, member_Id);
			pstmt.setInt(2, product_num);
			rs = pstmt.executeQuery();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	public int totalPrice(String member_Id)throws Exception{
		int total = 0;
		int  i =1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select product_price from cart where member_id = ? "); 
			pstmt.setString(1, member_Id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int price = rs.getInt("product_price");
				total += price;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return total;
	}
	
	public int countCart(String member_Id)throws Exception{
		int  x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from cart where member_id = ? "); 
			pstmt.setString(1, member_Id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
               x = rs.getInt(1);
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
	
	public List getCartListCheck(String member_Id, int product_num) throws Exception{
		List cartList= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from cart where member_id = ? and product_num = ?"); 
			pstmt.setString(1, member_Id);
			pstmt.setInt(2, product_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cartList = new ArrayList(); 
				do{ 
					CartDataBean product = new CartDataBean();
					product.setMember_id(rs.getString("member_id"));
					product.setProduct_color(rs.getString("product_color"));
					product.setProduct_img(rs.getString("product_img"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_num(rs.getInt("product_num"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_type(rs.getString("product_type"));
					product.setAmount(rs.getInt("amount"));
					cartList.add(product);
				}while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return cartList;
	}
	
}
