package ya.rain.bow.model.dao;

import java.util.List;

import ya.rain.bow.dtos.LoginDto;
import ya.rain.bow.dtos.MemberDto;

public interface LoginDao {

	public LoginDto SelectOneMemberLogin(String email);

	public MemberDto SelectOneMemberSession(String email);

}
