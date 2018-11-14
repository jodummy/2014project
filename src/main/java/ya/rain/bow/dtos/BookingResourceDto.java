package ya.rain.bow.dtos;

public class BookingResourceDto {
	
	private int br_no;
	private String br_name;
	private String schc_code;
	private String br_ctgr;
	
	public BookingResourceDto() {}
	public BookingResourceDto(int br_no, String br_name, String schc_code, String br_ctgr) {
		this.br_no = br_no;
		this.br_name = br_name;
		this.schc_code = schc_code;
		this.br_ctgr = br_ctgr;
	}
	
	public int getBr_no() {
		return br_no;
	}
	public void setBr_no(int br_no) {
		this.br_no = br_no;
	}
	public String getBr_name() {
		return br_name;
	}
	public void setBr_name(String br_name) {
		this.br_name = br_name;
	}
	public String getSchc_code() {
		return schc_code;
	}
	public void setSchc_code(String schc_code) {
		this.schc_code = schc_code;
	}
	public String getBr_ctgr() {
		return br_ctgr;
	}
	public void setBr_ctgr(String br_ctgr) {
		this.br_ctgr = br_ctgr;
	}
	
	@Override
	public String toString() {
		return "BookingResourceDto [br_no=" + br_no + ", br_name=" + br_name + ", schc_code=" + schc_code + ", br_ctgr="
				+ br_ctgr + "]";
	}
}
