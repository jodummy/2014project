package ya.rain.bow.model.service;

import java.util.List;

import ya.rain.bow.dtos.BookingResourceDto;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.dtos.ProjectSchduleDto;

public interface ProjectService {
	
	// 프로젝트 생성
	public boolean insertProject(ProjectDto prjDto);
	
	// 프로젝트일정 생성
	public boolean insertProjectSchdule(List<ProjectSchduleDto> lists);
	
	// 프로젝트 전체조회 클래스코드
	public List<ProjectDto> selectListPrjClass(PagingDto pagingDto);

	// 프로젝트 전체조회 팀코드
	public List<ProjectDto> selectListPrjTeam(PagingDto pagingDto);
	
	// 프로젝트 전체조회 총갯수 클래스코드
	public int selectOnePrjCntClass(PagingDto pagingDto);
	
	// 프로젝트 전체조회 총갯수 팀코드
	public int selectOnePrjCntTeam(PagingDto pagingDto);
	
	// 프로젝트일정 전체조회
	public List<ProjectSchduleDto> selectListProjectSchdule(int prj_no);
	
	// 프로젝트 상세조회
	public ProjectDto selectOneProject(int prj_no);
	
	// 프로젝트 delflag
	public boolean updateDelProject(String[] chkVal);
	
	// 프로젝트일정 삭제
	public boolean deleteProjectSchdule(int prj_no);
	
	// 프로젝트 수정
	public boolean updateProject(ProjectDto prjDto);
	
	
	// 캘린더조회
	public DepartmentDto selectOneSchdulecode(DepartmentDto deparDto);
	
	// 부서 전체조회 클래스
	public List<DepartmentDto> selectListDepartmentClass();
	
	// 캘린더등록
	public boolean updateSchdulecode(DepartmentDto deparDto);
	
	
	// 예약목록 code조회
	public BookingResourceDto selectOneBookingresource(int br_no);
	
	// 예약목록 전체조회
	public List<BookingResourceDto> selectListBookingresource();
	
	// 예약목록 등록
	public boolean insertBookingresource(BookingResourceDto bkrDto);
	
	// 예약목록 수정
	public boolean updateBookingresource(BookingResourceDto bkrDto);
	
	// 예약목록 삭제
	public boolean deleteBookingresource(String[] br_nos);

	// 예약목록 캘린더등록
	public boolean updateBookingresourceCode(BookingResourceDto bkrDto);
	
}
