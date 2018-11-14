package ya.rain.bow.model.service;

import java.util.List;
import java.util.Map;

import ya.rain.bow.dtos.AnswerboardDto;
import ya.rain.bow.dtos.PagingDto;

public interface AnswerboardService {
	
	// 글쓰기
	boolean insertAnswerboard(Map<String, Object> abMap);
	
	// 답글쓰기
	boolean insertReplyboard(Map<String, Object> abMap);
	
	// 게시글 상세보기
	AnswerboardDto selectOneAnswerboard(Map<String, Object> abMap);
	
	// 글수정
	boolean updateAnswerboardModify(Map<String, Object> abMap);
	
	// 유저 게시판 리스트조회
	List<AnswerboardDto> selectListAnswerboard(PagingDto pagingDto);
	// 유저 게시판 리스트총갯수
	int selectOneCntAnswerboard();
	
	// 비밀번호확인
	boolean selectOneChkAnswerboard(Map<String, Object> abMap);
	
	// 글 DELFLAG처리
	boolean updateDelAnswerboard(Map<String, Object> abMap);
}
