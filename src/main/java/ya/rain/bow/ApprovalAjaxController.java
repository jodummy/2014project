package ya.rain.bow;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ya.rain.bow.dtos.ApprovalDto;
import ya.rain.bow.dtos.DocumentDto;
import ya.rain.bow.model.service.ApprovalService;

@Controller
public class ApprovalAjaxController {

	private Logger logger = LoggerFactory.getLogger(ApprovalAjaxController.class);

	@Autowired
	private ApprovalService approvalService;

	/*
	 * 임시 저장시
	 */
	@RequestMapping(value = "/imsiSaveProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int imsiSaveProcess(Model model, HttpServletRequest request) {
		String doc_mngno_str = request.getParameter("doc_mngno").trim();
		String doc_deptnm = request.getParameter("doc_deptnm").trim();
		String doc_writer = request.getParameter("doc_writer").trim();
		String doc_regdate = request.getParameter("doc_regdate").trim();
		String doc_lastdate = request.getParameter("doc_lastdate").trim();
		String doc_title = request.getParameter("doc_title").trim();
		String doc_content = request.getParameter("editor").trim();
		String temp_no_str = request.getParameter("temp_no").trim();
		String mem_no_str = request.getParameter("mem_no").trim();
		String doc_reperence = request.getParameter("doc_reperence").trim();
		String doc_status = "0";

		int doc_mngno = Integer.parseInt(doc_mngno_str);
		int temp_no = Integer.parseInt(temp_no_str);
		int mem_no = Integer.parseInt(mem_no_str);

		DocumentDto docDto = new DocumentDto(doc_mngno, temp_no, mem_no, doc_deptnm, doc_writer, doc_title,
				doc_content, doc_regdate, doc_status, doc_lastdate,	doc_reperence);
		int n = approvalService.insertDocumentImsi(docDto);

		logger.info("====== insert result : " + n);

		System.out.println(doc_status);
		return n;
	}

	/*
	 * 결재 상신 시
	 */
	@RequestMapping(value = "/saveProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int saveProcess(Model model, HttpServletRequest request) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String doc_date = formatter.format(new Date());
		
		String doc_mngno_str = request.getParameter("doc_mngno").trim();
		String doc_deptnm = request.getParameter("doc_deptnm").trim();
		String doc_writer = request.getParameter("doc_writer").trim();
		String doc_regdate = request.getParameter("doc_regdate").trim();
		String doc_lastdate = request.getParameter("doc_lastdate").trim();
		String doc_title = request.getParameter("doc_title").trim();
		String doc_content = request.getParameter("editor");
		String temp_no_str = request.getParameter("temp_no").trim();
		String mem_no_mine = request.getParameter("mem_no").trim();
		String doc_status = "2";
		String doc_reperence = request.getParameter("doc_reperence").trim();
		String mem_no_str = request.getParameter("app1").split(" ")[0];
		String mem_no0_str = request.getParameter("app0").split(" ")[0];
		System.out.println("******************" + doc_content);

		int doc_mngno = Integer.parseInt(doc_mngno_str);
		int temp_no = Integer.parseInt(temp_no_str);

		int mem_no = Integer.parseInt(mem_no_mine);
		int mem_no1 = Integer.parseInt(mem_no_str);
		int mem_no0 = Integer.parseInt(mem_no0_str);
		
		// doc_no 문서번호를 만들어주는 부분
	 	String str = "";
	 	String cnt = approvalService.selectOneDocNo(doc_mngno);		// 문서함에 있는 가장 최근의 doc_no를 조회한다.
	 	
	 	if(cnt==null){
	 		logger.info("=========== cnt 값이 null 입니다.");
	 		str = doc_date;
	 		cnt = "001";
	 	} else{
	 		logger.info("===========");
	 		for (int i = 1; i < 9; i++) {
				str+=cnt.charAt(i);
			}
	 		System.err.println(str);
	 		if (doc_date.equals(str)) {
	 			cnt = str;
	 			if(cnt.charAt(0)=='0'){	// 100의 자리가 0이면
	 				if(cnt.charAt(1)=='0'){	// 10의 자리도 0이면 (0~9)
	 					cnt = "00" + (((int)cnt.charAt(2)-48)+1);
	 				} else { // 10의 값 (10~99)
	 					cnt = "0" + (((((int)cnt.charAt(1)-48)*10) + ((int)cnt.charAt(2)-48)) + 1);
	 				}
	 			} else {	// 100의 자리 값 (100~999)    
	 				cnt = "0" + ((  (((int)cnt.charAt(0)-48)*100) + (((int)cnt.charAt(1)-48)*10) + ((int)cnt.charAt(2)-48)  ) + 1)   ;
	 			}
	 		} else {
	 			cnt="001";
	 		}
	 	}
	 	String doc_no = approvalService.makeDocNo(temp_no, cnt);
				

		
		DocumentDto docDto = new DocumentDto(doc_mngno, temp_no, mem_no, doc_deptnm, doc_writer, doc_title, doc_content,
				doc_regdate, doc_no, doc_status, doc_lastdate, doc_reperence);
		
		ApprovalDto appDtomine = new ApprovalDto(doc_mngno, mem_no, 2, "2");
		ApprovalDto appDto1 = new ApprovalDto(doc_mngno, mem_no1, 1, "1");
		ApprovalDto appDto0 = new ApprovalDto(doc_mngno, mem_no0, 0, "1");

		int m = approvalService.insertDocumentApp(docDto);
		int n = approvalService.insertApprovalLine(appDtomine);
		int n1 = approvalService.insertApprovalLine(appDto1);
		int n2 = approvalService.insertApprovalLine(appDto0);

		return m + n + n1 + n2;
	}

	

	/*
	 * 임시 저장 문서를 다시 임시저장할때
	 */
	@RequestMapping(value = "/imsiSaveProcessAgain.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int imsiSaveProcessAgain(Model model, HttpServletRequest request) {
		String doc_mngno_str = request.getParameter("doc_mngno").trim();
		approvalService.deleteDocImsiAgain(doc_mngno_str);
		DocumentDto docDto = approvalService.selectMaxDocNo();
		int doc_mngno = docDto.getDoc_mngno() + 1;
		String doc_deptnm = request.getParameter("doc_deptnm").trim();
		String doc_writer = request.getParameter("doc_writer").trim();
		String doc_regdate = request.getParameter("doc_regdate").trim();
		String doc_lastdate = request.getParameter("doc_lastdate").trim();
		String doc_title = request.getParameter("doc_title").trim();
		String doc_content = request.getParameter("editor").trim();
		String temp_no_str = request.getParameter("temp_no").trim();
		String mem_no_str = request.getParameter("mem_no").trim();
		String doc_status = "0";

//		int doc_mngno = Integer.parseInt(doc_mngno_str);
		int temp_no = Integer.parseInt(temp_no_str);
		int mem_no = Integer.parseInt(mem_no_str);

		DocumentDto docDto1 = new DocumentDto(doc_mngno, temp_no, mem_no, doc_deptnm, doc_writer, doc_title,
				doc_content, doc_regdate, doc_status, doc_lastdate);
		int n = approvalService.insertDocumentImsi(docDto1);

		logger.info("====== insert result : " + n);

		System.out.println(doc_status);
		return n;
	}
	
	/*
	 * 임시 저장 문서 상신 시!
	 */
	@RequestMapping(value = "/saveProcessAgain.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int saveProcessAgain(Model model, HttpServletRequest request) {
		String doc_mngno_str = request.getParameter("doc_mngno").trim();
		approvalService.deleteDocImsiAgain(doc_mngno_str);

		DocumentDto docDto = approvalService.selectMaxDocNo();
		int doc_mngno = docDto.getDoc_mngno() + 1;

		String doc_deptnm = request.getParameter("doc_deptnm").trim();
		String doc_writer = request.getParameter("doc_writer").trim();
		String doc_regdate = request.getParameter("doc_regdate").trim();
		String doc_lastdate = request.getParameter("doc_lastdate").trim();
		String doc_title = request.getParameter("doc_title").trim();
		String doc_content = request.getParameter("editor");
		String temp_no_str = request.getParameter("temp_no").trim();
		String mem_no_mine = request.getParameter("mem_no").trim();
		String doc_status = "2";
		String doc_reperence = request.getParameter("doc_reperence").trim();
		String mem_no_str = request.getParameter("app1").split(" ")[0];
		String mem_no0_str = request.getParameter("app0").split(" ")[0];
		System.out.println("******************" + doc_content);

		int temp_no = Integer.parseInt(temp_no_str);

		// doc_no 문서번호를 만들어주는 부분
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String doc_date = formatter.format(new Date());
		String str = "";
		String cnt = approvalService.selectOneDocNo(doc_mngno);		// 문서함에 있는 가장 최근의 doc_no를 조회한다.
		
		if(cnt==null){
			logger.info("=========== cnt 값이 null 입니다.");
			str = doc_date;
			cnt = "001";
		} else{
			logger.info("===========");
			str= str.split("_")[1];
			System.err.println(str);
			if (doc_date.equals(str)) {
				cnt = cnt.split("_")[2];
				if(cnt.charAt(0)=='0'){	// 100의 자리가 0이면
					if(cnt.charAt(1)=='0'){	// 10의 자리도 0이면 (0~9)
						cnt = "00" + (((int)cnt.charAt(2)-48)+1);
					} else { // 10의 값 (10~99)
						cnt = "0" + (((((int)cnt.charAt(1)-48)*10) + ((int)cnt.charAt(2)-48)) + 1);
					}
				} else {	// 100의 자리 값 (100~999)    
					cnt = "0" + ((  (((int)cnt.charAt(0)-48)*100) + (((int)cnt.charAt(1)-48)*10) + ((int)cnt.charAt(2)-48)  ) + 1)   ;
				}
			} else {
				cnt="001";
			}
		}
		String doc_no = approvalService.makeDocNo(temp_no, cnt);
		

		int mem_no = Integer.parseInt(mem_no_mine);
		int mem_no1 = Integer.parseInt(mem_no_str);
		int mem_no0 = Integer.parseInt(mem_no0_str);

		DocumentDto docDto1 = new DocumentDto(doc_mngno, temp_no, mem_no, doc_deptnm, doc_writer, doc_title,
				doc_content, doc_regdate, doc_no, doc_status, doc_lastdate, doc_reperence);
		ApprovalDto appDtomine = new ApprovalDto(doc_mngno, mem_no, 2, "2");
		ApprovalDto appDto1 = new ApprovalDto(doc_mngno, mem_no1, 1, "1");
		ApprovalDto appDto0 = new ApprovalDto(doc_mngno, mem_no0, 0, "1");

		int m = approvalService.insertDocumentApp(docDto1);
		int n = approvalService.insertApprovalLine(appDtomine);
		int n1 = approvalService.insertApprovalLine(appDto1);
		int n2 = approvalService.insertApprovalLine(appDto0);

		return m + n + n1 + n2;
	}

	/*
	 * 스마트 에디터 결과 페이지
	 */
	@RequestMapping("/submit.do")
	public void submit(HttpServletRequest request) {
		System.out.println("에디터 컨텐츠값:" + request.getParameter("editor"));
	}

	/*
	 * 결재 관리
	 */
	@RequestMapping(value = "/appBanProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int appBanProcess(HttpServletRequest request) {
		String doc_mngno = request.getParameter("doc_mngno").trim();
		int n = approvalService.updateDocAppBan(doc_mngno);
		return n;
	}

		
	/*
	 * 결재 상신 시 - 남이 준 걸 내가 딱 받아서 결재를 딱!
	 */
	@RequestMapping(value = "/appProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int appProcess(Model model, HttpServletRequest request) {
		String doc_mngno_str = request.getParameter("doc_mngno").trim();
		String mem_no_str = request.getParameter("mem_no").trim();
		String order = request.getParameter("order").trim();
		logger.info("order"+order);
		int doc_mngno = Integer.parseInt(doc_mngno_str);
		int mem_no = Integer.parseInt(mem_no_str);
		int n = 0;
		logger.info("if문 시작");
		if(order.equals("1")) {		// 남이 준 걸 내가 딱 받아서 결재를 딱! 중간라인!
			logger.info("2번째 결재라인 프로세스 완료!");
			ApprovalDto appDto = new ApprovalDto(doc_mngno, mem_no, 1, "2");
			n = approvalService.updateApprovalLine(appDto);
		} else if(order.equals("2")) {		// 최종결재자! 딱!
			logger.info("3번째 결재라인 프로세스 완료!");
			ApprovalDto appDto = new ApprovalDto(doc_mngno, mem_no, 1, "2");
			ApprovalDto appDto1 = new ApprovalDto(doc_mngno, mem_no, 2, "2");
			DocumentDto docDto = new DocumentDto(doc_mngno, "0", "3");
			n = approvalService.updateApprovalLine(appDto);
			n = approvalService.updateApprovalLine(appDto1);
			approvalService.updateDocAppLineFinal(docDto);
		}
		logger.info("if문 끝");
		return +n;
	}
	
	@RequestMapping(value = "/replyProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int replyProcess(Model model, HttpServletRequest request) {
		int ar_no = approvalService.selectAppArNoMax()+1;
		int doc_mngno = Integer.parseInt(request.getParameter("doc_mngno"));
		String ar_content = request.getParameter("ar_content");
		String ar_writer = request.getParameter("ar_writer");
		ApprovalDto appDto = new ApprovalDto();
		appDto.setAr_no(ar_no);
		appDto.setDoc_mngno(doc_mngno);
		appDto.setAr_content(ar_content);
		appDto.setAr_writer(ar_writer);
		int rst = approvalService.insertAppReply(appDto);

		return rst;
	}
	
	@RequestMapping(value = "/replydeleteProcess.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int replydeleteProcess(Model model, HttpServletRequest request) {
		int ar_no = Integer.parseInt(request.getParameter("ar_no"));
		String doc_mngno_str = request.getParameter("doc_mngno");
		int doc_mngno= Integer.parseInt(doc_mngno_str);
		
		ApprovalDto appDto = new ApprovalDto();
		appDto.setAr_no(ar_no);
		appDto.setDoc_mngno(doc_mngno);
		
		int rst = approvalService.deleteAppReply(appDto);

		return rst;
	}
	/*// 파일 등록
	@RequestMapping(value="/insertBoardFile.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> insertBoardFile(MultipartHttpServletRequest multipartRequest) {
		logger.info("insertBoardFile : " + new Date());
		String board_content = multipartRequest.getParameter("board_content");
		board_content = board_content.replaceAll("\"", "&quot;");
		BoardDto boardDto = new BoardDto(Integer.parseInt(multipartRequest.getParameter("ctgr_no")), Integer.parseInt(multipartRequest.getParameter("mem_no")),
				multipartRequest.getParameter("dept_no"), multipartRequest.getParameter("board_notice"),
				multipartRequest.getParameter("board_title"), board_content);
		boolean rst = approvalService.insertDocumentApp(docDto);
		boolean rst2 = approvalService.insertApprovalLine(appDto);
		if (rst) {
			String filePath = "D:/fileupload/board";
			Map<String, List<String>> fileNames = new FileUpDown(multipartRequest, filePath).getFileName();
			rst = approvalService.insertFile(fileNames, 0);
		}
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}*/
	/*
	// 파일 수정
	@RequestMapping(value="/updateBoardFile.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> updateBoardFile(MultipartHttpServletRequest multipartRequest) {
		logger.info("updateBoardFile : " + new Date());
		int board_no = Integer.parseInt(multipartRequest.getParameter("board_no"));
		String board_content = multipartRequest.getParameter("board_content");
		board_content = board_content.replaceAll("\"", "&quot;");
		BoardDto boardDto = new BoardDto(board_no,
				multipartRequest.getParameter("board_notice"), multipartRequest.getParameter("board_title"), board_content);
		boolean rst = approvalService.updateBoard(boardDto);
		if (rst) {
			String filePath = "D:/fileupload/board";
			Map<String, List<String>> fileNames = new FileUpDown(multipartRequest, filePath).getFileName();
			rst = approvalService.insertBoardFile(fileNames, board_no);
		}
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}
	
	// 파일삭제
	@RequestMapping(value="/deleteBoardFile.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> deleteBoardFile(HttpServletRequest req) {
		logger.info("deleteBoardFile : " + new Date());
		int bf_no = Integer.parseInt(req.getParameter("bf_no"));
		String bf_savenm = req.getParameter("bf_savenm");
		String filePath = "D:/fileupload/board";
		String path = filePath + "/" +bf_savenm;
		boolean rst = approvalService.deleteBoardFile(bf_no);
		if (rst) {
			File file = new File(path);
			   if(file.exists() == true){
			      file.delete();
			   }
		}
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}
		*/
}
