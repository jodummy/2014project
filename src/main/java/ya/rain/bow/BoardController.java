package ya.rain.bow;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import ya.rain.bow.beans.FileUpDown;
import ya.rain.bow.dtos.BoardDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.BoardFileDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.SearchDto;
import ya.rain.bow.model.service.BoardService;

@Controller
public class BoardController {
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 커뮤니티 메인 - 게시글 조회
	@RequestMapping(value="/communityMain.do", method={RequestMethod.POST, RequestMethod.GET})
	public String communityMain(Model model, HttpSession session) {
		logger.info("communityMain : " + new Date());
		session.removeAttribute("paging");
		
		PagingDto pagingDto = new PagingDto();
		pagingDto.setListCnt(5);
		pagingDto.setCtgr_no(1);
		List<BoardDto> shareBoardLists = boardService.selectBoard(pagingDto);
		
		String dept_no = ((MemberDto)session.getAttribute("memDto")).getDept_no();
		if (dept_no.charAt(0) == 'T') {
			dept_no = ((MemberDto)session.getAttribute("memDto")).getDept_top();
		}
		pagingDto.setCtgr_no(2);
		pagingDto.setDept_no(dept_no);
		List<BoardDto> classBoardLists = boardService.selectBoard(pagingDto);

		pagingDto.setTotal(boardService.seletcBoardCount(pagingDto));
		model.addAttribute("shareBoardLists", shareBoardLists);
		model.addAttribute("classBoardLists", classBoardLists);
		model.addAttribute("boardTitleName", classBoardLists.get(0).getDept_name());
		return "board/communityMain";
	}
	
	
	// 게시판 글 조회
	@RequestMapping(value="/boardList.do", method={RequestMethod.POST, RequestMethod.GET})
	public String boardList(Model model, PagingDto pagingDto, HttpSession session) {
		logger.info("boardList : " + new Date());
		session.removeAttribute("paging");
		session.setAttribute("paging", pagingDto);
		
		if (session.getAttribute("keyWord") != null) {
			session.removeAttribute("searchNum");
			session.removeAttribute("keyWord");
		}
		int noticeCnt = boardService.selectBoardNoticeCount(pagingDto);
		if (noticeCnt != 0) {
			List<BoardDto> boardNoticeLists = boardService.selectBoardNotice(pagingDto);
			model.addAttribute("boardNoticeLists", boardNoticeLists);
		}
		List<BoardDto> boardLists = boardService.selectBoard(pagingDto);
		pagingDto.setTotal(boardService.seletcBoardCount(pagingDto));
		model.addAttribute("boardLists", boardLists);
		model.addAttribute("boardTitleName", boardLists.get(0).getDept_name());
		String thisDept = "";
		if(pagingDto.getCtgr_no() != 1) {
			thisDept = "&dept_no=" + pagingDto.getDept_no();
		}
		session.setAttribute("thisDept", thisDept);
		model.addAttribute("url", "./boardList.do");
		return "board/boardList";
	}
	
	// 게시글 검색
	@RequestMapping(value="/boardSearch.do", method={RequestMethod.POST, RequestMethod.GET})
	public String boardSearch(Model model, SearchDto searchDto, PagingDto pagingDto,
							HttpServletRequest req, HttpSession session) {
		logger.info("boardSearch : " + new Date());
		int searchNum = 0;
		String keyWord = null;
		if (req.getParameter("keyWord") != null) {
			searchNum = Integer.parseInt(req.getParameter("searchNum"));
			keyWord = req.getParameter("keyWord");
		}
		else {
			searchNum = (int)session.getAttribute("searchNum");
			keyWord = (String)session.getAttribute("keyWord");
		}
		switch (searchNum) {
		case 1:
			searchDto.setTitle(keyWord.split(" "));
			break;
		case 2:
			searchDto.setContent(keyWord.split(" "));
			break;
		case 3:
			searchDto.setSearch(keyWord.split(" "));
			break;
		case 4:
			searchDto.setName(keyWord.split(" "));
			break;
		}
		int noticeCnt = boardService.selectBoardNoticeCount(pagingDto);
		if (noticeCnt != 0) {
			List<BoardDto> boardNoticeLists = boardService.selectBoardNotice(pagingDto);
			model.addAttribute("boardNoticeLists", boardNoticeLists);
		}
		
		searchDto.setIntNum(pagingDto.getCtgr_no());
		searchDto.setPaging(pagingDto);
		
		pagingDto.setTotal(boardService.selectBoardSearchCount(searchDto));
		System.out.println(boardService.selectBoardSearchCount(searchDto));
		List<BoardDto> boardLists = boardService.selectBoardSearch(searchDto);
		model.addAttribute("boardLists", boardLists);
		model.addAttribute("paging", pagingDto);
		model.addAttribute("url", "./boardSearch.do");
		session.removeAttribute("paging");
		session.removeAttribute("searchNum");
		session.removeAttribute("keyWord");
		session.setAttribute("paging", pagingDto);
		session.setAttribute("searchNum", searchNum);
		session.setAttribute("keyWord", keyWord);
		model.addAttribute("url", "./boardSearch.do");
		String thisDept = "";
		if(pagingDto.getCtgr_no() != 1) {
			thisDept = "&dept_no=" + pagingDto.getDept_no();
		}
		session.setAttribute("thisDept", thisDept);
		return "board/boardList";
	}
	
	
	// 게시글 등록
	@RequestMapping(value="/insertBoard.do", method={RequestMethod.POST, RequestMethod.GET})
	public String insertBoard(Model model, BoardDto boardDto, HttpSession session) {
		logger.info("insertBoard : " + new Date());
	    if (boardDto.getBoard_notice() == null) {
	    	boardDto.setBoard_notice("N");
	    }
	    String board_content = boardDto.getBoard_content();
//		board_content = board_content.replaceAll("&quot;", "");
		System.out.println(board_content);
		boardDto.setBoard_content(board_content);
		boolean rst = boardService.insertBoard(boardDto);
		if (!rst) {
			model.addAttribute("msg", "게시글 등록 오류");
			model.addAttribute("url", "./boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no()+"&index="+((PagingDto)session.getAttribute("paging")).getIndex());
		}
		return rst ? "redirect:/boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no() + session.getAttribute("thisDept") : "common/error";
	}
	
	// 게시글 상세조회
	@RequestMapping(value="/boardDetail.do", method={RequestMethod.POST, RequestMethod.GET})
	public String boardDetail(Model model, HttpServletRequest req, HttpSession session) {
		logger.info("insertBoard : " + new Date());
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		boolean rst = boardService.updateBoardReadCnt(board_no);
		if (rst) {
			BoardDto boardDto = boardService.selectOneBoard(board_no);
			boardDto.setRnum(Integer.parseInt(req.getParameter("rnum")));
			model.addAttribute("boardDto", boardDto);
			List<BoardFileDto> lists = boardService.selectBoardFile(board_no);
			model.addAttribute("bfLists", lists);
			String thisDept = "";
			if(boardDto.getCtgr_no() != 1) {
				thisDept = "&dept_no=" + boardDto.getDept_no();
			}
			session.setAttribute("thisDept", thisDept);
		}
		else {
			model.addAttribute("msg", "게시글 조회 오류");
			model.addAttribute("url", "./boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no()+"&index="+((PagingDto)session.getAttribute("paging")).getIndex());
		}
		return rst ? "board/boardDetail" : "common/error";
	}
	
	// 게시글 삭제(delflag)
	@RequestMapping(value="/updateDelBoard.do", method={RequestMethod.POST, RequestMethod.GET})
	public String updateDelBoard(Model model, HttpServletRequest req, HttpSession session) {
		logger.info("updateDelBoard : " + new Date());
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		boolean rst = boardService.updateDelBoard(board_no);
		if (!rst) {
			model.addAttribute("msg", "게시글 삭제 오류");
			model.addAttribute("url", "./boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no()+"&index="+((PagingDto)session.getAttribute("paging")).getIndex());
		}
		return rst ? "redirect:/boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no() + session.getAttribute("thisDept") : "common/error";
	}
	
	// 게시글 다중삭제(delflag)
	@RequestMapping(value="/updateDelBoardChecked.do", method={RequestMethod.POST, RequestMethod.GET})
	public String updateDelBoardChecked(Model model, int[] chkVal, HttpSession session) {
		logger.info("updateDelBoardChecked : " + new Date());
		boolean rst = boardService.updateDelBoardChecked(chkVal);
		if (!rst) {
			model.addAttribute("msg", "게시글 선택 삭제 오류");
			model.addAttribute("url", "./boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no()+"&index="+((PagingDto)session.getAttribute("paging")).getIndex());
		}
		return rst ? "redirect:/boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no() + session.getAttribute("thisDept") : "common/error";
	}
	
	// 게시글 수정
	@RequestMapping(value="/updateBoard.do", method={RequestMethod.POST, RequestMethod.GET})
	public String updateBoard(Model model, HttpServletRequest req, HttpSession session) {
		logger.info("updateBoard : " + new Date());
		String board_content = req.getParameter("board_content");
//		board_content = board_content.replaceAll("\"", "&quot;");
		BoardDto boardDto = new BoardDto(Integer.parseInt(req.getParameter("board_no")),
				req.getParameter("board_notice"), req.getParameter("board_title"), board_content);
		int rnum = Integer.parseInt(req.getParameter("rnum"));
		if (boardDto.getBoard_notice() == null) {
			boardDto.setBoard_notice("N");
		}
		boolean rst = boardService.updateBoard(boardDto);
		if (!rst) {
			model.addAttribute("msg", "게시글 수정 오류");
			model.addAttribute("url", "./boardList.do?ctgr_no="+((PagingDto)session.getAttribute("paging")).getCtgr_no()+"&index="+((PagingDto)session.getAttribute("paging")).getIndex());
		}
		return rst ? "redirect:/boardDetail.do?board_no="+boardDto.getBoard_no()+"&rnum="+rnum+"&ctgr_no="+boardDto.getCtgr_no()+"&index="+((PagingDto)session.getAttribute("paging")).getIndex() + session.getAttribute("thisDept") : "common/error";
	}
	
	// 파일 다운로드
	@RequestMapping(value="/BoardFileDown.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private byte[] BoardFileDown(BoardFileDto bfDto , HttpServletResponse resp) {
		logger.info("BoardFileDown : " + new Date());
		String filePath = "D:/fileupload/board/" + bfDto.getBf_savenm();
		String oldNmae = bfDto.getBf_oldnm();
		return new FileUpDown(resp, filePath, oldNmae).getFileByte();
	}
}
