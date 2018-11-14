package ya.rain.bow.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ya.rain.bow.dtos.ProjectSchduleDto;

//bar chart 그리기위해 항목, cnt에 담는다
public class ProjectSchduleBarChart {
	
	private List<ProjectSchduleDto> prjsLists;
	private List<ProjectSchduleDto> prjsBar;
	
	public ProjectSchduleBarChart(List<ProjectSchduleDto> prjsLists) {
		this.prjsLists = prjsLists;
		prjsBar = new ArrayList<ProjectSchduleDto>();
	}
	
	public List<ProjectSchduleDto> getBarChart() {
		projectSchduleItem();
		projectSchduleItemCnt();
		Collections.sort(prjsBar, new NoDescCompare());
		return prjsBar;
	}
	
	private class NoDescCompare implements Comparator<ProjectSchduleDto> {
		// 내림차순(DESC)
		@Override
		public int compare(ProjectSchduleDto arg0, ProjectSchduleDto arg1) {
			return arg0.getPrjs_cnt() > arg1.getPrjs_cnt() ? -1 : arg0.getPrjs_cnt() < arg1.getPrjs_cnt() ? 1:0;
		}
	}
	
	private void projectSchduleItem() {
		ProjectSchduleDto prjsDto = null;
		for(int i=0; i<prjsLists.size(); i++) {
			if(prjsLists.get(i).getPrjs_step() == 0) {
				prjsDto = new ProjectSchduleDto();
				prjsDto.setPrjs_item(prjsLists.get(i).getPrjs_item());
				prjsDto.setPrjs_refer(prjsLists.get(i).getPrjs_refer());
				prjsBar.add(prjsDto);
			}
		}
	}
	private void projectSchduleItemCnt() {
		for(int i=0; i<prjsBar.size(); i++) {
			prjsBar.get(i).setPrjs_cnt(tremItemCnt(prjsBar.get(i).getPrjs_refer()));
		}
	}
	private int tremItemCnt(int refer) {
		int cnt = 0;
		for(int i=0; i<prjsLists.size(); i++) {
			if(refer == prjsLists.get(i).getPrjs_refer()) {
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
}
