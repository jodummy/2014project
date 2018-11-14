package ya.rain.bow.model.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ya.rain.bow.dtos.BookingResourceDto;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.dtos.ProjectSchduleDto;

@Repository
public class ProjectDaoImpl implements ProjectDao {
	
	private Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);
	private final String NS = "ya.rain.bow.project.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 프로젝트 생성
	@Override
	public int insertProject(ProjectDto prjDto) {
		logger.info("insertProject : " + new Date());
		return sqlSession.insert(NS+"insertProject", prjDto);
	}
	
	// 프로젝트일정 생성
	@Override
	public int insertProjectSchdule(ProjectSchduleDto prjsDto) {
		logger.info("insertProjectSchdule : " + new Date());
		return sqlSession.insert(NS+"insertProjectSchdule", prjsDto);
	}
	
	// 프로젝트 전체조회 클래스코드
	@Override
	public List<ProjectDto> selectListPrjClass(PagingDto pagingDto) {
		logger.info("selectListPrjClass : " + new Date());
		return sqlSession.selectList(NS+"selectListPrjClass", pagingDto);
	}

	// 프로젝트 전체조회 팀코드
	@Override
	public List<ProjectDto> selectListPrjTeam(PagingDto pagingDto) {
		logger.info("selectListPrjTeam : " + new Date());
		return sqlSession.selectList(NS+"selectListPrjTeam", pagingDto);
	}
	
	// 프로젝트 전체조회 총갯수 클래스코드
	@Override
	public int selectOnePrjCntClass(PagingDto pagingDto) {
		logger.info("selectOnePrjCntClass : " + new Date());
		return sqlSession.selectOne(NS+"selectOnePrjCntClass", pagingDto);
	}
	
	// 프로젝트 전체조회 총갯수 팀코드
	@Override
	public int selectOnePrjCntTeam(PagingDto pagingDto) {
		logger.info("selectOnePrjCntTeam : " + new Date());
		return sqlSession.selectOne(NS+"selectOnePrjCntTeam", pagingDto);
	}
	
	// 프로젝트일정 전체조회
	@Override
	public List<ProjectSchduleDto> selectListProjectSchdule(int prj_no) {
		logger.info("selectListProjectSchdule : " + new Date());
		return sqlSession.selectList(NS+"selectListProjectSchdule", prj_no);
	}
	
	// 프로젝트 상세조회
	@Override
	public ProjectDto selectOneProject(int prj_no) {
		logger.info("selectOneProject : " + new Date());
		return sqlSession.selectOne(NS+"selectOneProject", prj_no);
	}
	
	// 프로젝트 delflag
	@Override
	public int updateDelProject(int prj_no) {
		logger.info("updateDelProject : " + new Date());
		return sqlSession.update(NS+"updateDelProject", prj_no);
	}
	
	// 프로젝트일정 삭제
	@Override
	public int deleteProjectSchdule(int prj_no) {
		logger.info("deleteProjectSchdule : " + new Date());
		return sqlSession.delete(NS+"deleteProjectSchdule", prj_no);
	}

	// 프로젝트 수정
	@Override
	public int updateProject(ProjectDto prjDto) {
		logger.info("updateProject : " + new Date());
		return sqlSession.update(NS+"updateProject", prjDto);
	}
	
	
	// 캘린더조회 클래스
	@Override
	public DepartmentDto selectOneSchdulecodeClass(DepartmentDto deparDto) {
		logger.info("selectOneSchdulecodeClass : " + new Date());
		return sqlSession.selectOne(NS+"selectOneSchdulecodeClass", deparDto);
	}
	
	// 캘린더조회 팀
	@Override
	public DepartmentDto selectOneSchdulecodeTeam(DepartmentDto deparDto) {
		logger.info("selectOneSchdulecodeTeam : " + new Date());
		return sqlSession.selectOne(NS+"selectOneSchdulecodeTeam", deparDto);
	}
	
	// 부서 전체조회 클래스
	@Override
	public List<DepartmentDto> selectListDepartmentClass() {
		logger.info("selectListDepartmentClass : " + new Date());
		return sqlSession.selectList(NS+"selectListDepartmentClass");
	}
	
	// 캘린더등록
	@Override
	public int updateSchdulecode(DepartmentDto deparDto) {
		logger.info("updateSchdulecode : " + new Date());
		return sqlSession.update(NS+"updateSchdulecode", deparDto);
	}
	
	
	// 예약목록 code조회
	public BookingResourceDto selectOneBookingresource(int br_no) {
		logger.info("selectOneBookingresource : " + new Date());
		return sqlSession.selectOne(NS+"selectOneBookingresource", br_no);		
	}
	
	// 예약목록 전체조회
	@Override
	public List<BookingResourceDto> selectListBookingresource() {
		logger.info("selectListBookingresource : " + new Date());
		return sqlSession.selectList(NS+"selectListBookingresource");
	}
	
	// 예약목록 등록
	@Override
	public int insertBookingresource(BookingResourceDto bkrDto) {
		logger.info("insertBookingresource : " + new Date());
		return sqlSession.insert(NS+"insertBookingresource", bkrDto);
	}

	// 예약목록 수정
	@Override
	public int updateBookingresource(BookingResourceDto bkrDto) {
		logger.info("updateBookingresource : " + new Date());
		return sqlSession.update(NS+"updateBookingresource", bkrDto);
	}

	// 예약목록 삭제
	@Override
	public int deleteBookingresource(int br_no) {
		logger.info("deleteBookingresource : " + new Date());
		return sqlSession.delete(NS+"deleteBookingresource", br_no);
	}
	
	// 예약목록 캘린더등록
	@Override
	public int updateBookingresourceCode(BookingResourceDto bkrDto) {
		logger.info("updateBookingresourceCode : " + new Date());
		return sqlSession.update(NS+"updateBookingresourceCode", bkrDto);
	}
	
}
