package ya.rain.bow.beans;

import java.util.ArrayList;
import java.util.List;

import ya.rain.bow.dtos.ProjectSchduleDto;

// pie chart 그리기위해 이름, cnt에 담는다
public class ProjectSchdulePieChart {
	
	private List<ProjectSchduleDto> prjsLists;
	private List<ProjectSchduleDto> prjsPie;

	public ProjectSchdulePieChart(List<ProjectSchduleDto> prjsLists) {
		this.prjsLists = prjsLists;
		prjsPie = new ArrayList<ProjectSchduleDto>();
	}
	
	public List<ProjectSchduleDto> getPieChart() {
		projectSchduleName();
		projectSchduleNameCnt();
		return prjsPie;
	}
	
	private void projectSchduleNameCnt() {
		for(int i=0; i<prjsPie.size(); i++) {
			prjsPie.get(i).setPrjs_cnt(tremNameCnt(prjsPie.get(i).getPrjs_name()));
		}
	}
	
	private int tremNameCnt(String name) {
		int cnt = 0;
		for(int i=0; i<prjsLists.size(); i++) {
			if(name.equals(prjsLists.get(i).getPrjs_name().trim())) {
				cnt += tremCnt(prjsLists.get(i).getPrjs_term());
			}
		}
		return cnt;
	}
	private int tremCnt(String term) {
		int cnt = 0;
		for(int i=0; i<term.length(); i++) {
			if(term.charAt(i) == '1') {
				cnt++;
			}
		}
		return cnt;
	}
	private void projectSchduleName() {
		ProjectSchduleDto prjsDto = null;
		for(int i=0; i<prjsLists.size(); i++) {
			if(!isSameCheck(prjsLists.get(i).getPrjs_name().trim()) && !(prjsLists.get(i).getPrjs_name().trim().equals(""))) {
				prjsDto = new ProjectSchduleDto();
				prjsDto.setPrjs_name(prjsLists.get(i).getPrjs_name().trim());
				prjsPie.add(prjsDto);
			}
		}
	}
	private boolean isSameCheck(String name) {
		boolean chk = false;
		for(int i=0; i<prjsPie.size(); i++) {
			if(prjsPie.get(i).getPrjs_name().equals(name)) {
				chk = true;
			}
		}
		return chk;
	}
	
}
