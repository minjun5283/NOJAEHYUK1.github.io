package order;

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
import product.productDBBean;

public class OrderDBBean {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	// 하나의 매서드만 이용할수 있을때 싱글 인스턴스 이용
	private static OrderDBBean dao = new OrderDBBean(); // 싱글 Instance 구조로 객체 선언해서 사용 - new 사용하지 않게 (매서드만 사용할수 있는 형태)

	public static OrderDBBean getInstance() { // DAO - 객체를 재사용 ,, DTO - 재사용X
		return dao;
	}

	private OrderDBBean() {
	}

	private Connection getConnection() throws Exception { // 1,2단계는 외부에서 사용할 X
		Context ctx = new InitialContext(); // context.xml 관리 - 추가 resource
		Context env = (Context) ctx.lookup("java:comp/env"); // java로 사용된것만 검색 //comp/env 이름의 객체를 Context 형태로
		DataSource ds = (DataSource) env.lookup("jdbc/orcl");
		Connection conn = ds.getConnection();
		return conn;

	}

	public void insertData(OrderDataBean arti) throws Exception { // 주문자 / 상품 정보 삽입
		int order_num = arti.getOrder_num();
		int number = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(order_num) from order1");
			rs = pstmt.executeQuery();
			if (rs.next())
				number = rs.getInt(1) + 1;
			else
				number = 1;

			pstmt = conn.prepareStatement("insert into order1 values(order1_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,'배송준비',?)");
			pstmt.setInt(1, arti.getProduct_num());
			pstmt.setString(2, arti.getMember_id());
			pstmt.setInt(3, arti.getOrder_price());
			pstmt.setString(4, arti.getOrder_size());
			pstmt.setString(5, arti.getOrder_color());
			pstmt.setTimestamp(6, arti.getOrder_date());
			pstmt.setInt(7, arti.getOrder_count());
			pstmt.setString(8, arti.getOrder_phone());
			pstmt.setString(9, arti.getOrder_add());
			pstmt.setString(10, arti.getOrder_etc());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}
	}

	public List getList() { // 주문자 전체 리스트
		List orderList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 order by order_date desc"); // 전체 검색
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderList = new ArrayList();
				do {
					OrderDataBean arti = new OrderDataBean();
					arti.setOrder_num(rs.getInt("order_num"));
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setMember_id(rs.getString("member_id"));
					arti.setOrder_price(rs.getInt("order_price"));
					arti.setOrder_size(rs.getString("order_size"));
					arti.setOrder_color(rs.getString("order_color"));
					arti.setOrder_date(rs.getTimestamp("order_date"));
					arti.setOrder_count(rs.getInt("order_count"));
					arti.setOrder_state(rs.getString("order_state"));
					orderList.add(arti);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return orderList;
	}

	public int getCount() throws Exception { // order 테이블 count 값
		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from order1");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return x;

	}

	public void deleteData(int order_num) throws Exception { // 특정 num 의 값을 제거
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where order_num=?");
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = conn.prepareStatement("delete from order1 where order_num=?");
				pstmt.setInt(1, order_num);
				pstmt.executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	public OrderDataBean getData(int order_num) throws Exception { // 특정 num 의 값을 제거

		OrderDataBean arti = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where order_num=?");
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				arti = new OrderDataBean();
				arti.setOrder_num(rs.getInt("order_num"));
				arti.setProduct_num(rs.getInt("product_num"));
				arti.setMember_id(rs.getString("member_id"));
				arti.setOrder_price(rs.getInt("order_price"));
				arti.setOrder_size(rs.getString("order_color"));
				arti.setOrder_color(rs.getString("order_color"));
				arti.setOrder_date(rs.getTimestamp("order_date"));
				arti.setOrder_count(rs.getInt("order_count"));
				arti.setOrder_phone(rs.getString("order_phone"));
				arti.setOrder_add(rs.getString("order_add"));
				arti.setOrder_state(rs.getString("order_state"));
				arti.setOrder_etc(rs.getString("order_etc"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return arti;
	}

	public void getStateUpdate(String x, int order_num) throws Exception {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where order_num=?");
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = conn.prepareStatement("update order1 set order_state=? where order_num=?");
				pstmt.setString(1, x);
				pstmt.setInt(2, order_num);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	public List getListOrder_num(String getTex) {
		List orderList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where order_num like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderList = new ArrayList();
				do {
					OrderDataBean arti = new OrderDataBean();
					arti.setOrder_num(rs.getInt("order_num"));
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setMember_id(rs.getString("member_id"));
					arti.setOrder_price(rs.getInt("order_price"));
					arti.setOrder_size(rs.getString("order_size"));
					arti.setOrder_color(rs.getString("order_color"));
					arti.setOrder_date(rs.getTimestamp("order_date"));
					arti.setOrder_count(rs.getInt("order_count"));
					arti.setOrder_state(rs.getString("order_state"));
					orderList.add(arti);

				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return orderList;
	}

	public List getListProduct_num(String getTex) {
		List orderList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where product_num like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderList = new ArrayList();
				do {
					OrderDataBean arti = new OrderDataBean();
					arti.setOrder_num(rs.getInt("order_num"));
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setMember_id(rs.getString("member_id"));
					arti.setOrder_price(rs.getInt("order_price"));
					arti.setOrder_size(rs.getString("order_size"));
					arti.setOrder_color(rs.getString("order_color"));
					arti.setOrder_date(rs.getTimestamp("order_date"));
					arti.setOrder_count(rs.getInt("order_count"));
					arti.setOrder_state(rs.getString("order_state"));
					orderList.add(arti);

				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return orderList;
	}

	public List getListMember_id(String getTex) {
		List orderList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where member_id like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderList = new ArrayList();
				do {
					OrderDataBean arti = new OrderDataBean();
					arti.setOrder_num(rs.getInt("order_num"));
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setMember_id(rs.getString("member_id"));
					arti.setOrder_price(rs.getInt("order_price"));
					arti.setOrder_size(rs.getString("order_size"));
					arti.setOrder_color(rs.getString("order_color"));
					arti.setOrder_date(rs.getTimestamp("order_date"));
					arti.setOrder_count(rs.getInt("order_count"));
					arti.setOrder_state(rs.getString("order_state"));
					orderList.add(arti);

				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return orderList;
	}

	public List getListOrder_state(String getTex) {
		List orderList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where order_state like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderList = new ArrayList();
				do {
					OrderDataBean arti = new OrderDataBean();
					arti.setOrder_num(rs.getInt("order_num"));
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setMember_id(rs.getString("member_id"));
					arti.setOrder_price(rs.getInt("order_price"));
					arti.setOrder_size(rs.getString("order_size"));
					arti.setOrder_color(rs.getString("order_color"));
					arti.setOrder_date(rs.getTimestamp("order_date"));
					arti.setOrder_count(rs.getInt("order_count"));
					arti.setOrder_state(rs.getString("order_state"));
					orderList.add(arti);

				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return orderList;
	}

	public int countOrder_num(String getTex) throws Exception {
		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from order1 where order_num like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	public int countProduct_num(String getTex) throws Exception {
		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from order1 where product_num like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	public int countOrder_state(String getTex) throws Exception {
		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from order1 where order_state like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	public int countMember_id(String getTex) throws Exception {
		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from order1 where member_id like?");
			pstmt.setString(1, "%" + getTex + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	public List getListId(String id) {
		List orderList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where member_id=? order by order_date desc ");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderList = new ArrayList();
				do {
					OrderDataBean arti = new OrderDataBean();
					arti.setOrder_num(rs.getInt("order_num"));
					arti.setProduct_num(rs.getInt("product_num"));
					arti.setOrder_price(rs.getInt("order_price"));
					arti.setOrder_size(rs.getString("order_size"));
					arti.setOrder_color(rs.getString("order_color"));
					arti.setOrder_date(rs.getTimestamp("order_date"));
					arti.setOrder_count(rs.getInt("order_count"));
					arti.setOrder_state(rs.getString("order_state"));
					orderList.add(arti);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return orderList;
	}

	public int getListIdCount(String id) {
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from order1 where member_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return count;
	}

	public void updateState(int order_num, String order_state) throws Exception {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from order1 where order_num=?");
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = conn.prepareStatement("update order1 set order_state= ? where order_num=? ");
				pstmt.setString(1, order_state);
				pstmt.setInt(2, order_num);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

}
