package ya.rain.bow.dtos;

public class TemplateDto {

	private int temp_no;
	private String temp_name;
	private String temp_content;
	private String code;

	public TemplateDto() {
		super();
	}

	public TemplateDto(int temp_no, String temp_name, String temp_content,
			String code) {
		super();
		this.temp_no = temp_no;
		this.temp_name = temp_name;
		this.temp_content = temp_content;
		this.code = code;
	}

	public int getTemp_no() {
		return temp_no;
	}

	public void setTemp_no(int temp_no) {
		this.temp_no = temp_no;
	}

	public String getTemp_name() {
		return temp_name;
	}

	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}

	public String getTemp_content() {
		return temp_content;
	}

	public void setTemp_content(String temp_content) {
		this.temp_content = temp_content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "templateDto [temp_no=" + temp_no + ", temp_name=" + temp_name
				+ ", temp_content=" + temp_content + ", code=" + code + "]";
	}

}
