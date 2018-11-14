package ya.rain.bow.model.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ya.rain.bow.dtos.BoardFileDto;
import ya.rain.bow.dtos.BoardReplyDto;
import ya.rain.bow.dtos.BoardDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.dtos.SearchDto;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private Logger logger = LoggerFactory.getLogger(BoardDaoImpl.class);
	private final String NS = "ya.rain.bow.board.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 게시판 공지글 조회
	@Override
	public List<BoardDto> selectBoardNotice(PagingDto pagingDto) {
		logger.info("selectBoardNotice : " + new Date());
		return sqlSession.selectList(NS+"selectBoardNotice", pagingDto);
	}
	
	// 게시판 공지글 카운트
	@Override
	public int selectBoardNoticeCount(PagingDto pagingDto) {
		logger.info("selectBoardNoticeCount : " + new Date());
		return sqlSession.selectOne(NS+"selectBoardNoticeCount", pagingDto);
	}
	
	// 게시판 글 조회
	@Override
	public List<BoardDto> selectBoard(PagingDto pagingDto) {
		logger.info("selectBoard : " + new Date());
		return sqlSession.selectList(NS+"selectBoard", pagingDto);
	}
	
	// 전체 글 수(페이징을 사용하기 위함)
	@Override
	public int seletcBoardCount(PagingDto pagingDto) {
		logger.info("seletcBoardCount : " + new Date());
		return sqlSession.selectOne(NS+"seletcBoardCount", pagingDto);
	}
	
	// 상세 글조회
	@Override
	public BoardDto selectOneBoard(int board_no) {
		logger.info("selectOneBoard : " + new Date());
		return sqlSession.selectOne(NS+"selectOneBoard", board_no);
	}
	
	// 검색
	@Override
	public List<BoardDto> selectBoardSearch(SearchDto searchDto) {
		logger.info("selectBoardSearch : " + new Date());
		return sqlSession.selectList(NS+"selectBoardSearch", searchDto);
	}
	
	// 검색 글 수(페이징을 사용하기 위함)
	@Override
	public int selectBoardSearchCount(SearchDto searchDto) {
		logger.info("selectBoardSearchCount : " + new Date());
		return sqlSession.selectOne(NS+"selectBoardSearchCount", searchDto);
	}
	
	// 댓글조회
	@Override
	public List<BoardReplyDto> selectBoardReply(int board_no) {
		logger.info("selectBoardReply : " + new Date());
		return sqlSession.selectList(NS+"selectBoardReply", board_no);
	}
	
	// 파일 조회
	@Override
	public List<BoardFileDto> selectBoardFile(int board_no) {
		logger.info("selectBoardFile : " + new Date());
		return sqlSession.selectList(NS+"selectBoardFile", board_no);
	}
	
	// 조회수 증가
	@Override
	public int updateBoardReadCnt(int board_no) {
		logger.info("updateBoardReadCnt : " + new Date());
		return sqlSession.update(NS+"updateBoardReadCnt", board_no);
	}
	
	// 게시판 글 등록
	@Override
	public int insertBoard(BoardDto boardDto) {
		logger.info("insertBoard : " + new Date());
		return sqlSession.insert(NS+"insertBoard", boardDto);
	}
	
	// 댓글 등록
	@Override
	public int insertBoardReply(BoardReplyDto brDto) {
		logger.info("insertBoardReply : " + new Date());
		return sqlSession.insert(NS+"insertBoardReply", brDto);
	}
	
	// 댓글 등록
	// 1단계 : BR_STEP 1증가
	@Override
	public int updateBrStep(int br_no) {
		logger.info("updateBrStep : " + new Date());
		return sqlSession.update(NS+"updateBrStep", br_no);
	}
	// 2단계 : 등록
	@Override
	public int insertBrReply(BoardReplyDto brDto) {
		logger.info("insertBrReply : " + new Date());
		return sqlSession.insert(NS+"insertBrReply", brDto);
	}

	// 파일 업로드
	@Override
	public int insertBoardFile(BoardFileDto bfDto) {
		logger.info("insertBoardFile : " + new Date());
		return sqlSession.insert(NS+"insertBoardFile", bfDto);
	}
	
	// 글수정
	@Override
	public int updateBoard(BoardDto boardDto) {
		logger.info("updateBoard : " + new Date());
		return sqlSession.update(NS+"updateBoard", boardDto);
	}
	
	// 게시글 삭제(delflag)
	@Override
	public int updateDelBoard(int board_no) {
		logger.info("updateDelBoard : " + new Date());
		return sqlSession.update(NS+"updateDelBoard", board_no);
	}
	
	// 댓글 삭제(delflag)
	@Override
	public int updateDelBoardReply(int br_no) {
		logger.info("updateDelBoardReply : " + new Date());
		return sqlSession.update(NS+"updateDelBoardReply", br_no);
	}
	
	// 파일 삭제
	@Override
	public int deleteBoardFile(int bf_no) {
		logger.info("deleteBoardFile : " + new Date());
		return sqlSession.delete(NS+"deleteBoardFile", bf_no);
	}

}
