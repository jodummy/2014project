package ya.rain.bow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.TempFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ya.rain.bow.dtos.ApprovalDto;
import ya.rain.bow.dtos.DocumentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.TemplateDto;
import ya.rain.bow.model.service.ApprovalService;

@Controller
public class ApprovalController {

	private Logger logger = LoggerFactory.getLogger(ApprovalController.class);

	@Autowired
	private ApprovalService appService;

	/*
	 * 첫 접속 테스트
	 */
	@RequestMapping(value = "/appmain.do", method = RequestMethod.GET)
	public String appmain(Model model, HttpSession session) {	
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");
		int mem_no = memDto.getMem_no();
		
		// 임시문서함
		List<DocumentDto> lists = appService.selectListDocMainImsi(mem_no);
		System.err.println(lists);
		model.addAttribute("imsilist", lists);
		
		// 결재예정함
		DocumentDto docDto = new DocumentDto();
		docDto.setMem_no(mem_no);
		docDto.setApp_status("1");
		docDto.setDoc_status("2");
		List<DocumentDto> waitlist = appService.selectListMainWait(docDto);
		System.err.println(waitlist);
		model.addAttribute("waitlist", waitlist);
		
		// 나의문서함 결재진행함
		docDto.setDoc_status("2");

		List<DocumentDto> inglist = appService.selectListMainIng(docDto);
		model.addAttribute("inglist", inglist);
		
		// 통합문서함
		List<DocumentDto> doctotallist = appService.selectListMainTotal(mem_no);
		System.err.println(doctotallist);
		model.addAttribute("totallist", doctotallist);
		return "approval/appmain";
	}

	/*
	 * 문서작성 클릭 시 템플릿 선택 화면
	 */
	@RequestMapping(value = "/docwrite00.do", method = RequestMethod.GET)
	public String docwrite00() {
		return "approval/doc_write_00";
	}

	/*
	 * 문서 작성 중 '1 기안서' 템플릿
	 */
	@RequestMapping(value = "/docwritegian01.do", method = RequestMethod.GET)
	public String docwritegian01(Model model, HttpServletRequest req) {
		DocumentDto docDto = appService.selectMaxDocNo();
		int doc_mngno = docDto.getDoc_mngno() + 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());

		int temp_no = Integer.parseInt(req.getParameter("temp_no"));
		TemplateDto tempDto = appService.selectTemplate(temp_no);
		
		model.addAttribute("tempDto", tempDto);
		model.addAttribute("doc_mngno", doc_mngno);
		model.addAttribute("temp_no", temp_no);
		model.addAttribute("today", today);
		return "approval/doc_write_gian_01";
	}

	/*
	 * 문서 작성 중 '2 휴가원' 템플릿
	 */
	@RequestMapping(value = "/docwritev02.do", method = RequestMethod.GET)
	public String docwritev02(Model model, HttpServletRequest req) {
		DocumentDto docDto = appService.selectMaxDocNo();
		int doc_mngno = docDto.getDoc_mngno() + 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());

		int temp_no = Integer.parseInt(req.getParameter("temp_no"));
		TemplateDto tempDto = appService.selectTemplate(temp_no);
		
		model.addAttribute("tempDto", tempDto);
		model.addAttribute("doc_mngno", doc_mngno);
		model.addAttribute("temp_no", temp_no);
		model.addAttribute("today", today);
		return "approval/doc_write_v_02";
	}

	/*
	 * 문서 작성 중 '3 지출결의서' 템플릿
	 */
	@RequestMapping(value = "/docwritepay03.do", method = RequestMethod.GET)
	public String docwritepay03(Model model, HttpServletRequest req) {
		DocumentDto docDto = appService.selectMaxDocNo();
		int doc_mngno = docDto.getDoc_mngno() + 1;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());

		int temp_no = Integer.parseInt(req.getParameter("temp_no"));
		TemplateDto tempDto = appService.selectTemplate(temp_no);
		
		model.addAttribute("tempDto", tempDto);
		model.addAttribute("doc_mngno", doc_mngno);
		model.addAttribute("temp_no", temp_no);
		model.addAttribute("today", today);
		return "approval/doc_write_pay_03";
	}

	/*
	 * 문서 작성 중 '0 기타양식' 템플릿
	 */
	@RequestMapping(value = "/docwriteetc00.do", method = RequestMethod.GET)
	public String docwriteetc00(Model model, HttpServletRequest req) {
		DocumentDto docDto = appService.selectMaxDocNo();
		int doc_mngno = 0;
		if(docDto.getDoc_mngno()==0){
			doc_mngno = 1;
		} else {
			doc_mngno = docDto.getDoc_mngno()+1;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		 
		int temp_no = Integer.parseInt(req.getParameter("temp_no"));
		TemplateDto tempDto = appService.selectTemplate(temp_no);
		
		model.addAttribute("tempDto", tempDto);
		model.addAttribute("doc_mngno", doc_mngno);
		model.addAttribute("temp_no", temp_no);
		model.addAttribute("today", today);
		return "approval/doc_write_etc_00";
	}

	/*
	 * 결재자 1 찾기
	 */
	@RequestMapping(value = "/appfind00.do", method = RequestMethod.GET)
	public String appfind00(Model model, HttpSession session) {
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 
		int mem_no = memDto.getMem_no();

		logger.info(mem_no + " :          회원 번호");
		List<MemberDto> lists = appService.selectListMember(mem_no);
		model.addAttribute("memberList", lists);
		return "approval/app_find_00";
	}

	/*
	 * 결재자 2 찾기
	 */
	@RequestMapping(value = "/appfind001.do", method = RequestMethod.GET)
	public String appfind001(Model model, HttpSession session) {
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 
		int mem_no = memDto.getMem_no();

		List<MemberDto> lists = appService.selectListMember(mem_no);
		model.addAttribute("memberList", lists);
		return "approval/app_find_001";
	}

	/*
	 * 참조라인 찾기 -> 추후 다중 선택 기능 추가 예정
	 */
	@RequestMapping(value = "/chamsearch00.do", method = RequestMethod.GET)
	public String chamsearch00(Model model, HttpSession session) {
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 
		int mem_no = memDto.getMem_no();

		List<MemberDto> lists = appService.selectListMember(mem_no);
		model.addAttribute("memberList", lists);
		return "approval/cham_search_00";
	}

	/*
	 * 문서함 리스트 페이지
	 */
	@RequestMapping(value = "/doclist00.do", method = RequestMethod.GET)
	public String doclist00() {
		return "approval/doc_list_00";
	}

	/*
	 * 나의 문서함 - 임시문서함
	 */
	@RequestMapping(value = "/doclistimsiMine00.do", method = {	RequestMethod.GET, RequestMethod.POST })
	public String doclistimsiMine00(Model model, HttpSession session, PagingDto pagingDto) { 
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 
		
		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setTotal(appService.selectTotalImsiPaging(pagingDto.getMem_no()));
		
		List<DocumentDto> lists = appService.selectListDocImsi(pagingDto);
		model.addAttribute("imsilist", lists);
		model.addAttribute("paging", pagingDto);

		return "approval/doc_list_Imsi_00";
	}


	/*
	 * 나의 문서함 - 결재진행함
	 */
	@RequestMapping(value = "/doclistIngMine00.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String doclistIngMine00(Model model, HttpSession session, PagingDto pagingDto) { 
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setDoc_status("2");
		pagingDto.setTotal(appService.selectTotalMinePaging(pagingDto));
		
		List<DocumentDto> lists = appService.selectListDocMine(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_Ing_Mine_00";
	}

	/*
	 * 나의 문서함 - 결재완료함
	 */
	@RequestMapping(value = "/doclistEndMine00.do", method = {RequestMethod.GET, RequestMethod.POST })
	public String doclistEndMine00(Model model, HttpSession session, PagingDto pagingDto) {
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setDoc_status("3");
		pagingDto.setTotal(appService.selectTotalMinePaging(pagingDto));

		List<DocumentDto> lists = appService.selectListDocMine(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_End_Mine_00";
	}

	/*
	 * 나의 문서함 - 반려함
	 */
	@RequestMapping(value = "/doclistBanMine00.do", method = {	RequestMethod.GET, RequestMethod.POST })
	public String doclistBanMine00(Model model, HttpSession session, PagingDto pagingDto) {
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setDoc_status("4");
		pagingDto.setTotal(appService.selectTotalMinePaging(pagingDto));

		List<DocumentDto> lists = appService.selectListDocMine(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_Ban_Mine_00";
	}

	/*
	 * 받은 문서함 - 결재예정함
	 */
	@RequestMapping(value = "/doclistWait00.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String doclistWait00(Model model, HttpSession session, PagingDto pagingDto) { 
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setApp_status("1");
		pagingDto.setDoc_status("2");
		pagingDto.setTotal(appService.selectTotalOtherPaging(pagingDto));

		List<DocumentDto> lists = appService.selectListDoc(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_Wait_00";
	}

	/*
	 * 받은 문서함 - 결재진행함
	 */
	@RequestMapping(value = "/doclistIng00.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String doclistIng00(Model model, HttpSession session, PagingDto pagingDto) { 
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setApp_status("2");
		pagingDto.setDoc_status("2");
		pagingDto.setTotal(appService.selectTotalOtherPaging(pagingDto));

		List<DocumentDto> lists = appService.selectListDoc(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_Ing00";
	}

	/*
	 * 받은 문서함 - 결재완료함
	 */
	@RequestMapping(value = "/doclistEnd00.do", method = { RequestMethod.GET,RequestMethod.POST })
	public String doclistEnd00(Model model, HttpSession session, PagingDto pagingDto) { 
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setDoc_status("3");
		pagingDto.setTotal(appService.selectTotalDocBanPaging(pagingDto));

		List<DocumentDto> lists = appService.selectListDocBan(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_End_00";
	}

	/*
	 * 받은 문서함 - 반려함
	 */
	@RequestMapping(value = "/doclistBan00.do", method = { RequestMethod.GET,RequestMethod.POST })
	public String doclistBan00(Model model, HttpSession session,PagingDto pagingDto) { 
		MemberDto memDto = (MemberDto) session.getAttribute("memDto"); 

		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setDoc_status("4");
		pagingDto.setTotal(appService.selectTotalDocBanPaging(pagingDto));

		List<DocumentDto> lists = appService.selectListDocBan(pagingDto);
		model.addAttribute("doclist", lists);
		model.addAttribute("paging", pagingDto);
		return "approval/doc_list_Ban_00";
	}

	/*
	 * 통합문서함
	 */
	@RequestMapping(value = "/doclistTotal00.do", method = { RequestMethod.GET,	RequestMethod.POST })
	public String doclistTotal00(Model model, HttpSession session,PagingDto pagingDto) {
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");
		int mem_no = memDto.getMem_no();

		pagingDto.setTotal(appService.selectTotalAppPaging(mem_no));
		pagingDto.setMem_no(mem_no);
		System.out.println(pagingDto);

		List<DocumentDto> lists = appService.selectListDocument(pagingDto);
		System.out.println(lists);
		model.addAttribute("paging", pagingDto);
		model.addAttribute("doclist", lists);
		return "approval/doc_list_Total_00";
	}

	/*
	 * 스마트 에디터 페이지
	 */
	@RequestMapping(value = "/docwriteeditor.do", method = RequestMethod.GET)
	public String docwriteeditor() {
		return "approval/editor";
	}

	/*
	 * 나의 문서함 - 임시 문서함 - 문서 상세 보기
	 */
	@RequestMapping(value = "/docdetailimsi00.do", method = RequestMethod.GET)
	public String docdetailimsi00(Model model, String doc_mngno) {
		DocumentDto docDto = appService.selectOneDocImsi(doc_mngno);
		docDto.setDoc_lastdate(docDto.getDoc_lastdate().split(" ")[0]);
		model.addAttribute("docOne", docDto);
		return "approval/doc_detail_Imsi_00";
	}
	
	/*
	 * 받은문서함의 결재 예정함 디테일 페이지
	 */
	@RequestMapping(value = "/docdetailApp00.do", method = RequestMethod.GET)
	public String docdetailApp00(Model model, String doc_mngno) { 
		List<DocumentDto> lists = appService.selectListDetailDoc(doc_mngno);
		List<ApprovalDto> replylists = appService.selectListDocReply(doc_mngno);
		System.err.println(replylists);
		model.addAttribute("doclist", lists);
		model.addAttribute("replylist", replylists);
		return "approval/doc_detail_App_00";
	}

	/*
	 * 나의 문서함의 결재 상신 디테일 페이지
	 */
	@RequestMapping(value = "/docdetailSave00.do", method = RequestMethod.GET)
	public String docdetailSave00(Model model, String doc_mngno) { 
		List<DocumentDto> lists = appService.selectListDetailDoc(doc_mngno);
		model.addAttribute("doclist", lists);
		return "approval/doc_detail_Save_00";
	}

	/*
	 * 나의 문서함의 결재 진행함 디테일 페이지
	 */
	@RequestMapping(value = "/docdetailSave01.do", method = RequestMethod.GET)
	public String docdetailSave01(Model model, String doc_mngno) { 
		List<DocumentDto> lists = appService.selectListDetailDoc(doc_mngno);
		model.addAttribute("doclist", lists);
		return "approval/doc_detail_Save_01";
	}

	/*
	 * 결재 반려 디테일 페이지
	 */
	@RequestMapping(value = "/docdetailban00.do", method = RequestMethod.GET)
	public String docdetailban00(Model model, String doc_mngno) { 
		List<DocumentDto> lists = appService.selectListDetailDoc(doc_mngno);
		model.addAttribute("doclist", lists);
		return "approval/doc_detail_ban_00";
	}

	/*
	 * 나의 문서함 - 임시 문서함 - 문서 상세 보기
	 */
	@RequestMapping(value = "/docmodifyimsi00.do", method = RequestMethod.GET)
	public String docmodifyimsi00(Model model, String doc_mngno) { 
		DocumentDto docDto = appService.selectOneDocImsi(doc_mngno);
		docDto.setDoc_lastdate(docDto.getDoc_lastdate().split(" ")[0]);
		model.addAttribute("docOne", docDto);
		return "approval/doc_modify_imsi_00";
	}
}
