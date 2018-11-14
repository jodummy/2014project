package ya.rain.bow.model.dao;

import java.util.List;
import java.util.Map;

import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;

public interface ManagementDao {
	// 회원 리스트
	public List<MemberDto> selectListUser(PagingDto pagingDto);

	// class list
	public List<DepartmentDto> selectListDepartmentClass(PagingDto pagingDto);

	// 팀 조회
	public List<DepartmentDto> selectListDepart(PagingDto pagingDto);

	// class 조회
	public List<DepartmentDto> selectListDepartmentClassOne(PagingDto pagingDto);

	// 회원 가입
	public int insertMemberUser(MemberDto memdto);

	// 회원수정
	public int modyMemberUser(MemberDto memdto);

	// 회원 상세 조회
	public MemberDto selectOneUser(String email);

	// 회원 삭제 표시
	public int delflagMemberUser(String email);

	// 팀 detail 조회
	public DepartmentDto selectOneDepart(DepartmentDto depardto);

	// 팀원 검색
	public List<MemberDto> selectListTeamowe(Map<String, String> map);

	// 팀 삭제
	public boolean delflagDepartTeam(String dept_no);

	// 토탈
	public int selectTotalPaging();

	// 부서 토탈
	public int selectTotalPagingTeam();

	// class 토탈
	public int selectTotalPagingClass();

	// 클래스 토탈(소속)
	public int selectTotalPagingClassUser1(String dept_no);

	public int selectTotalPagingClassUser2(String dept_no);

	// 팀생성시 팀 만들기
	public boolean insertDepart(DepartmentDto depardto);

	// 유효성 체크
	public boolean selectOnevalidityCheck(String mem_email);

	public List<MemberDto> selectListMember(String dept_no);

	public boolean updateUserDepart(MemberDto memDto);

	// 삭제 시킬 멤버들
	public List<MemberDto> selectListDeldepart(String dept_no);

	public MemberDto selectOnedepartUP(String dept_no);

	public boolean updateDepartUserDel(MemberDto memDto);

	public List<MemberDto> selectListClassDel();

	public boolean updateClassCharge(String mem_email);

	public boolean updateClassName(DepartmentDto depardto);

	// class 바꿀 수 있는 사람들의 모임
	public List<MemberDto> selectListClassMaster(String dept_no);

	public DepartmentDto selectOneTeamDetail(String dept_no);

	public MemberDto selectOneMaster(String dept_no);

	public boolean updateTeamUserReader(MemberDto memDto);

	public int selectUserCountDepart(MemberDto memDto);

	public List<MemberDto> selectListMemberClass(String dept_no);

	public List<MemberDto> selectListMemberDelClass(String dept_no);

	public boolean insertClassZero(String dept_no);

	public boolean updateDelfalag(String dept_no);

	public MemberDto selectOneMaster2(String dept_no);
}
