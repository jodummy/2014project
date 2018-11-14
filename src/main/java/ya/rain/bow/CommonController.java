package ya.rain.bow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ya.rain.bow.beans.PercentCalculation;
import ya.rain.bow.dtos.BoardDto;
import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.ProjectDto;
import ya.rain.bow.model.service.ApprovalService;
import ya.rain.bow.model.service.BoardService;
import ya.rain.bow.model.service.ProjectService;

@Controller
public class CommonController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private ApprovalService appService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private BoardService boardService;
	
	// 다중파일업로드
	@RequestMapping(value = "/resources/editor/sample/photo_uploader/multiplePhotoUpload.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse respon) {
		try {
			// 파일정보
			String sFileInfo = "";
			// 파일명을 받는다 - 일반 원본파일명
			String filename = request.getHeader("file-name");
			// 파일 확장자
			String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
			// 확장자를소문자로 변경
			filename_ext = filename_ext.toLowerCase();
			// 파일 기본경로 _ 상세경로
			String filePath = "D:\\workspaceF\\ProjectSupporter\\src\\main\\webapp\\resource\\photo_uploader" + File.separator;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String realFileNm = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = formatter.format(new java.util.Date());
			realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			String rlFileNm = filePath + realFileNm;
			///////////////// 서버에 파일쓰기 /////////////////
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(rlFileNm);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			if (is != null) {
				is.close();
			}
			os.flush();
			os.close();
			///////////////// 서버에 파일쓰기 /////////////////
			// 정보 출력
			sFileInfo += "&bNewLine=true";
			// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
			sFileInfo += "&sFileName=" + filename;
			sFileInfo += "&sFileURL=" + "http://localhost:8091/ProjectSupporter/resource/photo_uploader/" + realFileNm;
			PrintWriter print = respon.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value="/main.do", method={RequestMethod.GET, RequestMethod.POST})
	public String main(HttpSession session, Model model, PagingDto p) {
		logger.info("main : " + new Date());
		
		// [전자결재] 수정
		MemberDto memDto = (MemberDto) session.getAttribute("memDto");
		// 결재예정함 갯수
		PagingDto pagingDto = new PagingDto();
		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setApp_status("1");
		pagingDto.setDoc_status("2");
		int waittotal= appService.selectTotalOtherPaging(pagingDto);
		model.addAttribute("waittotal", waittotal);
		// 결재진행함 갯수
		pagingDto.setMem_no(memDto.getMem_no());
		pagingDto.setDoc_status("2");
		int myingtotal = appService.selectTotalMinePaging(pagingDto);
		model.addAttribute("myingtotal",myingtotal);
		
		//클래스 캘린더 코드 처리
		String dept_no = ((MemberDto) session.getAttribute("memDto")).getDept_no();
		DepartmentDto deparDto = new DepartmentDto();
		deparDto.setDept_no(dept_no);
		deparDto = projectService.selectOneSchdulecode(deparDto);
		model.addAttribute("deparDto", deparDto);
		//프로젝트 일정처리
		List<ProjectDto> prjLists = null;
		p.setDept_no(dept_no);
		if(p.getDept_no().charAt(0) == 'C') {
			prjLists = projectService.selectListPrjClass(p);
		}else {
			prjLists = projectService.selectListPrjTeam(p);
		}
		for(int i=0; i<prjLists.size(); i++) {
			prjLists.get(i).setPrjs_term(new PercentCalculation(projectService.selectListProjectSchdule(prjLists.get(i).getPrj_no())).getTermLine());
		}
		List<ProjectDto> prjList = new ArrayList<ProjectDto>();
		for (ProjectDto prjDto : prjLists) {
			if(prjDto.getDept_no().equalsIgnoreCase(dept_no)) {
				prjList.add(prjDto);
				break;
			}
		}
		model.addAttribute("prjList", prjList);
		
		// 공유 게시판 공지사항
		PagingDto boardPagingDto = new PagingDto();
		boardPagingDto.setListCnt(5);
		boardPagingDto.setCtgr_no(1);
		List<BoardDto> boardNoticeLists = boardService.selectBoardNotice(boardPagingDto);
		model.addAttribute("boardNoticeLists", boardNoticeLists);
		
		return "common/main";
	}
	
	@RequestMapping(value="/info/error", method={RequestMethod.GET, RequestMethod.POST})
	public String error(HttpServletResponse response, Model model) {
		logger.info("error : " + new Date());
		
		response.setStatus(HttpServletResponse.SC_OK);
		model.addAttribute("msg", "안알려줌");
		model.addAttribute("url", "../main.do");
		return "common/error";
	}
	
	
}
