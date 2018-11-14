package ya.rain.bow.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ya.rain.bow.dtos.AnswerboardDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.model.dao.AnswerboardDao;

@Service
public class AnswerboardServiceImpl implements AnswerboardService {
	
	@Autowired
	private AnswerboardDao dao;
	
	//글쓰기
	@Override
	public boolean insertAnswerboard(Map<String, Object> abMap) {
		return dao.insertAnswerboard(abMap)==1 ? true : false;
	}

	// 답글쓰기
	@Override
	public boolean insertReplyboard(Map<String, Object> abMap) {
		int n = dao.updateAnswerboardReadCnt(abMap);
		if(n>-1) {
			return dao.insertReplyboard(abMap)==1 ? true : false;
		}else {
			return false;
		}
	}

	// 게시글 상세보기
	@Override
	public AnswerboardDto selectOneAnswerboard(Map<String, Object> abMap) {
		int n = dao.updateAnswerboardReadCnt(abMap);
		if(n>-1) {
			return dao.selectOneAnswerboard(abMap);
		}else {
			return null;
		}
	}

	// 글수정
	@Override
	public boolean updateAnswerboardModify(Map<String, Object> abMap) {
		return dao.updateAnswerboardModify(abMap)==1 ? true : false;
	}

	// 유저 게시판 리스트조회
	@Override
	public List<AnswerboardDto> selectListAnswerboard(PagingDto pagingDto) {
		return dao.selectListAnswerboard(pagingDto);
	}
	// 유저 게시판 리스트총갯수
	@Override
	public int selectOneCntAnswerboard() {
		return dao.selectOneCntAnswerboard();
	}
	
	// 비밀번호확인
	@Override
	public boolean selectOneChkAnswerboard(Map<String, Object> abMap) {
		return dao.selectOneChkAnswerboard(abMap)==1 ? true : false;
	}
	
	// 글 DELFLAG처리
	@Override
	public boolean updateDelAnswerboard(Map<String, Object> abMap) {
		return dao.updateDelAnswerboard(abMap)==1 ? true : false;
	}
	
}
