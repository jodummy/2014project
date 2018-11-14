package ya.rain.bow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ya.rain.bow.dtos.LoginDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.model.service.LoginService;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/logintest.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String logintest(Model model, HttpSession session, HttpServletRequest req) {
		String email = (String) req.getParameter("email");
		LoginDto loginDto = loginService.SelectOneMemberLogin(email);
		if (loginDto != null) {
			MemberDto memDto = loginService.SelectOneMemberSession(email);
			session.setAttribute("memDto", memDto);
			int auth_no = loginDto.getAuth_no();
			if (auth_no == 0) {
				return "redirect:/main.do";
			} else if (auth_no > 0) {
				return "redirect:/main.do";
			} else {
				return "login/logoutPage";
			}
		} else {
			return "login/logoutPage";
		}
	}

	@RequestMapping(value = "/googlelogin.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String googlelogin() {
		return "login/googlelogin";
	}

	@RequestMapping(value = "/logout.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		session.invalidate();
		return "login/googlelogin";
	}

}