package ya.rain.bow.model.service;

import java.util.List;

import ya.rain.bow.dtos.LoginDto;
import ya.rain.bow.dtos.MemberDto;

public interface LoginService {
	// 로그인 확인
	public LoginDto SelectOneMemberLogin(String email);

	public MemberDto SelectOneMemberSession(String email);
}
