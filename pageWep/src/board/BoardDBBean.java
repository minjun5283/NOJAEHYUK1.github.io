package board;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*; 

public class BoardDBBean {

	private static BoardDBBean instance = new BoardDBBean();
	
	public static BoardDBBean getInstance() {
		return instance;
	
	}
	private BoardDBBean() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
		return ds.getConnection();
	}
	
	
	public void insertArticle(BoardDataBean article) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int board_num=article.getBoard_num();
		int board_ref=article.getBoard_ref();
		int board_restep=article.getBoard_restep();
		int board_relevel=article.getBoard_relevel();
		int board_number=0;
		String sql="";
		try {
			conn = getConnection(); 
			pstmt = conn.prepareStatement("select max(board_num) from board2"); //최대값 찾기.
			rs = pstmt.executeQuery();
			if (rs.next()) 
				board_number=rs.getInt(1)+1;	 //최대값 + 1
			else
				board_number=1; 
			if (board_num!=0) 
			{ 
				sql="update board2 set board_restep=board_restep+1 where board_ref= ? and board_restep> ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_ref);
				pstmt.setInt(2, board_restep);
				pstmt.executeUpdate();
				board_restep=board_restep+1;
				board_relevel=board_relevel+1;
			}else{ 
				board_ref=board_number;
				board_restep=0;
				board_relevel=0;
			} 
 
			sql = "insert into board2(board_num, member_id ,board_subject ,board_pw ,board_reg_date ,board_contents,";
			sql+="board_ref ,board_restep ,board_relevel,board_readcount) values(board2_seq.NEXTVAL,?,?,?,?,?,?,?,?,0)";  
				pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getMember_id());
			pstmt.setString(2, article.getBoard_subject());
			pstmt.setString(3, article.getBoard_pw());
			pstmt.setTimestamp(4, article.getBoard_reg_date());
			pstmt.setString(5, article.getBoard_contents());
			pstmt.setInt(6, board_ref);
			pstmt.setInt(7, board_restep);
			pstmt.setInt(8, board_relevel);
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
			pstmt = conn.prepareStatement("select count(*) from board2"); // 레코드의 수
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
			pstmt = conn.prepareStatement("select count(*) from board2 where  member_id =?"); // 레코드의 수
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

	public List getArticles(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList=null;
		try { // select (select (select) ) 
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,r "+
					"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,rownum r " +
					"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount " +
					"from board2 order by board_ref desc, board_restep asc) order by board_ref desc, board_restep asc ) where r >= ? and r <= ? ");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 

					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							BoardDataBean article= new BoardDataBean();
							article.setBoard_num(rs.getInt("board_num"));
							article.setMember_id(rs.getString("member_id"));
							article.setBoard_subject(rs.getString("board_subject"));
							article.setBoard_pw(rs.getString("board_pw"));
							article.setBoard_reg_date(rs.getTimestamp("board_reg_date"));
							article.setBoard_readcount(rs.getInt("board_readcount"));
							article.setBoard_ref(rs.getInt("board_ref"));
							article.setBoard_restep(rs.getInt("board_restep"));
							article.setBoard_relevel(rs.getInt("board_relevel"));
							article.setBoard_contents(rs.getString("board_contents"));
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
					"select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,r "+ 
							"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,r "+
							"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,rownum r " +
							"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount " +
							"from board2 order by board_ref desc, board_restep asc) order by board_ref desc, board_restep asc ) where r >= ? and r <= ?)  where  "+ch+" like '%"+search+"%' order by board_reg_date");
					pstmt.setInt(1, start); 
					pstmt.setInt(2, end); 
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							BoardDataBean article= new BoardDataBean();
							article.setBoard_num(rs.getInt("board_num"));
							article.setMember_id(rs.getString("member_id"));
							article.setBoard_subject(rs.getString("board_subject"));
							article.setBoard_pw(rs.getString("board_pw"));
							article.setBoard_reg_date(rs.getTimestamp("board_reg_date"));
							article.setBoard_readcount(rs.getInt("board_readcount"));
							article.setBoard_ref(rs.getInt("board_ref"));
							article.setBoard_restep(rs.getInt("board_restep"));
							article.setBoard_relevel(rs.getInt("board_relevel"));
							article.setBoard_contents(rs.getString("board_contents"));
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
					"select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,r "+ 
							"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,r "+
							"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount,rownum r " +
							"from (select board_num,member_id,board_subject,board_pw,board_reg_date,board_ref,board_restep,board_relevel,board_contents,board_readcount " +
							"from board2  where  member_id = ? order by board_ref desc, board_restep asc ) order by board_ref desc, board_restep asc ) where r >= ? and r <= ?)  order by board_reg_date");
					pstmt.setString(1, id);
					pstmt.setInt(2, start); 
					pstmt.setInt(3, end); 
					
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(end); 
						do{ 
							BoardDataBean article= new BoardDataBean();
							article.setBoard_num(rs.getInt("board_num"));
							article.setMember_id(rs.getString("member_id"));
							article.setBoard_subject(rs.getString("board_subject"));
							article.setBoard_pw(rs.getString("board_pw"));
							article.setBoard_reg_date(rs.getTimestamp("board_reg_date"));
							article.setBoard_readcount(rs.getInt("board_readcount"));
							article.setBoard_ref(rs.getInt("board_ref"));
							article.setBoard_restep(rs.getInt("board_restep"));
							article.setBoard_relevel(rs.getInt("board_relevel"));
							article.setBoard_contents(rs.getString("board_contents"));
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
	public BoardDataBean getArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"update board2 set board_readcount=board_readcount+1 where board_num = ?"); 
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(
			"select * from board2 where board_num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article= new BoardDataBean();
				article.setBoard_num(rs.getInt("board_num"));
				article.setMember_id(rs.getString("member_id"));
				article.setBoard_subject(rs.getString("board_subject"));
				article.setBoard_pw(rs.getString("board_pw"));
				article.setBoard_reg_date(rs.getTimestamp("board_reg_date"));
				article.setBoard_readcount(rs.getInt("board_readcount"));
				article.setBoard_ref(rs.getInt("board_ref"));
				article.setBoard_restep(rs.getInt("board_restep"));
				article.setBoard_relevel(rs.getInt("board_relevel"));
				article.setBoard_contents(rs.getString("board_contents"));
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
	
	
	public BoardDataBean updateGetArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select * from board2 where board_num = ?"); 
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article= new BoardDataBean();
				article.setBoard_num(rs.getInt("board_num"));
				article.setMember_id(rs.getString("member_id"));
				article.setBoard_subject(rs.getString("board_subject"));
				article.setBoard_pw(rs.getString("board_pw"));
				article.setBoard_reg_date(rs.getTimestamp("board_reg_date"));
				article.setBoard_readcount(rs.getInt("board_readcount"));
				article.setBoard_ref(rs.getInt("board_ref"));
				article.setBoard_restep(rs.getInt("board_restep"));
				article.setBoard_relevel(rs.getInt("board_relevel"));
				article.setBoard_contents(rs.getString("board_contents"));
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
	

	public int updateArticle(BoardDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String dbpasswd="";
		String sql="";
		int x=-1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
			"select board_pw from board2 where board_num = ?");
			pstmt.setInt(1, article.getBoard_num());
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd= rs.getString("board_pw"); 
				if(dbpasswd.equals(article.getBoard_pw())){
					sql="update board2 set member_id=?,board_subject=?,board_pw=?";
					sql+=",board_contents=? where board_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getMember_id());
					pstmt.setString(2, article.getBoard_subject());
					pstmt.setString(3, article.getBoard_pw());
					pstmt.setString(4, article.getBoard_contents());
					pstmt.setInt(5, article.getBoard_num());
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
			"select board_pw from board2 where board_num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dbpasswd= rs.getString("board_pw");
				if(dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement("delete from board2 where board_num=?");
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
			String sql = " where "+ch+" like '%"+search+"%'";
			pstmt = conn.prepareStatement("select count(*) from board2"+sql); // 레코드의 수
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
			
			String sql = " where " + ch + " like '%"+search+"%' order by board_reg_date";
			pstmt = conn.prepareStatement("select * from board"+sql); // 레코드의 수
					rs = pstmt.executeQuery();
					if (rs.next()) {
						articleList = new ArrayList(); 
						do{ 
							BoardDataBean article= new BoardDataBean();
							article.setBoard_num(rs.getInt("board_num"));
							article.setMember_id(rs.getString("member_id"));
							article.setBoard_subject(rs.getString("board_subject"));
							article.setBoard_pw(rs.getString("board_pw"));
							article.setBoard_reg_date(rs.getTimestamp("board_reg_date"));
							article.setBoard_readcount(rs.getInt("board_readcount"));
							article.setBoard_ref(rs.getInt("board_ref"));
							article.setBoard_restep(rs.getInt("board_restep"));
							article.setBoard_relevel(rs.getInt("board_relevel"));
							article.setBoard_contents(rs.getString("board_contents"));
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
