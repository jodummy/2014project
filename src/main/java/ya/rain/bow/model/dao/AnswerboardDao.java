package ya.rain.bow.model.dao;

import java.util.List;
import java.util.Map;

import ya.rain.bow.dtos.AnswerboardDto;
import ya.rain.bow.dtos.PagingDto;

public interface AnswerboardDao {
	
	// 글쓰기
	int insertAnswerboard(Map<String, Object> abMap);
	
	// 답글업데이트처리
	int updateReplyboard(Map<String, Object> abMap);
	// 답글쓰기
	int insertReplyboard(Map<String, Object> abMap);
	
	// 게시글 상세보기
	AnswerboardDto selectOneAnswerboard(Map<String, Object> abMap);
	
	// 조회수 증가
	int updateAnswerboardReadCnt(Map<String, Object> abMap);
	
	// 글수정
	int updateAnswerboardModify(Map<String, Object> abMap);
	
	// 유저 게시판 리스트조회
	List<AnswerboardDto> selectListAnswerboard(PagingDto pagingDto);
	// 유저 게시판 리스트총갯수
	int selectOneCntAnswerboard();
	
	// 비밀번호확인
	int selectOneChkAnswerboard(Map<String, Object> abMap);
	
	// 글 DELFLAG처리
	int updateDelAnswerboard(Map<String, Object> abMap);
}
