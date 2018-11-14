package ya.rain.bow.dtos;

public class ProjectDto {
	
	private int prj_no;
	private String dept_no;
	private String prj_name;
	private String prj_start;
	private String prj_last;
	private String prj_delflag;
	private String prjs_term;
	
	public ProjectDto() {}

	public int getPrj_no() {
		return prj_no;
	}
	public void setPrj_no(int prj_no) {
		this.prj_no = prj_no;
	}
	public String getDept_no() {
		return dept_no;
	}
	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}
	public String getPrj_name() {
		return prj_name;
	}
	public void setPrj_name(String prj_name) {
		this.prj_name = prj_name;
	}
	public String getPrj_start() {
		return prj_start.split(" ")[0];
	}
	public void setPrj_start(String prj_start) {
		this.prj_start = prj_start;
	}
	public String getPrj_last() {
		return prj_last.split(" ")[0];
	}
	public void setPrj_last(String prj_last) {
		this.prj_last = prj_last;
	}
	public String getPrj_delflag() {
		return prj_delflag;
	}
	public void setPrj_delflag(String prj_delflag) {
		this.prj_delflag = prj_delflag;
	}
	public String getPrjs_term() {
		return prjs_term;
	}
	public void setPrjs_term(String prjs_term) {
		this.prjs_term = prjs_term;
	}
	
	@Override
	public String toString() {
		return "ProjectDto [prj_no=" + prj_no + ", dept_no=" + dept_no + ", prj_name=" + prj_name + ", prj_start="
				+ prj_start + ", prj_last=" + prj_last + ", prj_delflag=" + prj_delflag + ", prjs_term=" + prjs_term
				+ "]";
	}
	

	
}
