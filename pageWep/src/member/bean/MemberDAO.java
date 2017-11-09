//로그인 회원가입 정보수정 및 탈퇴 

package member.bean;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import javax.sql.*;
import javax.naming.*;

import member.bean.MemberDTO;


public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {return instance;}
	
	private MemberDAO() {}
	
	private Connection getConnection() throws Exception{
		Context Ctx= new InitialContext();
		Context env= (Context)Ctx.lookup("java:comp/env");
		DataSource ds = (DataSource)env.lookup("jdbc/orcl");
		return ds.getConnection();	
	}
	Connection conn= null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public void insertMember(MemberDTO member) throws Exception{ 
		try {
			conn= getConnection();
			pstmt= conn.prepareStatement(
					"insert into MEMBER2 values(?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());
			pstmt.executeUpdate();
	     } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	    }
	public int userCheck(String id, String pw)throws Exception{
		String dbpw="";
		int x=-1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select member_pw from MEMBER2 where member_id=?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				dbpw= rs.getString("member_pw");
				if(dbpw.equals(pw)) 
					x= 1;
				else x=0;
			}else x=-1;
	       } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
	}
	public int confirmId(String id) throws Exception{
		String dbpw="";
		int x=-1;
		
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(
					"select member_id from MEMBER2 where member_id =?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {x=1;}
			else {x=-1;}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs !=null) try {rs.close();}catch(Exception ex) {}
			if(pstmt != null) try {pstmt.close();} catch(Exception ex) {}
			if(conn != null) try{conn.close();} catch(Exception ex) {}
		}return x;
	}
	public MemberDTO getMember(String id)throws Exception {
		 MemberDTO member=null;
        try {
            conn = getConnection();
			           
            pstmt = conn.prepareStatement(
			            "select * from MEMBER2 where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
			       member = new MemberDTO();
			       member.setId(rs.getString("member_id"));
			       member.setPw(rs.getString("member_pw"));
			       member.setName(rs.getString("member_name"));
			       member.setPhone(rs.getString("member_phone"));
			       member.setAddress(rs.getString("member_add"));
						}
		 } catch(Exception ex) {
		      ex.printStackTrace();
		 } finally {
			  if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			  if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			  if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		 }
		return member;
	}
			    
	public void updateMember(MemberDTO member)throws Exception {
		try {
		      conn = getConnection();
			            
		      pstmt = conn.prepareStatement(
		             "update MEMBER2 set member_pw=?,member_name=?,member_phone=?,member_add=? where member_id=?");
			            pstmt.setString(1, member.getPw());
			            pstmt.setString(2, member.getName());
			            pstmt.setString(3, member.getPhone());
			            pstmt.setString(4, member.getAddress());
			            pstmt.setString(5, member.getId());
			            
			            pstmt.executeUpdate();
	  } catch(Exception ex) {
		   ex.printStackTrace();
	  } finally {
			  if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			  if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	  			}
     }
			    
	public int deleteMember(String id, String pw)throws Exception {
			       
	     String dbpw="";
	     int x=-1;
	     try {
				conn = getConnection();
	            pstmt = conn.prepareStatement(
	            		"select member_pw from MEMBER2 where member_id = ?");
		        pstmt.setString(1, id);
		        rs = pstmt.executeQuery();
			            
				if(rs.next()){
						dbpw= rs.getString("member_pw"); 
						if(dbpw.equals(pw)){
								pstmt = conn.prepareStatement(
									"delete from MEMBER2 where member_id=?");
			                    pstmt.setString(1, id);
			                    pstmt.executeUpdate();
								x= 1; 
							}else
								x= 0; 
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
	public List getAllMember() throws Exception
	{
		List AllList=null;
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement("select * from MEMBER2");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				AllList = new ArrayList();
			do {
				MemberDTO arti = new MemberDTO();
				arti.setId(rs.getString("member_id"));
				arti.setPw(rs.getString("member_pw"));
				arti.setName(rs.getString("member_name"));
			    arti.setPhone(rs.getString("member_phone"));
			    arti.setAddress(rs.getString("member_add"));
			    AllList.add(arti);
				
			}while(rs.next());					
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch(SQLException e) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if (conn != null) try { conn.close(); } catch(SQLException e) {}	
		}
		
		return AllList;
	}
	public int getCount() throws Exception { //product 테이블 count 값 
		int x=0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from member2");
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
	
	
}


/*작성자: 조문영  /수정 날짜 :20171018*/


