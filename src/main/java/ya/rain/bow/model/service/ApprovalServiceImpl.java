package ya.rain.bow.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ya.rain.bow.dtos.ApprovalDto;
import ya.rain.bow.dtos.DocumentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.TemplateDto;
import ya.rain.bow.model.dao.ApprovalDao;

@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao dao;

	@Override
	public List<DocumentDto> selectListDocument(PagingDto pagingDto) {
		return dao.selectListDocument(pagingDto);
	}

	@Override
	public List<MemberDto> selectListMember(int mem_no) {
		return dao.selectListMember(mem_no);
	}

	@Override
	public int insertDocumentImsi(DocumentDto docDto) {
		return dao.insertDocumentImsi(docDto);
	}

	@Override
	public DocumentDto selectMaxDocNo() {
		return dao.selectMaxDocNo();
	}

	@Override
	public int insertDocumentApp(DocumentDto docDto) {
		// TODO Auto-generated method stub
		return dao.insertDocumentApp(docDto);
	}

	@Override
	public int insertApprovalLine(ApprovalDto appDto) {
		// TODO Auto-generated method stub
		return dao.insertApprovalLine(appDto);
	}

	@Override
	public List<DocumentDto> selectListDocImsi(PagingDto pagingDto) {
		// TODO Auto-generated method stub
		return dao.selectListDocImsi(pagingDto);
	}

	@Override
	public List<DocumentDto> selectListDocMine(PagingDto pagingDto) {
		// TODO Auto-generated method stub
		return dao.selectListDocMine(pagingDto);
	}

	@Override
	public List<DocumentDto> selectListDoc(PagingDto pagingDto){
		// TODO Auto-generated method stub
		return dao.selectListDoc(pagingDto);
	}

	@Override
	public DocumentDto selectOneDocImsi(String doc_mngno) {
		// TODO Auto-generated method stub
		return dao.selectOneDocImsi(doc_mngno);
	}

	@Override
	public List<DocumentDto> selectListDetailDoc(String doc_mngno) {
		// TODO Auto-generated method stub
		return dao.selectListDetailDoc(doc_mngno);
	}

	@Override
	public int updateDocAppBan(String doc_mngno) {
		// TODO Auto-generated method stub
		return dao.updateDocAppBan(doc_mngno);
	}

	@Override
	public List<DocumentDto> selectListDocBan(PagingDto pagingDto) {
		// TODO Auto-generated method stub
		return dao.selectListDocBan(pagingDto);
	}

	@Override
	public int updateApprovalLine(ApprovalDto appDto1) {
		// TODO Auto-generated method stub
		return dao.updateApprovalLine(appDto1);
	}

	@Override
	public int deleteDocImsiAgain(String doc_mngno) {
		return dao.deleteDocImsiAgain(doc_mngno);
	}

	@Override
	public String makeDocNo(int temp_no, String cnt) {
		SimpleDateFormat  formatter = new SimpleDateFormat("yyyyMMdd");
		String today =  formatter.format(new Date());
		String temp = "";
		
		switch(temp_no) {
		case 1:
			temp = "G";
		case 2:
			temp = "V";
		case 3:
			temp = "P";
		case 4:
			temp = "E";
		default:
			break;
		}
		String doc_no=temp+today+"_"+cnt;
		
		return doc_no;
	}

	@Override
	public String selectOneDocNo(int doc_mngno) {
		return dao.selectOneDocNo(doc_mngno);
	}

	@Override
	public int updateDocumentImsi(DocumentDto docDto) {
		return dao.updateDocumentImsi(docDto);
	}

	@Override
	public int updateDocAppLineFinal(DocumentDto docDto) {
		return dao.updateDocAppLineFinal(docDto);
	}

	@Override
	public List<DocumentDto> selectListDocWait(DocumentDto docDto) {
		return dao.selectListDocWait(docDto);
	}

	@Override
	public int selectTotalAppPaging(int mem_no) {
		return dao.selectTotalAppPaging(mem_no);
	}

	@Override
	public int selectTotalImsiPaging(int mem_no) {
		return dao.selectTotalImsiPaging(mem_no);
	}

	@Override
	public int selectTotalMinePaging(PagingDto pagingDto) {
		return dao.selectTotalMinePaging(pagingDto);
	}

	@Override
	public int selectTotalOtherPaging(PagingDto pagingDto) {
		return dao.selectTotalOtherPaging(pagingDto);
	}

	@Override
	public int selectTotalDocBanPaging(PagingDto pagingDto) {
		return dao.selectTotalDocBanPaging(pagingDto);
	}

	@Override
	public int insertAppReply(ApprovalDto appDto) {
		return dao.insertAppReply(appDto);
	}

	@Override
	public int selectAppArNoMax() {
		return dao.selectAppArNoMax();
	}

	@Override
	public List<ApprovalDto> selectListDocReply(String doc_mngno) {
		return dao.selectListDocReply(doc_mngno);
	}

	@Override
	public int deleteAppReply(ApprovalDto appDto) {
		return dao.deleteAppReply(appDto);
	}

	@Override
	public List<DocumentDto> selectListDocMainImsi(int mem_no) {
		return dao.selectListDocMainImsi(mem_no);
	}

	@Override
	public List<DocumentDto> selectListMainWait(DocumentDto docDto) {
		return dao.selectListMainWait(docDto);
	}

	@Override
	public List<DocumentDto> selectListMainTotal(int mem_no) {
		return dao.selectListMainTotal(mem_no);
	}

	@Override
	public List<DocumentDto> selectListMainIng(DocumentDto docDto) {
		return dao.selectListMainIng(docDto);
	}

	@Override
	public TemplateDto selectTemplate(int temp_no) {
		return dao.selectTemplate(temp_no);
	}

}
