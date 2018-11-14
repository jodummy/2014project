package ya.rain.bow.dtos;

public class BoardDto {
	
	private int rnum;
	private int ctgr_no;
	private int board_no;
	private int mem_no;
	private String mem_name;
	private String dept_no;
	private String dept_name;
	private String board_notice;
	private String board_title;
	private String board_content;
	private int board_readcnt;
	private String board_regdate;
	private String board_delflag;
	private int board_replycnt;
	
	public BoardDto() {
		
	}
	
	// 글수정에서 사용하는 생성자
	public BoardDto(int board_no, String board_notice, String board_title, String board_content) {
		super();
		this.board_no = board_no;
		this.board_notice = board_notice;
		this.board_title = board_title;
		this.board_content = board_content;
	}
	
	// 게시글 등록에 사용하는 생성자
	public BoardDto(int ctgr_no, int mem_no, String dept_no, String board_notice, String board_title,
			String board_content) {
		super();
		this.ctgr_no = ctgr_no;
		this.mem_no = mem_no;
		this.dept_no = dept_no;
		this.board_notice = board_notice;
		this.board_title = board_title;
		this.board_content = board_content;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getCtgr_no() {
		return ctgr_no;
	}

	public void setCtgr_no(int ctgr_no) {
		this.ctgr_no = ctgr_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getBoard_notice() {
		return board_notice;
	}

	public void setBoard_notice(String board_notice) {
		this.board_notice = board_notice;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_readcnt() {
		return board_readcnt;
	}

	public void setBoard_readcnt(int board_readcnt) {
		this.board_readcnt = board_readcnt;
	}

	public String getBoard_regdate() {
		return board_regdate.split(" ")[0];
	}

	public void setBoard_regdate(String board_regdate) {
		this.board_regdate = board_regdate;
	}

	public String getBoard_delflag() {
		return board_delflag;
	}

	public void setBoard_delflag(String board_delflag) {
		this.board_delflag = board_delflag;
	}

	public int getBoard_replycnt() {
		return board_replycnt;
	}

	public void setBoard_replycnt(int board_replycnt) {
		this.board_replycnt = board_replycnt;
	}

	@Override
	public String toString() {
		return "BoardDto [rnum=" + rnum + ", ctgr_no=" + ctgr_no + ", board_no=" + board_no + ", mem_no=" + mem_no
				+ ", mem_name=" + mem_name + ", dept_no=" + dept_no + ", dept_name=" + dept_name + ", board_notice="
				+ board_notice + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_readcnt=" + board_readcnt + ", board_regdate=" + board_regdate + ", board_delflag="
				+ board_delflag + ", board_replycnt=" + board_replycnt + "]";
	}
	
}
