package ya.rain.bow.dtos;

public class AnswerboardDto {
	
	private int ab_seq;    
	private String ab_name;   
	private String ab_title; 
	private String ab_content;
	private String ab_password;
	private int ab_refer;
	private int ab_step;   
	private int ab_depth;
	private int ab_readcount;
	private String ab_delflag;
	private String ab_regdate;
	
	public AnswerboardDto() {
	}
	public int getAb_seq() {
		return ab_seq;
	}

	public void setAb_seq(int ab_seq) {
		this.ab_seq = ab_seq;
	}

	public String getAb_name() {
		return ab_name;
	}

	public void setAb_name(String ab_name) {
		this.ab_name = ab_name;
	}

	public String getAb_title() {
		return ab_title;
	}

	public void setAb_title(String ab_title) {
		this.ab_title = ab_title;
	}

	public String getAb_content() {
		return ab_content;
	}

	public void setAb_content(String ab_content) {
		this.ab_content = ab_content;
	}

	public String getAb_password() {
		return ab_password;
	}
	public void setAb_password(String ab_password) {
		this.ab_password = ab_password;
	}
	public int getAb_refer() {
		return ab_refer;
	}

	public void setAb_refer(int ab_refer) {
		this.ab_refer = ab_refer;
	}

	public int getAb_step() {
		return ab_step;
	}

	public void setAb_step(int ab_step) {
		this.ab_step = ab_step;
	}

	public int getAb_depth() {
		return ab_depth;
	}

	public void setAb_depth(int ab_depth) {
		this.ab_depth = ab_depth;
	}

	public int getAb_readcount() {
		return ab_readcount;
	}

	public void setAb_readcount(int ab_readcount) {
		this.ab_readcount = ab_readcount;
	}

	public String getAb_delflag() {
		return ab_delflag;
	}

	public void setAb_delflag(String ab_delflag) {
		this.ab_delflag = ab_delflag;
	}

	public String getAb_regdate() {
		return ab_regdate.split(" ")[0];
	}

	public void setAb_regdate(String ab_regdate) {
		this.ab_regdate = ab_regdate;
	}
	@Override
	public String toString() {
		return "AnswerboardDto [ab_seq=" + ab_seq + ", ab_name=" + ab_name
				+ ", ab_title=" + ab_title + ", ab_content=" + ab_content
				+ ", ab_password=" + ab_password + ", ab_refer=" + ab_refer
				+ ", ab_step=" + ab_step + ", ab_depth=" + ab_depth
				+ ", ab_readcount=" + ab_readcount + ", ab_delflag="
				+ ab_delflag + ", ab_regdate=" + ab_regdate + "]";
	}
}
