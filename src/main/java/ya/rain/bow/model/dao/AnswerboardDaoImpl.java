package ya.rain.bow.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ya.rain.bow.dtos.AnswerboardDto;
import ya.rain.bow.dtos.PagingDto;

@Repository
public class AnswerboardDaoImpl implements AnswerboardDao {
	
	private Logger logger = LoggerFactory.getLogger(AnswerboardDaoImpl.class);
	private final String NS = "ya.rain.bow.answerboard.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 글쓰기
	@Override
	public int insertAnswerboard(Map<String, Object> abMap) {
		logger.info("insertAnswerboard : " + abMap);
		return sqlSession.insert(NS+"insertAnswerboard", abMap);
	}
	
	// 답글업데이트처리
	@Override
	public int updateReplyboard(Map<String, Object> abMap) {
		return sqlSession.update(NS+"updateReplyboard", abMap);
	}
	// 답글쓰기
	@Override
	public int insertReplyboard(Map<String, Object> abMap) {
		return sqlSession.insert(NS+"insertReplyboard", abMap);
	}
	
	// 게시글 상세보기
	@Override
	public AnswerboardDto selectOneAnswerboard(Map<String, Object> abMap) {
		return sqlSession.selectOne(NS+"selectOneAnswerboard", abMap);
	}
	
	// 조회수 증가
	@Override
	public int updateAnswerboardReadCnt(Map<String, Object> abMap) {
		return sqlSession.update(NS+"updateAnswerboardReadCnt", abMap);
	}
	
	// 글수정
	@Override
	public int updateAnswerboardModify(Map<String, Object> abMap) {
		return sqlSession.update(NS+"updateAnswerboardModify", abMap);
	}
	
	// 유저 게시판 리스트조회
	@Override
	public List<AnswerboardDto> selectListAnswerboard(PagingDto pagingDto) {
		return sqlSession.selectList(NS+"selectListAnswerboard", pagingDto);
	}
	// 유저 게시판 리스트총갯수
	@Override
	public int selectOneCntAnswerboard() {
		return sqlSession.selectOne(NS+"selectOneCntAnswerboard");
	}

	// 비밀번호확인
	@Override
	public int selectOneChkAnswerboard(Map<String, Object> abMap) {
		return sqlSession.selectOne(NS+"selectOneChkAnswerboard", abMap);
	}
	
	// 글 DELFLAG처리
	@Override
	public int updateDelAnswerboard(Map<String, Object> abMap) {
		return sqlSession.update(NS+"updateDelAnswerboard", abMap);
	}

}
