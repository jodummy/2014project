package ya.rain.bow.dtos;

public class BoardReplyDto {
	
	private int br_no;
	private int board_no;
	private String dept_no;
	private String dept_name;
	private int mem_no;
	private String mem_name;
	private String br_content;
	private int br_refer;
	private int br_step;
	private int br_depth;
	private String br_regdate;
	private String br_delflag;
	
	public BoardReplyDto() {

	}
	
	// 댓글 등록에 사용하는 생성자
	public BoardReplyDto(int board_no, String dept_no, int mem_no, String br_content) {
		super();
		this.board_no = board_no;
		this.dept_no = dept_no;
		this.mem_no = mem_no;
		this.br_content = br_content;
	}
	
	// 댓글'댓글 등록에 사용하는 생성자
	public BoardReplyDto(int br_no, int board_no, String dept_no, int mem_no, String br_content) {
		super();
		this.br_no = br_no;
		this.board_no = board_no;
		this.dept_no = dept_no;
		this.mem_no = mem_no;
		this.br_content = br_content;
	}
	
	// 댓글 수정에 사용하는 생성자
	public BoardReplyDto(int br_no, String br_content, String br_regdate) {
		super();
		this.br_no = br_no;
		this.br_content = br_content;
		this.br_regdate = br_regdate;
	}

	public int getBr_no() {
		return br_no;
	}

	public void setBr_no(int br_no) {
		this.br_no = br_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
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

	public String getBr_content() {
		return br_content;
	}

	public void setBr_content(String br_content) {
		this.br_content = br_content;
	}

	public int getBr_refer() {
		return br_refer;
	}

	public void setBr_refer(int br_refer) {
		this.br_refer = br_refer;
	}

	public int getBr_step() {
		return br_step;
	}

	public void setBr_step(int br_step) {
		this.br_step = br_step;
	}

	public int getBr_depth() {
		return br_depth;
	}

	public void setBr_depth(int br_depth) {
		this.br_depth = br_depth;
	}

	public String getBr_regdate() {
		return br_regdate.split(" ")[0];
	}

	public void setBr_regdate(String br_regdate) {
		this.br_regdate = br_regdate;
	}

	public String getBr_delflag() {
		return br_delflag;
	}

	public void setBr_delflag(String br_delflag) {
		this.br_delflag = br_delflag;
	}

	@Override
	public String toString() {
		return "BoardReplyDto [br_no=" + br_no + ", board_no=" + board_no + ", dept_no=" + dept_no + ", dept_name="
				+ dept_name + ", mem_no=" + mem_no + ", mem_name=" + mem_name + ", br_content=" + br_content
				+ ", br_refer=" + br_refer + ", br_step=" + br_step + ", br_depth=" + br_depth + ", br_regdate="
				+ br_regdate + ", br_delflag=" + br_delflag + "]";
	}
	
}
