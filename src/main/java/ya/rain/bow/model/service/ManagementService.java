package ya.rain.bow.model.service;

import java.util.List;
import java.util.Map;

import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;

public interface ManagementService {
	// 회원 리스트
	public List<MemberDto> selectListUser(PagingDto pagingDto);

	// class list
	public List<DepartmentDto> selectListDepartmentClass(PagingDto pagingDto);

	// 팀 리스트
	public List<DepartmentDto> selectListDepart(PagingDto pagingDto);

	// class 학생 리스트
	public List<DepartmentDto> selectListDepartmentClassOne(PagingDto pagingDto);

	// 토탈
	public int selectTotalPaging();

	// 부서 토탈
	public int selectTotalPagingTeam();

	// 클래스 토탈
	public int selectTotalPagingClass();

	// 학생 토탈
	public int selectTotalPagingClassUser(String dept_no);

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

	// 팀 detail 01 위랑 다릅니다
	public DepartmentDto selectOneTeamDetail(String dept_no);

	// 팀장 검색 01 겁색 해놔라
	public MemberDto selectOneMaster(String dept_no);

	// 팀원 검색
	public List<MemberDto> selectListTeamowe(Map<String, String> map);

	// 유효성 체크
	public boolean selectOnevalidityCheck(String mem_email);

	// 기능 리스트 뿌려주기(수정이 필요합니다)
	public List<MemberDto> selectListMember(String dept_no);

	// 팀원을 부여한다
	public boolean updateUserDepart(MemberDto memDto);

	// 삭제시킬 멤버들 불러오기
	public List<MemberDto> selectListDeldepart(String dept_no);

	// reUpdate 수정하기 클래스로 다시 돌아가기
	public boolean updateDepartUserDel(MemberDto memDto);

	// 팀원 수정 하기 위해 불러오기
	public MemberDto selectOnedepartUP(String dept_no);

	// 삭제후 클래스 된돌려주기
	public List<MemberDto> selectListClassDel();

	// 클래스/팀 생성
	public boolean insertDepart(DepartmentDto depardto);

	// 클래스 생성시 임의적 사람 넣기 아무것도 없는 사람
	public boolean insertClassZero(String dept_no);

	// 클래스 수정
	public boolean updateClassNC(DepartmentDto depardto, String mem_email);

	// 바꿀 사람들의 정보를 전부 가져온다
	public List<MemberDto> selectListClassMaster(String dept_no);

	public boolean updateTeamUserReader(MemberDto memDto);

	public boolean updateClassName(DepartmentDto depardto);

	public int selectUserCountDepart(MemberDto memDto);

	public List<MemberDto> selectListMemberClass(String dept_no);

	public List<MemberDto> selectListMemberDelClass(String dept_no);

	public boolean updateDelfalag(String dept_no);

	public MemberDto selectOneMaster2(String dept_no);

}
