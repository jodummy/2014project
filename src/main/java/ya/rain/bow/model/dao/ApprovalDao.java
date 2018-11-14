package ya.rain.bow.model.dao;

import java.util.List;

import ya.rain.bow.dtos.ApprovalDto;
import ya.rain.bow.dtos.DocumentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.TemplateDto;

public interface ApprovalDao {
	public List<DocumentDto> selectListDocument(PagingDto pagingDto);
	public List<MemberDto> selectListMember(int mem_no);
	public int insertDocumentImsi(DocumentDto docDto);
	public DocumentDto selectMaxDocNo();
	public int insertDocumentApp(DocumentDto docDto);
	public int insertApprovalLine(ApprovalDto appDto);
	public List<DocumentDto> selectListDocImsi(PagingDto pagingDto);
	public List<DocumentDto> selectListDocMine(PagingDto pagingDto);
	public List<DocumentDto> selectListDoc(PagingDto pagingDto);
	public DocumentDto selectOneDocImsi(String doc_mngno);
	public List<DocumentDto> selectListDetailDoc(String doc_mngno);
	public int updateDocAppBan(String doc_mngno);
	public List<DocumentDto> selectListDocBan(PagingDto pagingDto);
	public int updateApprovalLine(ApprovalDto appDto1);
	public int deleteDocImsiAgain(String doc_mngno);
	public String selectOneDocNo(int doc_mngno);
	public int updateDocumentImsi(DocumentDto docDto);
	public int updateDocAppLineFinal(DocumentDto docDto);
	public List<DocumentDto> selectListDocWait(DocumentDto docDto);
	public int selectTotalAppPaging(int mem_no);
	public int selectTotalImsiPaging(int mem_no);
	public int selectTotalMinePaging(PagingDto pagingDto);
	public int selectTotalOtherPaging(PagingDto pagingDto);
	public int selectTotalDocBanPaging(PagingDto pagingDto);
	public int insertAppReply(ApprovalDto appDto);
	public int selectAppArNoMax();
	public List<ApprovalDto> selectListDocReply(String doc_mngno);
	public int deleteAppReply(ApprovalDto appDto);
	public List<DocumentDto> selectListDocMainImsi(int mem_no);
	public List<DocumentDto> selectListMainWait(DocumentDto docDto);
	public List<DocumentDto> selectListMainTotal(int mem_no);
	public List<DocumentDto> selectListMainIng(DocumentDto docDto);
	public TemplateDto selectTemplate(int temp_no);
}
