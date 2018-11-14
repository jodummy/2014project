package ya.rain.bow.dtos;

public class ApprovalDto {

	private int doc_mngno;
	private int mem_no;
	private int app_order;
	private String app_status;
	private int ar_no;
	private String ar_content;
	private String ar_writer;
	private String ar_regdate;

	public ApprovalDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApprovalDto(int doc_mngno, int mem_no, int app_order) {
		super();
		this.doc_mngno = doc_mngno;
		this.mem_no = mem_no;
		this.app_order = app_order;
	}

	public ApprovalDto(int doc_mngno, int mem_no, int app_order, String app_status) {
		super();
		this.doc_mngno = doc_mngno;
		this.mem_no = mem_no;
		this.app_order = app_order;
		this.app_status = app_status;
	}

	public ApprovalDto(int doc_mngno, String ar_content, String ar_writer) {
		super();
		this.doc_mngno = doc_mngno;
		this.ar_content = ar_content;
		this.ar_writer = ar_writer;
	}



	public ApprovalDto(int doc_mngno, int ar_no, String ar_content,
			String ar_writer) {
		super();
		this.doc_mngno = doc_mngno;
		this.ar_no = ar_no;
		this.ar_content = ar_content;
		this.ar_writer = ar_writer;
	}

	public int getDoc_mngno() {
		return doc_mngno;
	}

	public void setDoc_mngno(int doc_mngno) {
		this.doc_mngno = doc_mngno;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public int getApp_order() {
		return app_order;
	}

	public void setApp_order(int app_order) {
		this.app_order = app_order;
	}

	public String getApp_status() {
		return app_status;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}

	public String getAr_content() {
		return ar_content;
	}

	public void setAr_content(String ar_content) {
		this.ar_content = ar_content;
	}

	public String getAr_writer() {
		return ar_writer;
	}

	public void setAr_writer(String ar_writer) {
		this.ar_writer = ar_writer;
	}

	public int getAr_no() {
		return ar_no;
	}

	public void setAr_no(int ar_no) {
		this.ar_no = ar_no;
	}

	public String getAr_regdate() {
		return ar_regdate;
	}

	public void setAr_regdate(String ar_regdate) {
		this.ar_regdate = ar_regdate;
	}

	@Override
	public String toString() {
		return "ApprovalDto [doc_mngno=" + doc_mngno + ", mem_no=" + mem_no
				+ ", app_order=" + app_order + ", app_status=" + app_status
				+ ", ar_no=" + ar_no + ", ar_content=" + ar_content
				+ ", ar_writer=" + ar_writer + ", ar_regdate=" + ar_regdate
				+ "]";
	}

	

}
