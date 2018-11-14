package ya.rain.bow.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ya.rain.bow.dtos.BoardFileDto;
import ya.rain.bow.dtos.BoardReplyDto;
import ya.rain.bow.dtos.BoardDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.SearchDto;
import ya.rain.bow.model.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao dao;
	
	// 게시판 공지글 조회
	@Override
	public List<BoardDto> selectBoardNotice(PagingDto pagingDto) {
		return dao.selectBoardNotice(pagingDto);
	}
	
	// 게시판 공지글 카운트
	@Override
	public int selectBoardNoticeCount(PagingDto pagingDto) {
		return dao.selectBoardNoticeCount(pagingDto);
	}
	
	// 게시판 글조회
	@Override
	public List<BoardDto> selectBoard(PagingDto pagingDto) {
		return dao.selectBoard(pagingDto);
	}
	
	// 전체 글 수(페이징을 사용하기 위함)
	@Override
	public int seletcBoardCount(PagingDto pagingDto) {
		return dao.seletcBoardCount(pagingDto);
	}
	
	// 상세 글조회
	@Override
	public BoardDto selectOneBoard(int board_no) {
		return dao.selectOneBoard(board_no);
	}
	
	// 조회수 증가
	@Override
	public boolean updateBoardReadCnt(int board_no) {
		return dao.updateBoardReadCnt(board_no) == 1 ? true : false;
	}
	
	// 검색
	@Override
	public List<BoardDto> selectBoardSearch(SearchDto searchDto) {
		return dao.selectBoardSearch(searchDto);
	}
	
	// 검색 글 수(페이징을 사용하기 위함)
	@Override
	public int selectBoardSearchCount(SearchDto searchDto) {
		return dao.selectBoardSearchCount(searchDto);
	}

	// 댓글조회
	@Override
	public List<BoardReplyDto> selectBoardReply(int board_no) {
		return dao.selectBoardReply(board_no);
	}
	
	// 게시판 글 등록
	@Override
	public boolean insertBoard(BoardDto boardDto) {
		return dao.insertBoard(boardDto)!=0 ? true : false;
	}
	
	// 댓글 등록
	@Override
	public boolean insertBoardReply(BoardReplyDto brDto) {
		return dao.insertBoardReply(brDto)!=0 ? true : false;
	}
	
	// 댓글'댓글 등록 (BR_STEP 1증가)
	@Override
	@Transactional(readOnly=true)
	public boolean insertBrReply(BoardReplyDto brDto) {
		int rst = 0;
		rst = dao.updateBrStep(brDto.getBr_no());
		rst += dao.insertBrReply(brDto);
		return rst !=0 ? true : false;
	}
	
	// 글수정
	@Override
	public boolean updateBoard(BoardDto boardDto) {
		return dao.updateBoard(boardDto)!=0 ? true : false;
	}
	
	// 게시글 삭제(delflag)
	@Override
	public boolean updateDelBoard(int board_no) {
		return dao.updateDelBoard(board_no)!=0 ? true : false;
	}
	
	// 게시글 선택 삭제(delflag)
	@Override
	@Transactional(readOnly=true)
	public boolean updateDelBoardChecked(int[] board_nums) {
		int rst = 0;
		for (int i : board_nums) {
			rst += dao.updateDelBoard(i);
		}
		return rst != 0 ? true : false;
	}
	
	// 댓글 삭제(delflag)
	@Override
	public boolean updateDelBoardReply(int br_no) {
		return dao.updateDelBoardReply(br_no)!=0 ? true : false;
	}
	
	// 파일 조회
	@Override
	public List<BoardFileDto> selectBoardFile(int board_no) {
		return dao.selectBoardFile(board_no);
	}
	
	// 파일 업로드
	@Override
	@Transactional(readOnly=true)
	public boolean insertBoardFile(Map<String, List<String>> fileNames, int board_no) {
		
		List<String> oldNames = fileNames.get("oldNames");
		List<String> saveNames = fileNames.get("saveNames");
		int rst = 0;
		for (int i = 0; i < oldNames.size(); i++) {
			rst += dao.insertBoardFile(new BoardFileDto(board_no, oldNames.get(i), saveNames.get(i)));
		}
		return rst != 0 ? true : false;
	}
	
	// 파일 삭제
	@Override
	public boolean deleteBoardFile(int bf_no) {
		return dao.deleteBoardFile(bf_no) != 0 ? true : false;
	}

}
