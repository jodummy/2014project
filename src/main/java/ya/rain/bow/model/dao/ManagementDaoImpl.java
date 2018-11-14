package ya.rain.bow.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;

@Repository
public class ManagementDaoImpl implements ManagementDao {

	private Logger logger = LoggerFactory.getLogger(ManagementDaoImpl.class);
	private final String NS = "ya.rain.bow.management.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MemberDto> selectListUser(PagingDto pagingDto) {
		return sqlSession.selectList(NS + "selectListUser", pagingDto);
	}

	@Override
	public int insertMemberUser(MemberDto memdto) {
		return sqlSession.insert(NS + "insertMemberUser", memdto);
	}

	@Override
	public int modyMemberUser(MemberDto memdto) {
		int n = sqlSession.update(NS + "modyMemberUser", memdto);
		return n;
	}

	@Override
	public MemberDto selectOneUser(String email) {
		MemberDto dto = sqlSession.selectOne(NS + "selectOneUser", email);
		return dto;
	}

	@Override
	public int delflagMemberUser(String email) {
		int n = sqlSession.update(NS + "delflagMemberUser", email);
		return n;
	}

	@Override
	public List<DepartmentDto> selectListDepart(PagingDto pagingDto) {
		List<DepartmentDto> list = sqlSession.selectList(NS + "selectListDepart", pagingDto);
		return list;
	}

	@Override
	public DepartmentDto selectOneDepart(DepartmentDto depardto) {
		return sqlSession.selectOne(NS + "selectOneDepart", depardto);
	}

	@Override
	public List<MemberDto> selectListTeamowe(Map<String, String> map) {
		return sqlSession.selectList(NS + "selectListTeamowe", map);
	}

	@Override
	public boolean delflagDepartTeam(String dept_no) {
		boolean isc = false;
		int n = sqlSession.update(NS + "delflagDepartTeam", dept_no);
		if (n == 1) {
			isc = true;
		}
		return isc;
	}

	@Override
	public List<DepartmentDto> selectListDepartmentClass(PagingDto pagingDto) {
		return sqlSession.selectList(NS + "selectListDepartmentClass", pagingDto);
	}

	@Override
	public List<DepartmentDto> selectListDepartmentClassOne(PagingDto pagingDto) {
		return sqlSession.selectList(NS + "selectListDepartmentClassOne", pagingDto);
	}

	@Override
	public int selectTotalPaging() {
		return sqlSession.selectOne(NS + "selectTotalPaging");
	}

	@Override
	public int selectTotalPagingTeam() {
		return sqlSession.selectOne(NS + "selectTotalPagingTeam");
	}

	@Override
	public int selectTotalPagingClass() {
		return sqlSession.selectOne(NS + "selectTotalPagingClass");
	}

	@Override
	public boolean insertDepart(DepartmentDto depardto) {
		return (sqlSession.insert(NS + "insertDepart", depardto) > 0) ? true : false;
	}

	@Override
	public boolean selectOnevalidityCheck(String mem_email) {
		return ((int) sqlSession.selectOne(NS + "selectOnevalidityCheck", mem_email) == 0) ? true : false;
	}

	@Override
	public int selectTotalPagingClassUser1(String dept_no) {
		return sqlSession.selectOne(NS + "selectTotalPagingClassUser1", dept_no);
	}

	@Override
	public int selectTotalPagingClassUser2(String dept_no) {
		return sqlSession.selectOne(NS + "selectTotalPagingClassUser2", dept_no);
	}

	@Override
	public List<MemberDto> selectListMember(String dept_no) {
		return sqlSession.selectList(NS + "selectListMember", dept_no);
	}

	@Override
	public boolean updateUserDepart(MemberDto memDto) {
		return (sqlSession.update(NS + "updateUserDepart", memDto) > 0) ? true : false;
	}

	@Override
	public List<MemberDto> selectListDeldepart(String dept_no) {
		return sqlSession.selectList(NS + "selectListDeldepart", dept_no);
	}

	@Override
	public MemberDto selectOnedepartUP(String dept_no) {
		return sqlSession.selectOne(NS + "selectOnedepartUP", dept_no);
	}

	@Override
	public boolean updateDepartUserDel(MemberDto memDto) {
		return (sqlSession.update(NS + "updateDepartUserDel", memDto) > 0) ? true : false;
	}

	@Override
	public List<MemberDto> selectListClassDel() {
		return sqlSession.selectList(NS + "selectListClassDel");
	}

	@Override
	public boolean updateClassCharge(String mem_email) {

		return sqlSession.update(NS + "updateClassCharge", mem_email) > 0 ? true : false;

	}

	@Override
	public boolean updateClassName(DepartmentDto depardto) {

		return sqlSession.update(NS + "updateClassName", depardto) > 0 ? true : false;

	}

	@Override
	public List<MemberDto> selectListClassMaster(String dept_no) {
		return sqlSession.selectList(NS + "selectListClassMaster", dept_no);
	}

	@Override
	public DepartmentDto selectOneTeamDetail(String dept_no) {

		return sqlSession.selectOne(NS + "selectOneTeamDetail", dept_no);
	}

	@Override
	public MemberDto selectOneMaster(String dept_no) {
		return sqlSession.selectOne(NS + "selectOneMaster", dept_no);
	}

	@Override
	public boolean updateTeamUserReader(MemberDto memDto) {
		return sqlSession.update(NS + "updateTeamUserReader", memDto) > 0 ? true : false;
	}

	@Override
	public int selectUserCountDepart(MemberDto memDto) {
		return sqlSession.selectOne(NS + "selectUserCountDepart", memDto);
	}

	@Override
	public List<MemberDto> selectListMemberClass(String dept_no) {
		return sqlSession.selectList(NS + "selectListMemberClass", dept_no);
	}

	@Override
	public List<MemberDto> selectListMemberDelClass(String dept_no) {
		return sqlSession.selectList(NS + "selectListMemberDelClass", dept_no);
	}

	@Override
	public boolean insertClassZero(String dept_no) {
		return sqlSession.insert(NS + "insertClassZero", dept_no) > 0 ? true : false;
	}

	@Override
	public boolean updateDelfalag(String dept_no) {
		return sqlSession.update(NS + "updateDelfalag", dept_no) > 0 ? true : false;
	}

	@Override
	public MemberDto selectOneMaster2(String dept_no) {
		return sqlSession.selectOne(NS + "selectOneMaster2", dept_no);
	}

}
