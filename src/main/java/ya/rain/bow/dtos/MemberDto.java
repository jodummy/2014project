package ya.rain.bow.dtos;

public class MemberDto {
	@Override
	public String toString() {
		return "MemberDto [mem_no=" + mem_no + ", mem_email=" + mem_email + ", dept_no=" + dept_no + ", auth_no="
				+ auth_no + ", mem_name=" + mem_name + ", mem_tel=" + mem_tel + ", mem_sex=" + mem_sex + ", mem_birth="
				+ mem_birth + ", mem_photonm=" + mem_photonm + ", mem_signnm=" + mem_signnm + ", mem_ragdate="
				+ mem_ragdate + ", mem_delflag=" + mem_delflag + ", mem_deldate=" + mem_deldate + ", dept_name="
				+ dept_name + ", dept_top=" + dept_top + "]";
	}

	private int mem_no;
	private String mem_email;
	private String dept_no;
	private int auth_no;
	private String mem_name;
	private String mem_tel;
	private String mem_sex;
	private String mem_birth;
	private String mem_photonm;
	private String mem_signnm;
	private String mem_ragdate;
	private String mem_delflag;
	private String mem_deldate;
	private String dept_name;
	private String dept_top;

	public String getDept_top() {
		return dept_top;
	}

	public void setDept_top(String dept_top) {
		this.dept_top = dept_top;
	}

	public MemberDto(int mem_no, int auth_no) {
		super();
		this.mem_no = mem_no;
		this.auth_no = auth_no;
	}

	public MemberDto(int mem_no, int auth_no, String dept_top) {
		super();
		this.mem_no = mem_no;
		this.auth_no = auth_no;
		this.dept_top = dept_top;
	}

	public MemberDto(int mem_no, String mem_email, String dept_no, int auth_no, String mem_name, String mem_signnm,
			String mem_delflag, String dept_name) {
		super();
		this.mem_no = mem_no;
		this.mem_email = mem_email;
		this.dept_no = dept_no;
		this.auth_no = auth_no;
		this.mem_name = mem_name;
		this.mem_signnm = mem_signnm;
		this.mem_delflag = mem_delflag;
		this.dept_name = dept_name;
	}

	public MemberDto(String mem_name, String mem_tel, String mem_sex, String mem_birth, String mem_photonm,
			String mem_signnm, String mem_email) {
		super();
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_sex = mem_sex;
		this.mem_birth = mem_birth;
		this.mem_photonm = mem_photonm;
		this.mem_signnm = mem_signnm;
		this.mem_email = mem_email;
	}

	public MemberDto(String mem_email, String dept_no, String mem_name, String mem_tel, String mem_sex,
			String mem_birth, String mem_photonm, String mem_signnm) {
		super();
		this.mem_email = mem_email;
		this.dept_no = dept_no;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_sex = mem_sex;
		this.mem_birth = mem_birth;
		this.mem_photonm = mem_photonm;
		this.mem_signnm = mem_signnm;
	}

	public MemberDto(String dept_no, int auth_no) {
		super();
		this.dept_no = dept_no;
		this.auth_no = auth_no;
	}

	public MemberDto(int mem_no, String dept_no) {
		super();
		this.mem_no = mem_no;
		this.dept_no = dept_no;
	}

	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String mem_email, String dept_no, int auth_no) {
		super();
		this.mem_email = mem_email;
		this.dept_no = dept_no;
		this.auth_no = auth_no;
	}

	public MemberDto(String dept_no) {
		// TODO Auto-generated constructor stub
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public int getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(int auth_no) {
		this.auth_no = auth_no;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_sex() {
		return mem_sex;
	}

	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}

	public String getMem_birth() {
		return mem_birth.split(" ")[0];
	}

	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_photonm() {
		return mem_photonm;
	}

	public void setMem_photonm(String mem_photonm) {
		this.mem_photonm = mem_photonm;
	}

	public String getMem_signnm() {
		return mem_signnm;
	}

	public void setMem_signnm(String mem_signnm) {
		this.mem_signnm = mem_signnm;
	}

	public String getMem_ragdate() {
		return mem_ragdate;
	}

	public void setMem_ragdate(String mem_regdate) {
		this.mem_ragdate = mem_regdate;
	}

	public String getMem_delflag() {
		return mem_delflag;
	}

	public void setMem_delflag(String mem_delflag) {
		this.mem_delflag = mem_delflag;
	}

	public String getMem_deldate() {
		return mem_deldate;
	}

	public void setMem_deldate(String mem_deldate) {
		this.mem_deldate = mem_deldate;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

}
