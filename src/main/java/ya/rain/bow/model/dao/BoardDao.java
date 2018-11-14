package ya.rain.bow.model.dao;

import java.util.List;

import ya.rain.bow.dtos.BoardFileDto;
import ya.rain.bow.dtos.BoardReplyDto;
import ya.rain.bow.dtos.BoardDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.SearchDto;

public interface BoardDao {
	
	// 게시판 공지글 조회
	public List<BoardDto> selectBoardNotice(PagingDto pagingDto);
	// 게시판 공지글 카운트
	public int selectBoardNoticeCount(PagingDto pagingDto);
	// 게시판 글 조회
	public List<BoardDto> selectBoard(PagingDto pagingDto);
	// 전체 글 수(페이징을 사용하기 위함)
	public int seletcBoardCount(PagingDto pagingDto);
	// 상세 글조회
	public BoardDto selectOneBoard(int board_no);
	// 검색
	public List<BoardDto> selectBoardSearch(SearchDto searchDto);
	// 검색 글 수(페이징을 사용하기 위함)
	public int selectBoardSearchCount(SearchDto searchDto);
	// 댓글조회
	public List<BoardReplyDto> selectBoardReply(int board_no);
	// 파일 조회
	public List<BoardFileDto> selectBoardFile(int board_no);
	// 조회수 증가
	public int updateBoardReadCnt(int board_no);
	// 게시판 글 등록
	public int insertBoard(BoardDto boardDto);
	// 댓글 등록
	public int insertBoardReply(BoardReplyDto brDto);
	// 댓글 등록
	// 1단계 : BR_STEP 1증가
	public int updateBrStep(int br_no);
	// 2단계 : 등록
	public int insertBrReply(BoardReplyDto brDto);
	// 파일 업로드
	public int insertBoardFile(BoardFileDto bfDto);
	// 글수정
	public int updateBoard(BoardDto boardDto);
	// 게시글 삭제(delflag)
	public int updateDelBoard(int board_no);
	// 댓글 삭제(delflag)
	public int updateDelBoardReply(int br_no);
	// 파일 삭제
	public int deleteBoardFile(int bf_no);
	
}
