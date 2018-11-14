package ya.rain.bow.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ya.rain.bow.dtos.ApprovalDto;
import ya.rain.bow.dtos.DocumentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.TemplateDto;

@Repository
public class ApprovalDaoImpl implements ApprovalDao {
	
	private Logger logger = LoggerFactory.getLogger(ApprovalDaoImpl.class);
	private final String NS = "ya.rain.bow.approval.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<DocumentDto> selectListDocument(PagingDto pagingDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDocument", pagingDto);
		return lists;
	}
	
	@Override
	public List<MemberDto> selectListMember(int mem_no) {
		List<MemberDto> lists = sqlSession.selectList(NS + "selectListMember", mem_no);
		return lists;
	}

	@Override
	public int insertDocumentImsi(DocumentDto docDto) {
		int rst = sqlSession.insert(NS+"insertDocumentImsi", docDto);
		return rst;
	}

	@Override
	public DocumentDto selectMaxDocNo() {
		DocumentDto docDto = sqlSession.selectOne(NS+"selectMaxDocNo");
		return docDto;
	}

	@Override
	public int insertDocumentApp(DocumentDto docDto) {
		int rst = sqlSession.insert(NS+"insertDocumentApp", docDto);
		return rst;
	}

	@Override
	public int insertApprovalLine(ApprovalDto appDto) {
		int rst = sqlSession.insert(NS+"insertApprovalLine", appDto);
		return rst;
	}

	@Override
	public List<DocumentDto> selectListDocImsi(PagingDto pagingDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDocImsi", pagingDto);
		return lists;
	}

	@Override
	public List<DocumentDto> selectListDocMine(PagingDto pagingDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDocMine", pagingDto);
		return lists;
	}

	@Override
	public List<DocumentDto> selectListDoc(PagingDto pagingDto){
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDoc", pagingDto);
		return lists;
	}

	@Override
	public DocumentDto selectOneDocImsi(String doc_mngno) {
		DocumentDto imsidocDto = sqlSession.selectOne(NS + "selectOneDocImsi", doc_mngno);
		return imsidocDto;
	}

	@Override
	public List<DocumentDto> selectListDetailDoc(String doc_mngno) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDetailDoc", doc_mngno);
		return lists;
	}

	@Override
	public int updateDocAppBan(String doc_mngno) {
		int n = sqlSession.update(NS + "updateDocAppBan", doc_mngno);
		return n;
	}

	@Override
	public List<DocumentDto> selectListDocBan(PagingDto pagingDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDocBan", pagingDto);
		return lists;
	}

	@Override
	public int updateApprovalLine(ApprovalDto appDto) {
		int n = sqlSession.update(NS + "updateApprovalLine", appDto);
		return n;
	}

	@Override
	public int deleteDocImsiAgain(String doc_mngno) {
		int n = sqlSession.delete(NS + "deleteDocImsiAgain", doc_mngno);
		return n;
	}

	@Override
	public String selectOneDocNo(int doc_mngno) {
		String doc_no = sqlSession.selectOne(NS + "selectOneDocNo", doc_mngno);
		return doc_no;
	}

	@Override
	public int updateDocumentImsi(DocumentDto docDto) {
		int n = sqlSession.update(NS + "updateDocumentImsi", docDto);
		return n;
	}

	@Override
	public int updateDocAppLineFinal(DocumentDto docDto) {
		int n = sqlSession.update(NS + "updateDocAppLineFinal", docDto);
		return n;
	}

	@Override
	public List<DocumentDto> selectListDocWait(DocumentDto docDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDocWait", docDto);
		return lists;
	}

	@Override
	public int selectTotalAppPaging(int mem_no) {
		int n = sqlSession.selectOne(NS + "selectTotalAppPaging", mem_no);
		return n;
	}

	@Override
	public int selectTotalImsiPaging(int mem_no) {
		int n = sqlSession.selectOne(NS + "selectTotalImsiPaging", mem_no);
		return n;
	}

	@Override
	public int selectTotalMinePaging(PagingDto pagingDto) {
		int n = sqlSession.selectOne(NS + "selectTotalMinePaging", pagingDto);
		return n;
	}

	@Override
	public int selectTotalOtherPaging(PagingDto pagingDto) {
		int n = sqlSession.selectOne(NS + "selectTotalOtherPaging", pagingDto);
		return n;
	}

	@Override
	public int selectTotalDocBanPaging(PagingDto pagingDto) {
		int n = sqlSession.selectOne(NS + "selectTotalDocBanPaging", pagingDto);
		return n;
	}

	@Override
	public int insertAppReply(ApprovalDto appDto) {
		int rst = sqlSession.insert(NS+"insertAppReply", appDto);
		return rst;
	}

	@Override
	public int selectAppArNoMax() {
		int rst = sqlSession.selectOne(NS+"selectAppArNoMax");
		return rst;
	}

	@Override
	public List<ApprovalDto> selectListDocReply(String doc_mngno) {
		List<ApprovalDto> lists = sqlSession.selectList(NS + "selectListDocReply", doc_mngno);
		return lists;
	}

	@Override
	public int deleteAppReply(ApprovalDto appDto) {
		int rst = sqlSession.delete(NS + "deleteAppReply", appDto);
		return rst;
	}

	@Override
	public List<DocumentDto> selectListDocMainImsi(int mem_no) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListDocMainImsi", mem_no);
		return lists;
	}

	@Override
	public List<DocumentDto> selectListMainWait(DocumentDto docDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListMainWait", docDto);
		return lists;
	}

	@Override
	public List<DocumentDto> selectListMainTotal(int mem_no) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListMainTotal", mem_no);
		return lists;
	}

	@Override
	public List<DocumentDto> selectListMainIng(DocumentDto docDto) {
		List<DocumentDto> lists = sqlSession.selectList(NS + "selectListMainIng", docDto);
		return lists;
	}

	@Override
	public TemplateDto selectTemplate(int temp_no) {
		TemplateDto imsidocDto = sqlSession.selectOne(NS + "selectTemplate", temp_no);
		return imsidocDto;
	}
	
}
