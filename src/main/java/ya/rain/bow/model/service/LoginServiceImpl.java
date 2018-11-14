package ya.rain.bow.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ya.rain.bow.dtos.LoginDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.model.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao dao;

	@Override
	public LoginDto SelectOneMemberLogin(String email) {
		return dao.SelectOneMemberLogin(email);
	}

	@Override
	public MemberDto SelectOneMemberSession(String email) {
		// TODO Auto-generated method stub
		return dao.SelectOneMemberSession(email);
	}

}
