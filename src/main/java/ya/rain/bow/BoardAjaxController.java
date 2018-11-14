package ya.rain.bow;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ya.rain.bow.beans.FileUpDown;
import ya.rain.bow.dtos.BoardDto;
import ya.rain.bow.dtos.BoardReplyDto;
import ya.rain.bow.model.service.BoardService;

@Controller
public class BoardAjaxController {
	
	private Logger logger = LoggerFactory.getLogger(BoardAjaxController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 댓글 조회
	@RequestMapping(value="/selectBoardReply.do", method=RequestMethod.POST)
	@ResponseBody
	private List<BoardReplyDto> selectBoardReply(int board_no) {
		logger.info("selectBoardReply : " + new Date());
		List<BoardReplyDto> brLists = boardService.selectBoardReply(board_no);
		return brLists;
	}
	
	// 댓글 등록
	@RequestMapping(value="/insertBoardReply.do", method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Boolean> insertBoardReply(HttpServletRequest req) {
		logger.info("insertBoardReply : " + new Date());
		BoardReplyDto brDto = new BoardReplyDto(Integer.parseInt(req.getParameter("board_no")),
				req.getParameter("dept_no"), Integer.parseInt(req.getParameter("mem_no")),
				req.getParameter("br_content"));
		boolean rst = boardService.insertBoardReply(brDto);
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}
	
	// 댓글'댓글 등록
	@RequestMapping(value="/insertBrReply.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> insertBrReply(HttpServletRequest req) {
		logger.info("insertBrReply : " + new Date());
		BoardReplyDto brDto = new BoardReplyDto(Integer.parseInt(req.getParameter("br_no")),
				Integer.parseInt(req.getParameter("board_no")), req.getParameter("dept_no"),
				Integer.parseInt(req.getParameter("mem_no")), req.getParameter("br_content"));
		boolean rst = boardService.insertBrReply(brDto);
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}
	
	// 댓글 삭제(delflag)
	@RequestMapping(value="/updateDelBoardReply.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> updateDelBoardReply(int br_no) {
		logger.info("updateDelBoardReply : " + new Date());
		boolean rst = boardService.updateDelBoardReply(br_no);
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}
	
	// 파일 등록
	@RequestMapping(value="/insertBoardFile.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> insertBoardFile(MultipartHttpServletRequest multipartRequest) {
		logger.info("insertBoardFile : " + new Date());
		String board_content = multipartRequest.getParameter("board_content");
//		board_content = board_content.replaceAll("\"", "&quot;");
		BoardDto boardDto = new BoardDto(Integer.parseInt(multipartRequest.getParameter("ctgr_no")), Integer.parseInt(multipartRequest.getParameter("mem_no")),
				multipartRequest.getParameter("dept_no"), multipartRequest.getParameter("board_notice"),
				multipartRequest.getParameter("board_title"), board_content);
		boolean rst = boardService.insertBoard(boardDto);
		if (rst) {
			String filePath = "D:/fileupload/board";
			Map<String, List<String>> fileNames = new FileUpDown(multipartRequest, filePath).getFileName();
			rst = boardService.insertBoardFile(fileNames, 0);
		}
		Map<String, Boolean> rMap = new HashMap<String, Boolean>();
		rMap.put("isc", rst);
		return rMap;
	}
	
	// 파일 수정
	@RequestMapping(value="/updateBoardFile.do", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	private Map<String, Boolean> updateBoardFile(MultipartHttpServletRequest multipartRequest) {
		logger.info("updateBoardFile : " + new Date());
		int board_no = Integer.parseInt(multipartRequest.getParameter("board_no"));
		String board_content = multipartRequest.getParameter("board_content");
//		board_content = board_content.replaceAll("\"", "&quot;");
		BoardDto boardDto = new BoardDto(board_no,
				multipartRequest.getParameter("board_notice"), multipartRequest.getParameter("board_title"), board_content);
		boolean rst = boardService.updateBoard(boardDto);
		if (rst) {
			String filePath = "D:/fileupload/board";
			Map<String, List<String>> fileNames = new FileUpDown(multipartRequest, filePath).getFileName();
			rst = boardService.insertBoardFile(fileNames, board_no);
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
		boolean rst = boardService.deleteBoardFile(bf_no);
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
		
}
