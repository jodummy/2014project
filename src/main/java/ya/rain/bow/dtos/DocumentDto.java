package ya.rain.bow.dtos;

public class DocumentDto {

	private int doc_mngno;
	private int temp_no;
	private int mem_no;
	private String doc_deptnm;
	private String doc_writer;
	private String doc_title;
	private String doc_content;
	private String doc_regdate;
	private String doc_no;
	private String doc_status;
	private String doc_lastdate;
	private String doc_reperence;
	private String doc_bcc;
	private String doc_cooperation;
	private String doc_conference;

	private String app_status;
	private String app_order;

	public DocumentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentDto(int doc_mngno, String app_status, String doc_status) {
		super();
		this.doc_mngno = doc_mngno;
		this.doc_status = doc_status;
		this.app_status = app_status;
	}


	public DocumentDto(int mem_no, String app_status) {
		super();
		this.mem_no = mem_no;
		this.app_status = app_status;
	}

	public DocumentDto(int doc_mngno, int temp_no, int mem_no, String doc_deptnm, String doc_writer, String doc_title,
			String doc_content, String doc_regdate, String doc_status, String doc_lastdate) {
		super();
		this.doc_mngno = doc_mngno;
		this.temp_no = temp_no;
		this.mem_no = mem_no;
		this.doc_deptnm = doc_deptnm;
		this.doc_writer = doc_writer;
		this.doc_title = doc_title;
		this.doc_content = doc_content;
		this.doc_regdate = doc_regdate;
		this.doc_status = doc_status;
		this.doc_lastdate = doc_lastdate;
	}

	public DocumentDto(int doc_mngno, int temp_no, int mem_no, String doc_deptnm, String doc_writer, String doc_title,
			String doc_content, String doc_regdate, String doc_status, String doc_lastdate, String doc_reperence) {
		super();
		this.doc_mngno = doc_mngno;
		this.temp_no = temp_no;
		this.mem_no = mem_no;
		this.doc_deptnm = doc_deptnm;
		this.doc_writer = doc_writer;
		this.doc_title = doc_title;
		this.doc_content = doc_content;
		this.doc_regdate = doc_regdate;
		this.doc_status = doc_status;
		this.doc_lastdate = doc_lastdate;
		this.doc_reperence = doc_reperence;
	}
	
	public DocumentDto(int doc_mngno, int temp_no, int mem_no, String doc_deptnm, String doc_writer, String doc_title,
			String doc_content, String doc_regdate, String doc_no, String doc_status, String doc_lastdate,
			String doc_reperence) {
		super();
		this.doc_mngno = doc_mngno;
		this.temp_no = temp_no;
		this.mem_no = mem_no;
		this.doc_deptnm = doc_deptnm;
		this.doc_writer = doc_writer;
		this.doc_title = doc_title;
		this.doc_content = doc_content;
		this.doc_regdate = doc_regdate;
		this.doc_no = doc_no;
		this.doc_status = doc_status;
		this.doc_lastdate = doc_lastdate;
		this.doc_reperence = doc_reperence;
	}
	
	public DocumentDto(int doc_mngno, int temp_no, int mem_no, String doc_deptnm, String doc_writer, String doc_title,
			String doc_content, String doc_regdate, String doc_no, String doc_status, String doc_lastdate,
			String doc_reperence, String doc_bcc, String doc_cooperation, String doc_conference, String app_status,
			String app_order) {
		super();
		this.doc_mngno = doc_mngno;
		this.temp_no = temp_no;
		this.mem_no = mem_no;
		this.doc_deptnm = doc_deptnm;
		this.doc_writer = doc_writer;
		this.doc_title = doc_title;
		this.doc_content = doc_content;
		this.doc_regdate = doc_regdate;
		this.doc_no = doc_no;
		this.doc_status = doc_status;
		this.doc_lastdate = doc_lastdate;
		this.doc_reperence = doc_reperence;
		this.doc_bcc = doc_bcc;
		this.doc_cooperation = doc_cooperation;
		this.doc_conference = doc_conference;
		this.app_status = app_status;
		this.app_order = app_order;
	}

	public int getDoc_mngno() {
		return doc_mngno;
	}

	public void setDoc_mngno(int doc_mngno) {
		this.doc_mngno = doc_mngno;
	}

	public int getTemp_no() {
		return temp_no;
	}

	public void setTemp_no(int temp_no) {
		this.temp_no = temp_no;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getDoc_deptnm() {
		return doc_deptnm;
	}

	public void setDoc_deptnm(String doc_deptnm) {
		this.doc_deptnm = doc_deptnm;
	}

	public String getDoc_write() {
		return doc_writer;
	}

	public void setDoc_write(String doc_write) {
		this.doc_writer = doc_write;
	}

	public String getDoc_title() {
		return doc_title;
	}

	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}

	public String getDoc_content() {
		return doc_content;
	}

	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}

	public String getDoc_regdate() {
		return doc_regdate;
	}

	public void setDoc_regdate(String doc_regdate) {
		this.doc_regdate = doc_regdate;
	}

	public String getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}

	public String getDoc_status() {
		return doc_status;
	}

	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}

	public String getDoc_lastdate() {
		return doc_lastdate;
	}

	public void setDoc_lastdate(String doc_lastdate) {
		this.doc_lastdate = doc_lastdate;
	}

	public String getDoc_reperence() {
		return doc_reperence;
	}

	public void setDoc_reperence(String doc_reperence) {
		this.doc_reperence = doc_reperence;
	}

	public String getDoc_bcc() {
		return doc_bcc;
	}

	public void setDoc_bcc(String doc_bcc) {
		this.doc_bcc = doc_bcc;
	}

	public String getDoc_cooperation() {
		return doc_cooperation;
	}

	public void setDoc_cooperation(String doc_cooperation) {
		this.doc_cooperation = doc_cooperation;
	}

	public String getDoc_conference() {
		return doc_conference;
	}

	public void setDoc_conference(String doc_conference) {
		this.doc_conference = doc_conference;
	}

	public String getDoc_writer() {
		return doc_writer;
	}

	public void setDoc_writer(String doc_writer) {
		this.doc_writer = doc_writer;
	}

	public String getApp_status() {
		return app_status;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}

	public String getApp_order() {
		return app_order;
	}

	public void setApp_order(String app_order) {
		this.app_order = app_order;
	}

	@Override
	public String toString() {
		return "DocumentDto [doc_mngno=" + doc_mngno + ", temp_no=" + temp_no + ", mem_no=" + mem_no + ", doc_deptnm="
				+ doc_deptnm + ", doc_writer=" + doc_writer + ", doc_title=" + doc_title + ", doc_content="
				+ doc_content + ", doc_regdate=" + doc_regdate + ", doc_no=" + doc_no + ", doc_status=" + doc_status
				+ ", doc_lastdate=" + doc_lastdate + ", doc_reperence=" + doc_reperence + ", doc_bcc=" + doc_bcc
				+ ", doc_cooperation=" + doc_cooperation + ", doc_conference=" + doc_conference + ", app_status="
				+ app_status + ", app_order=" + app_order + "]";
	}


}
