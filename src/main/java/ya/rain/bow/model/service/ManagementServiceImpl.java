package ya.rain.bow.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.model.dao.ManagementDao;

@Service
public class ManagementServiceImpl implements ManagementService {

	@Autowired
	private ManagementDao dao;

	// 회원 리스트
	@Override
	public List<MemberDto> selectListUser(PagingDto pagingDto) {
		return dao.selectListUser(pagingDto);
	}

	// 회원 가입
	@Override
	public int insertMemberUser(MemberDto memdto) {
		return dao.insertMemberUser(memdto);
	}

	// 회원수정
	@Override
	public int modyMemberUser(MemberDto memdto) {
		return dao.modyMemberUser(memdto);
	}

	// 회원 상세 조회
	@Override
	public MemberDto selectOneUser(String email) {
		return dao.selectOneUser(email);
	}

	// 회원 삭제 표시
	@Override
	public int delflagMemberUser(String email) {
		return dao.delflagMemberUser(email);
	}

	// 팀 조회
	@Override
	public List<DepartmentDto> selectListDepart(PagingDto pagingDto) {

		return dao.selectListDepart(pagingDto);
	}

	// 팀 detail 조회
	@Override
	public DepartmentDto selectOneDepart(DepartmentDto depardto) {
		return dao.selectOneDepart(depardto);
	}

	// 팀원 검색
	@Override
	public List<MemberDto> selectListTeamowe(Map<String, String> map) {
		return dao.selectListTeamowe(map);
	}

	// class list
	@Override
	public List<DepartmentDto> selectListDepartmentClass(PagingDto pagingDto) {

		return dao.selectListDepartmentClass(pagingDto);
	}

	// class 조회
	@Override
	public List<DepartmentDto> selectListDepartmentClassOne(PagingDto pagingDto) {

		return dao.selectListDepartmentClassOne(pagingDto);
	}

	@Override
	public boolean selectOnevalidityCheck(String mem_email) {

		return dao.selectOnevalidityCheck(mem_email);
	}

	// user 토탈
	@Override
	public int selectTotalPaging() {
		return dao.selectTotalPaging();
	}

	// team 토탈
	@Override
	public int selectTotalPagingTeam() {

		return dao.selectTotalPagingTeam();
	}

	// class 토탈
	@Override
	public int selectTotalPagingClass() {

		return dao.selectTotalPagingClass();
	}

	@Override
	public int selectTotalPagingClassUser(String dept_no) {
		int n = dao.selectTotalPagingClassUser1(dept_no);
		int n1 = dao.selectTotalPagingClassUser2(dept_no);

		return n + n1;
	}

	@Override
	public List<MemberDto> selectListMember(String dept_no) {
		List<MemberDto> lists = dao.selectListMember(dept_no);
		return lists;
	}

	@Override
	public boolean updateUserDepart(MemberDto memDto) {

		return dao.updateUserDepart(memDto);

	}

	@Override
	public List<MemberDto> selectListDeldepart(String dept_no) {
		return dao.selectListDeldepart(dept_no);
	}

	@Override
	public MemberDto selectOnedepartUP(String dept_no) {
		return dao.selectOnedepartUP(dept_no);
	}

	@Override
	public boolean updateDepartUserDel(MemberDto memDto) {

		return dao.updateDepartUserDel(memDto);
	}

	@Override
	public List<MemberDto> selectListClassDel() {
		return dao.selectListClassDel();
	}

	@Override
	public boolean insertDepart(DepartmentDto depardto) {
		return dao.insertDepart(depardto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateClassNC(DepartmentDto depardto, String mem_email) {
		boolean isc = false;
		if (dao.updateClassName(depardto) || dao.updateClassCharge(mem_email)) {
			isc = true;
		}
		return isc;
	}

	@Override
	public List<MemberDto> selectListClassMaster(String dept_no) {
		return dao.selectListClassMaster(dept_no);
	}

	@Override
	public DepartmentDto selectOneTeamDetail(String dept_no) {

		return dao.selectOneTeamDetail(dept_no);
	}

	@Override
	public MemberDto selectOneMaster(String dept_no) {

		return dao.selectOneMaster(dept_no);
	}

	@Override
	public boolean updateClassName(DepartmentDto depardto) {
		return dao.updateClassName(depardto);
	}

	@Override
	public boolean updateTeamUserReader(MemberDto memDto) {
		return dao.updateTeamUserReader(memDto);
	}

	@Override
	public int selectUserCountDepart(MemberDto memDto) {
		return dao.selectUserCountDepart(memDto);
	}

	@Override
	public List<MemberDto> selectListMemberClass(String dept_no) {

		return dao.selectListMemberClass(dept_no);
	}

	@Override
	public List<MemberDto> selectListMemberDelClass(String dept_no) {
		return dao.selectListMemberDelClass(dept_no);
	}

	@Override
	public boolean insertClassZero(String dept_no) {
		return dao.insertClassZero(dept_no);
	}

	@Override
	public boolean updateDelfalag(String dept_no) {
		return dao.updateDelfalag(dept_no);
		
	}

	@Override
	public MemberDto selectOneMaster2(String dept_no) {
		
		return dao.selectOneMaster2(dept_no);
	}

}
