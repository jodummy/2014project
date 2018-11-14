package ya.rain.bow.dtos;

public class BoardFileDto {
	
	private int bf_no;
	private int board_no;
	private String bf_oldnm;
	private String bf_savenm;
	private String bf_regdate;
	
	public BoardFileDto() {
	
	}
	
	public BoardFileDto(int board_no, String bf_oldnm, String bf_savenm) {
		super();
		this.board_no = board_no;
		this.bf_oldnm = bf_oldnm;
		this.bf_savenm = bf_savenm;
	}

	public int getBf_no() {
		return bf_no;
	}

	public void setBf_no(int bf_no) {
		this.bf_no = bf_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBf_oldnm() {
		return bf_oldnm;
	}

	public void setBf_oldnm(String bf_oldnm) {
		this.bf_oldnm = bf_oldnm;
	}

	public String getBf_savenm() {
		return bf_savenm;
	}

	public void setBf_savenm(String bf_savenm) {
		this.bf_savenm = bf_savenm;
	}

	public String getBf_regdate() {
		return bf_regdate.split(" ")[0];
	}

	public void setBf_regdate(String bf_regdate) {
		this.bf_regdate = bf_regdate;
	}
	
}
