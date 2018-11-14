package ya.rain.bow.model.service;

import java.util.List;

import ya.rain.bow.dtos.ApprovalDto;
import ya.rain.bow.dtos.DocumentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.TemplateDto;

public interface ApprovalService {
	// 로그인 session에 저장된 mem_no가 포함된 문서를 조회하는 함수
	public List<DocumentDto> selectListDocument(PagingDto pagingDto);
	// 회원 리스트를 출력하는 함수
	public List<MemberDto> selectListMember(int mem_no);
	// 임시저장 시 문서 데이터를 삽입해주는 함수
	public int insertDocumentImsi(DocumentDto docDto);
	// 현재 문서관리번호(doc_mngno)의 최대값을 조회하는 함수
	public DocumentDto selectMaxDocNo();
	// 결재상신 처리된 문서 데이터를 삽입하는 함수
	public int insertDocumentApp(DocumentDto docDto);
	// 결재상신된 문서의 결재라인을 삽입하는 함수 (doc_mngno로 연결한다.)
	public int insertApprovalLine(ApprovalDto appDto);
	// 임시리스트를 출력하기 위한 함수
	public List<DocumentDto> selectListDocImsi(PagingDto pagingDto);
	// 내가 작성한 문서들을 보기 위한 함수
	public List<DocumentDto> selectListDocMine(PagingDto pagingDto);
	// 내가 포함된, 다른 사람이 작성한 문서 리스트를 출력하기 위한 함수
	public List<DocumentDto> selectListDoc(PagingDto pagingDto);
	// 임시 저장 문서의 상세보기를 위한 함수
	public DocumentDto selectOneDocImsi(String doc_mngno);
	// 결재에 관련된 문서들의 상세보기를 위한 함수
	public List<DocumentDto> selectListDetailDoc(String doc_mngno);
	// 반려처리 doc_status=반려(4)로 변경해주는 함수
	public int updateDocAppBan(String doc_mngno);
	// doc_status=반려(4)를 조회하기 위한 함수
	public List<DocumentDto> selectListDocBan(PagingDto pagingDto);
	// 결재라인을 처리하면서 app_status를 변경해주기 위한 함수
	public int updateApprovalLine(ApprovalDto appDto1);
	// 임시저장문서를 다시 불러와서 '임시저장/결재상신'시 기존에 DB에 존재하던 문서를 지워준다.
	public int deleteDocImsiAgain(String doc_mngno);
	// 문서번호 생성
	public String makeDocNo(int temp_no, String cnt);
	// 이전 문서번호를 불러오는 함수(쿼리미완성)
	public String selectOneDocNo(int doc_mngno);
	// 임시 저장 문서를 다시 임시저장시 사용하는 함수
	public int updateDocumentImsi(DocumentDto docDto1);
	// DOCUMENT 최종 결재완료 처리하는 함수
	public int updateDocAppLineFinal(DocumentDto docDto);
	//  결재예정함에서 2단계에 결재되지 않은 것들을 제외하고 출력하기 위해 생성한 함수
	public List<DocumentDto> selectListDocWait(DocumentDto docDto);
	// 통합문서함  총게시물 수 Paging 쿼리
	public int selectTotalAppPaging(int mem_no);
	// 임시리스트를 총게시물 수 (Paging)
	public int selectTotalImsiPaging(int mem_no);
	// 나의 문서함 총게시물 수(Paging)
	public int selectTotalMinePaging(PagingDto pagingDto);
	// 다른 사람으로부터 받은 문서함 총게시물 수(Paging)
	public int selectTotalOtherPaging(PagingDto pagingDto);
	// 다른 사람으로부터 받은 문서 중 발려함 총게시물 수(Paging)
	public int selectTotalDocBanPaging(PagingDto pagingDto);
	// 댓글을 등록하는 함수
	public int insertAppReply(ApprovalDto appDto);
	// 댓글 최고 번호 확인하는 함수
	public int selectAppArNoMax();
	// 댓글 리스트 조회를 위한 함수
	public List<ApprovalDto> selectListDocReply(String doc_mngno);
	// 댓글 삭제 함수
	public int deleteAppReply(ApprovalDto appDto);
	// 전자결재 메인페이지 리스트 출력 함수
	public List<DocumentDto> selectListDocMainImsi(int mem_no);
	public List<DocumentDto> selectListMainWait(DocumentDto docDto);
	public List<DocumentDto> selectListMainTotal(int mem_no);
	public List<DocumentDto> selectListMainIng(DocumentDto docDto);
	// 템플릿 관리
	public TemplateDto selectTemplate(int temp_no);

}
