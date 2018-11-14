package ya.rain.bow.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ya.rain.bow.dtos.LoginDto;
import ya.rain.bow.dtos.MemberDto;

@Repository
public class LoginDaoImpl implements LoginDao {

	private Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	private final String NS = "ya.rain.bow.login.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public LoginDto SelectOneMemberLogin(String email) {
		return sqlSession.selectOne(NS + "SelectOneMemberLogin", email);
	}

	@Override
	public MemberDto SelectOneMemberSession(String email) {
		return sqlSession.selectOne(NS + "SelectOneMemberSession", email);
	}

}
