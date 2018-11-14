package ya.rain.bow.beans;

import java.util.List;

import ya.rain.bow.dtos.ProjectSchduleDto;

// term 리스트별 문자열을 하나의 문자열로 만들어줌
public class PercentCalculation {
	
	private List<ProjectSchduleDto> prjsLists;

	public PercentCalculation(List<ProjectSchduleDto> prjsLists) {
		this.prjsLists = prjsLists;
	}
	
	public String getTermLine() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < prjsLists.size(); i++) {
			sb.append(prjsLists.get(i).getPrjs_term());
		}
		return sb.toString();
	}
}
