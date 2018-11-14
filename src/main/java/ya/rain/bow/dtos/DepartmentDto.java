package ya.rain.bow.dtos;

public class DepartmentDto {

	public String dept_no;
	public String dept_name;
	public String dept_top;
	public String dept_delflag;
	private String mem_name;
	private int auth_no;
	private String schc_code;

	
	public DepartmentDto() {}
	
	public DepartmentDto(String dept_no, String dept_name, String dept_top, String dept_delflag) {
		super();
		this.dept_no = dept_no;
		this.dept_name = dept_name;
		this.dept_top = dept_top;
		this.dept_delflag = dept_delflag;
	}

	public DepartmentDto(String dept_no, String dept_name, String dept_top) {
		super();
		this.dept_no = dept_no;
		this.dept_name = dept_name;
		this.dept_top = dept_top;

	}

	public DepartmentDto(String dept_no, int auth_no) {
		super();
		this.dept_no = dept_no;
		this.auth_no = auth_no;
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

	public String getDept_top() {
		return dept_top;
	}

	public void setDept_top(String dept_top) {
		this.dept_top = dept_top;
	}

	public String getDept_delflag() {
		return dept_delflag;
	}

	public void setDept_delflag(String dept_delflag) {
		this.dept_delflag = dept_delflag;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public int getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(int auth_no) {
		this.auth_no = auth_no;
	}
	public String getSchc_code() {
		return schc_code;
	}

	public void setSchc_code(String schc_code) {
		this.schc_code = schc_code;
	}

	@Override
	public String toString() {
		return "DepartmentDto [dept_no=" + dept_no + ", dept_name=" + dept_name + ", dept_top=" + dept_top
				+ ", dept_delflag=" + dept_delflag + ", mem_name=" + mem_name + ", auth_no=" + auth_no + ", schc_code="
				+ schc_code + "]";
	}

}
