package ya.rain.bow.dtos;

public class ProjectSchduleDto {

	private int prj_no;
	private String prjs_item;
	private String prjs_name;
	private int prjs_refer;
	private int prjs_step;
	private int prjs_depth;
	private String prjs_term;
	private int prjs_cnt;
	
	public ProjectSchduleDto() {}

	public ProjectSchduleDto(int prj_no, String prjs_item, String prjs_name, int prjs_refer, int prjs_step,
			int prjs_depth, String prjs_term) {
		this.prj_no = prj_no;
		this.prjs_item = prjs_item;
		this.prjs_name = prjs_name;
		this.prjs_refer = prjs_refer;
		this.prjs_step = prjs_step;
		this.prjs_depth = prjs_depth;
		this.prjs_term = prjs_term;
	}

	public int getPrj_no() {
		return prj_no;
	}
	public void setPrj_no(int prj_no) {
		this.prj_no = prj_no;
	}
	public String getPrjs_item() {
		return prjs_item;
	}
	public void setPrjs_item(String prjs_item) {
		this.prjs_item = prjs_item;
	}
	public String getPrjs_name() {
		return prjs_name;
	}
	public void setPrjs_name(String prjs_name) {
		this.prjs_name = prjs_name;
	}
	public int getPrjs_refer() {
		return prjs_refer;
	}
	public void setPrjs_refer(int prjs_refer) {
		this.prjs_refer = prjs_refer;
	}
	public int getPrjs_step() {
		return prjs_step;
	}
	public void setPrjs_step(int prjs_step) {
		this.prjs_step = prjs_step;
	}
	public int getPrjs_depth() {
		return prjs_depth;
	}
	public void setPrjs_depth(int prjs_depth) {
		this.prjs_depth = prjs_depth;
	}
	public String getPrjs_term() {
		return prjs_term;
	}
	public void setPrjs_term(String prjs_term) {
		this.prjs_term = prjs_term;
	}
	public int getPrjs_cnt() {
		return prjs_cnt;
	}
	public void setPrjs_cnt(int prjs_cnt) {
		this.prjs_cnt = prjs_cnt;
	}

	@Override
	public String toString() {
		return "ProjectSchduleDto [prj_no=" + prj_no + ", prjs_item=" + prjs_item + ", prjs_name=" + prjs_name
				+ ", prjs_refer=" + prjs_refer + ", prjs_step=" + prjs_step + ", prjs_depth=" + prjs_depth
				+ ", prjs_term=" + prjs_term + ", prjs_cnt=" + prjs_cnt + "]";
	}
}
