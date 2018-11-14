package ya.rain.bow.model.dao;

import java.util.List;

import ya.rain.bow.dtos.BookingResourceDto;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.dtos.ProjectSchduleDto;

public interface ProjectDao {
	
	// 프로젝트 생성
	public int insertProject(ProjectDto prjDto);
	
	// 프로젝트일정 생성
	public int insertProjectSchdule(ProjectSchduleDto prjsDto);
	
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
	public int updateDelProject(int prj_no);
	
	// 프로젝트일정 삭제
	public int deleteProjectSchdule(int prj_no);
	
	// 프로젝트 수정
	public int updateProject(ProjectDto prjDto);
	
	
	// 캘린더조회 클래스
	public DepartmentDto selectOneSchdulecodeClass(DepartmentDto deparDto);
	
	// 캘린더조회 팀
	public DepartmentDto selectOneSchdulecodeTeam(DepartmentDto deparDto);
	
	// 부서 전체조회 클래스
	public List<DepartmentDto> selectListDepartmentClass();
	
	// 캘린더등록
	public int updateSchdulecode(DepartmentDto deparDto);
	
	
	// 예약목록 code조회
	public BookingResourceDto selectOneBookingresource(int br_no);
	
	// 예약목록 전체조회
	public List<BookingResourceDto> selectListBookingresource();
	
	// 예약목록 등록
	public int insertBookingresource(BookingResourceDto bkrDto);
	
	// 예약목록 수정
	public int updateBookingresource(BookingResourceDto bkrDto);
	
	// 예약목록 삭제
	public int deleteBookingresource(int br_no);
	
	// 예약목록 캘린더등록
	public int updateBookingresourceCode(BookingResourceDto bkrDto);
}
