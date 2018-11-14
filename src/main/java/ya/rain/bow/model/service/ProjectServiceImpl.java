package ya.rain.bow.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ya.rain.bow.dtos.BookingResourceDto;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.dtos.ProjectSchduleDto;
import ya.rain.bow.model.dao.ProjectDao;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDao dao;
	
	// 프로젝트 생성
	@Override
	public boolean insertProject(ProjectDto prjDto) {
		return dao.insertProject(prjDto)==1 ? true : false;
	}
	
	// 프로젝트일정 생성
	@Override
	@Transactional(readOnly=true)
	public boolean insertProjectSchdule(List<ProjectSchduleDto> lists) {
		boolean check = false;
		dao.deleteProjectSchdule(lists.get(0).getPrj_no());
		for (ProjectSchduleDto prjsDto : lists) {
			check = dao.insertProjectSchdule(prjsDto)==1 ? true : false;
		}
		return check;
	}
	
	// 프로젝트 전체조회 클래스코드
	@Override
	public List<ProjectDto> selectListPrjClass(PagingDto pagingDto) {
		return dao.selectListPrjClass(pagingDto);
	}

	// 프로젝트 전체조회 팀코드
	@Override
	public List<ProjectDto> selectListPrjTeam(PagingDto pagingDto) {
		return dao.selectListPrjTeam(pagingDto);
	}
	
	// 프로젝트 전체조회 총갯수 클래스코드
	@Override
	public int selectOnePrjCntClass(PagingDto pagingDto) {
		return dao.selectOnePrjCntClass(pagingDto);
	}
	
	// 프로젝트 전체조회 총갯수 팀코드
	@Override
	public int selectOnePrjCntTeam(PagingDto pagingDto) {
		return dao.selectOnePrjCntTeam(pagingDto);
	}
	
	// 프로젝트일정 전체조회
	@Override
	public List<ProjectSchduleDto> selectListProjectSchdule(int prj_no) {
		return dao.selectListProjectSchdule(prj_no);
	}
	
	// 프로젝트 상세조회
	@Override
	public ProjectDto selectOneProject(int prj_no) {
		return dao.selectOneProject(prj_no);
	}
	
	// 프로젝트 delflag
	@Override
	@Transactional(readOnly=true)
	public boolean updateDelProject(String[] chkVal) {
		boolean check = false;
		for (String prj_no : chkVal) {
			check = dao.updateDelProject(Integer.parseInt(prj_no))!=0 ? true : false;
		}
		return check;
	}
	
	// 프로젝트일정 삭제
	@Override
	public boolean deleteProjectSchdule(int prj_no) {
		return dao.deleteProjectSchdule(prj_no) >=0 ? true : false;
	}
	
	// 프로젝트 수정
	@Override
	public boolean updateProject(ProjectDto prjDto) {
		return dao.updateProject(prjDto)!=0 ? true : false;
	}

	
	// 캘린더조회
	@Override
	public DepartmentDto selectOneSchdulecode(DepartmentDto deparDto) {
		if(deparDto.getDept_no().charAt(0) == 'C') {
			return dao.selectOneSchdulecodeClass(deparDto);
		}else {
			return dao.selectOneSchdulecodeTeam(deparDto);
		}
	}
	
	// 부서 전체조회 클래스
	@Override
	public List<DepartmentDto> selectListDepartmentClass() {
		return dao.selectListDepartmentClass();
	}

	// 캘린더등록
	@Override
	public boolean updateSchdulecode(DepartmentDto deparDto) {
		return dao.updateSchdulecode(deparDto)==1 ? true : false;
	}

	
	// 예약목록 code조회
	public BookingResourceDto selectOneBookingresource(int br_no) {
		return dao.selectOneBookingresource(br_no);
	}
	
	// 예약목록 전체조회
	@Override
	public List<BookingResourceDto> selectListBookingresource() {
		return dao.selectListBookingresource();
	}

	// 예약목록 등록
	@Override
	public boolean insertBookingresource(BookingResourceDto bkrDto) {
		return dao.insertBookingresource(bkrDto) == 1 ? true : false;
	}

	// 예약목록 수정
	@Override
	public boolean updateBookingresource(BookingResourceDto bkrDto) {
		return dao.updateBookingresource(bkrDto) == 1 ? true : false;
	}

	// 예약목록 삭제
	@Override
	public boolean deleteBookingresource(String[] br_nos) {
		boolean chk = false;
		for (String br_no : br_nos) {
			chk = dao.deleteBookingresource(Integer.parseInt(br_no)) != 0 ? true : false;
		}
		return chk;
	}
	
	// 예약목록 캘린더등록
	@Override
	public boolean updateBookingresourceCode(BookingResourceDto bkrDto) {
		return dao.updateBookingresourceCode(bkrDto) == 1 ? true : false;
	}
	
}
