package order;

import java.sql.Timestamp;

public class OrderDataBean {
	
	private int order_num;
	private int product_num;
	private String member_id;
	private int order_price;
	private String order_size;
	private String order_color;
	private Timestamp order_date;
	private int order_count;
	private String order_phone;
	private String order_add;
	private String order_state;
	private String order_etc;
	
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public String getOrder_size() {
		return order_size;
	}
	public void setOrder_size(String order_size) {
		this.order_size = order_size;
	}
	public String getOrder_color() {
		return order_color;
	}
	public void setOrder_color(String order_color) {
		this.order_color = order_color;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	public String getOrder_add() {
		return order_add;
	}
	public void setOrder_add(String order_add) {
		this.order_add = order_add;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getOrder_etc() {
		return order_etc;
	}
	public void setOrder_etc(String order_etc) {
		this.order_etc = order_etc;
	}

}
