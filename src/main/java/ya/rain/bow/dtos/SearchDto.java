package ya.rain.bow.dtos;

public class SearchDto {
	
	private int intNum;			// 숫자 번호
	private String strNum;		// 스트링 번호
	private String[] name;		// 이름
	private String[] deptName;	// 부서명
	private String[] title;	// 제목
	private String[] content;	// 컨텐츠
	private String[] search;	// 제목 + 컨텐츠
	private String startDate;	// 시작
	private String endDate;	// 종료일
	
	private PagingDto paging;	// 페이징을 위한 DTO

	public SearchDto() {
		
	}
	
	public SearchDto(int intNum, PagingDto paging) {
		super();
		this.intNum = intNum;
		this.paging = paging;
	}

	public SearchDto(int intNum, String[] name, String[] deptName, String[] title,
			String[] content, String[] search) {
		super();
		this.intNum = intNum;
		this.name = name;
		this.deptName = deptName;
		this.title = title;
		this.content = content;
		this.search = search;
	}
	
	public void setPaging(PagingDto paging) {
		this.paging = paging;
	}

	public int getStart() {
		return paging.getStart();
	}
	public int getLast() {
		return paging.getLast();
	}
	
	public int getIntNum() {
		return intNum;
	}

	public void setIntNum(int intNum) {
		this.intNum = intNum;
	}

	public String getStrNum() {
		return strNum;
	}

	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}
	
	public String[] getDeptName() {
		return deptName;
	}

	public void setDeptName(String[] deptName) {
		this.deptName = deptName;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public String[] getSearch() {
		return search;
	}

	public void setSearch(String[] search) {
		this.search = search;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
