package ya.rain.bow.dtos;

public class LoginDto {

	private String mem_no;
	private String mem_email;
	private String mem_name;
	private int auth_no;

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
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

	@Override
	public String toString() {
		return "LoginDto [mem_no=" + mem_no + ", mem_email=" + mem_email + ", mem_name=" + mem_name + ", auth_no="
				+ auth_no + "]";
	}

	public LoginDto(String mem_no, String mem_email, String mem_name, int auth_no) {
		super();
		this.mem_no = mem_no;
		this.mem_email = mem_email;
		this.mem_name = mem_name;
		this.auth_no = auth_no;
	}

	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
