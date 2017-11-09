package board;
import java.sql.Timestamp;

public class BoardDataBean{

	private int board_num; 
    private String member_id;
    private String board_subject;
    private String board_pw;
	private String board_contents;
    private int board_ref;
    private int board_restep;	
    private int board_relevel;
    private int board_readcount;
    private Timestamp board_reg_date;
    
    public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_pw() {
		return board_pw;
	}
	public void setBoard_pw(String board_pw) {
		this.board_pw = board_pw;
	}
	public String getBoard_contents() {
		return board_contents;
	}
	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}
	public int getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}
	public int getBoard_restep() {
		return board_restep;
	}
	public void setBoard_restep(int board_restep) {
		this.board_restep = board_restep;
	}
	public int getBoard_relevel() {
		return board_relevel;
	}
	public void setBoard_relevel(int board_relevel) {
		this.board_relevel = board_relevel;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public Timestamp getBoard_reg_date() {
		return board_reg_date;
	}
	public void setBoard_reg_date(Timestamp board_reg_date) {
		this.board_reg_date = board_reg_date;
	}

}
    
//작성자: 안다인 boardDTO

