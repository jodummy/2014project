package ya.rain.bow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ya.rain.bow.dtos.DepartmentDto;
import ya.rain.bow.dtos.MemberDto;
import ya.rain.bow.dtos.PagingDto;
import ya.rain.bow.model.service.ManagementService;

@Controller
public class ManagementController {

	private Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@Autowired
	private ManagementService managementService;

	@RequestMapping(value = "/managementMain.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String managementMain(HttpSession session, Model model) {
		MemberDto dto = (MemberDto) session.getAttribute("memDto");
		// System.err.println(dto.getAuth_no());
		return dto.getAuth_no() > 1 ? "management/userMainPage" : "management/managementMain";
	}

	// user list
	@RequestMapping(value = "/mainUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String meberList(Model model, HttpSession session, PagingDto pagintDto) {
		pagintDto.setTotal(managementService.selectTotalPaging());
		List<MemberDto> list = managementService.selectListUser(pagintDto);
		model.addAttribute("key", list);
		MemberDto dto = (MemberDto) session.getAttribute("memDto");
		model.addAttribute("dto", dto);
		model.addAttribute("paging", pagintDto);

		return "management/mainUser";
	}

	// 팀 List
	// 03-10
	@RequestMapping(value = "/listDepartment.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String listDepartment(Model model, HttpSession session, PagingDto pagintDto) {
		pagintDto.setTotal(managementService.selectTotalPagingTeam());
		List<DepartmentDto> list = managementService.selectListDepart(pagintDto);
		model.addAttribute("memlist", list);
		MemberDto dto = (MemberDto) session.getAttribute("memDto");
		model.addAttribute("dto", dto);
		model.addAttribute("paging", pagintDto);
		return "management/listDepartment";
	}

	// class list
	@RequestMapping(value = "/listDepartmentClass.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String listDepartmentClass(Model model, HttpSession session, PagingDto pagintDto) {
		pagintDto.setTotal(managementService.selectTotalPagingClass());
		List<DepartmentDto> list = managementService.selectListDepartmentClass(pagintDto);
		model.addAttribute("list", list);
		MemberDto dto = (MemberDto) session.getAttribute("memDto");
		model.addAttribute("dto", dto);
		model.addAttribute("paging", pagintDto);

		return "management/listDepartmentClass";
	}

	// 03-17
	// class 상세 페이지
	@RequestMapping(value = "/detailDepartClass.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectListDepartmentClassOne(Model model, String dept_no, PagingDto pagintDto) {
		int auth_no = 2;
		DepartmentDto dto = new DepartmentDto(dept_no, auth_no);
		dto = managementService.selectOneDepart(dto);
		model.addAttribute("dto", dto);

		pagintDto.setTotal(managementService.selectTotalPagingClassUser(dept_no));
		List<DepartmentDto> list = managementService.selectListDepartmentClassOne(pagintDto);
		model.addAttribute("mlist", list);
		model.addAttribute("paging", pagintDto);
		return "management/detailDepartClass";
	}

	@RequestMapping(value = "/insertUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertUser(Model model, PagingDto pagintDto) {
		List<MemberDto> Dlist = managementService.selectListClassDel();
		model.addAttribute("Dlist", Dlist);
		return "management/insertUser";
	}

	// 03-16
	@RequestMapping(value = "/insertUserPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> insertUserPage(Model model, MemberDto memDto, HttpServletRequest req) {
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String dept_no = req.getParameter("dept_no");
		String mem_sex = req.getParameter("mem_sex");
		memDto.setDept_no(dept_no);
		memDto.setMem_sex(mem_sex);
		if (managementService.insertMemberUser(memDto) == 1) {
			isc = true;
			map.put("isc", isc);
		} else {
			map.put("isc", isc);
		}
		return map;

	}

	// 03-16
	@RequestMapping(value = "/insertDeparPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> insertDeparPage(DepartmentDto ddto, Model model) {
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (managementService.insertDepart(ddto)) {
			isc = true;
			map.put("isc", isc);
		}
		return map;
	}

	@RequestMapping(value = "/insertDepartmentClassPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> insertDepartmentClassPage(Model model, DepartmentDto ddto) {
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		ddto.setDept_top("0");
		isc = managementService.insertDepart(ddto) == true ? true : false;
		if (isc) {
			managementService.insertClassZero(ddto.getDept_no());
		}
		map.put("isc", isc);
		return map;
	}

	@RequestMapping(value = "/detailUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyDetailUser(Model model, String mem_email) {
		MemberDto dto = managementService.selectOneUser(mem_email);
		model.addAttribute("dto", dto);
		return "management/detailUser";
	}

	@RequestMapping(value = "/modyMemberUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String modyMemberUser(Model model, String mem_email) {
		System.err.println(mem_email);
		MemberDto dto = managementService.selectOneUser(mem_email);
		List<MemberDto> Dlist = managementService.selectListClassDel();
		model.addAttribute("Dlist", Dlist);
		model.addAttribute("dto", dto);
		return "management/modyMemberUser";
	}

	// 03-16
	@RequestMapping(value = "/modyMemberUserPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> modyMemberUserPage(Model model, MemberDto memDto, HttpServletRequest req) {
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String dept_no = req.getParameter("dept_no");
		String mem_sex = req.getParameter("mem_sex");
		// String mem_birth = req.getParameter("mem_birth");
		memDto.setDept_no(dept_no);
		memDto.setMem_sex(mem_sex);
		// memDto.setMem_sex(mem_birth);
		if (managementService.modyMemberUser(memDto) == 1) {
			isc = true;
			map.put("isc", isc);
		} else {
			map.put("isc", isc);
		}
		return map;
	}

	// 03-11 수정
	@RequestMapping(value = "/updateDepatrUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateDepatrUser(Model model, String dept_no, String dept_top, MemberDto dto) {
		List<MemberDto> lists = managementService.selectListClassMaster(dept_no);
		MemberDto dto2 = managementService.selectOneMaster(dept_no);
		model.addAttribute("dept_top", dept_top);
		model.addAttribute("dept_no", dept_no);
		model.addAttribute("mlist", lists);
		model.addAttribute("dto2", dto2);
		return "management/updateDepatrUser";
	}

	// 팀 수정 //ajax로 처리해주면 밑에 부분 날릴 수 잇다
	@RequestMapping(value = "/updateDepatrUserPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> departUpPage(Model model, HttpServletRequest req, DepartmentDto ddto, String dept_top,
			MemberDto dto) {
		int auth_no = Integer.parseInt(req.getParameter("auth_no"));
		dto.setAuth_no(auth_no);
		managementService.updateTeamUserReader(dto);
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (managementService.selectUserCountDepart(dto) > 1 && auth_no % 2 == 0) {
			model.addAttribute("message", "팀장 중복입니다");
			auth_no = auth_no > 3 ? 5 : 3;
			dto.setAuth_no(auth_no);
			managementService.updateTeamUserReader(dto);
			map.put("isc", isc);
		} else {
			isc = true;
			map.put("isc", isc);
			managementService.updateClassName(ddto);
		}
		return map;
	}

	//// 03-10
	@RequestMapping(value = "/insertDepartment.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertDepartment(Model model) {
		List<MemberDto> Dlist = managementService.selectListClassDel();
		model.addAttribute("Dlist", Dlist);
		return "management/insertDepartment";
	}

	@RequestMapping(value = "/delDepatrClassPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String delDepatrClassPage(String dept_no) {
		managementService.updateDelfalag(dept_no);
		return "redirect:/listDepartmentClass.do";
	}

	@RequestMapping(value = "/delDepatrTeamPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String delDepatrTeamPage(String dept_no) {
		System.err.println(dept_no);
		managementService.updateDelfalag(dept_no);
		return "redirect:/listDepartment.do";
	}

	// 중복 체크 실시간 체크
	// ajax
	@RequestMapping(value = "/validityCheck.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> validityCheck(String mem_email) {
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (mem_email != null && mem_email.substring(mem_email.length() - 10, mem_email.length()).equals("@gmail.com")
				&& managementService.selectOnevalidityCheck(mem_email)) {
			isc = true;
			map.put("isc", isc);
		}
		return map;
	}

	@RequestMapping(value = "/delflagMemberUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Boolean> delflagMemberUser(Model model, String mem_email) {
		boolean isc = false;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (managementService.delflagMemberUser(mem_email) == 1) {
			isc = true;
			map.put("isc", isc);
		} else {
			map.put("isc", isc);
		}
		return map;
	}

	// 팀 상세 정보
	// 03-11
	@RequestMapping(value = "/detailDepartment.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String detailDepartment(Model model, String dept_no, String dept_top) {
		HashMap<String, String> map = new HashMap<>();
		map.put("auth_no", "5");
		map.put("auth_no2", "5");
		map.put("dept_no", dept_no);
		// 현재 팀원 리스트
		List<MemberDto> mlist = managementService.selectListTeamowe(map);
		// 동료가 될 사람들
		List<MemberDto> lists = managementService.selectListMember(dept_top);
		// 팀 상세 정보
		DepartmentDto dto = managementService.selectOneTeamDetail(dept_no);
		// 팀장
		MemberDto dto2 = managementService.selectOneMaster(dept_no);

		model.addAttribute("mlist", mlist);
		model.addAttribute("dto", dto);
		model.addAttribute("memberList", lists);
		if (dto2 != null) {
			model.addAttribute("dto2", dto2);
		} else {
			model.addAttribute("dto2.mem_name", "미정입니다");
		}

		return "management/detailDepartment";
	}

	// 03-11 팀원 추가 해주기 페이지
	@RequestMapping(value = "/memlistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String memlistpage(Model model, HttpServletRequest req, String dept_no, String dept_top) {
		String[] array = req.getParameterValues("mem_no");
		if (dept_top != null) {
			for (int i = 0; i < array.length; i++) {
				MemberDto dto = new MemberDto(Integer.parseInt(array[i]), dept_no);
				managementService.updateUserDepart(dto);
			}
			return "redirect:/detailDepartment.do?dept_no=" + dept_no + "&dept_top=" + dept_top;
		} else {
			for (int i = 0; i < array.length; i++) {
				MemberDto dto = new MemberDto(Integer.parseInt(array[i]), dept_no);
				managementService.updateUserDepart(dto);
			}
			return "redirect:/modyfyDepatrClass.do?dept_no=" + dept_no;
		}
	}

	@RequestMapping(value = "/memdellistpage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String memdellistpage(Model model, HttpServletRequest req, String dept_no, String dept_top) {
		String[] array = req.getParameterValues("delmem_no");
		if (dept_top != null) {
			for (int i = 0; i < array.length; i++) {
				MemberDto dto = new MemberDto(Integer.parseInt(array[i]), dept_top);
				managementService.updateDepartUserDel(dto);
			}
			return "redirect:/detailDepartment.do?dept_no=" + dept_no + "&dept_top=" + dept_top;
		} else {
			for (int i = 0; i < array.length; i++) {
				MemberDto dto = new MemberDto(Integer.parseInt(array[i]), "C0000");
				managementService.updateDepartUserDel(dto);
			}
			return "redirect:/modyfyDepatrClass.do?dept_no=" + dept_no;
		}
	}

	// 팀 수정
	@RequestMapping(value = "/departUp.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String departUp(Model model, String dept_no) {
		List<MemberDto> list = managementService.selectListClassMaster(dept_no);
		model.addAttribute("list", list);
		return "management/departUp";
	}

	// class 마스터 이름 알려줘요
	@RequestMapping(value = "/classtUp.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String classtUp(Model model, String dept_no) {
		List<MemberDto> list = managementService.selectListClassMaster(dept_no);
		MemberDto dto2 = managementService.selectOneMaster2(dept_no);
		model.addAttribute("list", list);
		model.addAttribute("dto2", dto2);
		return "management/updateClassUser";
	}

	// 클래스 생성
	@RequestMapping(value = "/insertDepartmentClass.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertDepartmentClass(Model model) {

		return "management/insertDepartmentClass";
	}

	// class
	@RequestMapping(value = "/modyfyDepatrClass.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String modyfyDepatrClass(Model model, String dept_no) {
		model.addAttribute("dept_no", dept_no);
		// 현재 팀원 리스트
		List<MemberDto> mlist = managementService.selectListMemberClass(dept_no);
		// 동료가 될 사람들
		List<MemberDto> lists = managementService.selectListMemberDelClass("C0000");
		// 팀장
		MemberDto dto2 = managementService.selectOneMaster(dept_no);
		model.addAttribute("mlist", mlist);
		model.addAttribute("lists", lists);
		model.addAttribute("dto2", dto2);

		return "management/modyfyDepatrClass";
	}

	// 관리자 이외에 페이지
	@RequestMapping(value = "/userMainPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String userMainPage() {
		return "management/userMainPage";
	}

	@RequestMapping(value = "/myPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myPage(Model model, PagingDto pagintDto, HttpSession session) {
		MemberDto dto2 = (MemberDto) session.getAttribute("memDto");
		String dept_no = dto2.getDept_no();
		pagintDto.setDept_no(dept_no);
		int auth_no = 2;
		model.addAttribute("dto2", dto2);
		DepartmentDto dto = new DepartmentDto(dept_no, auth_no);
		dto = managementService.selectOneDepart(dto);
		model.addAttribute("dto", dto);
		pagintDto.setTotal(managementService.selectTotalPagingClassUser(dept_no));
		List<DepartmentDto> list = managementService.selectListDepartmentClassOne(pagintDto);
		model.addAttribute("mlist", list);
		model.addAttribute("paging", pagintDto);
		return "management/myPage";
	}

	@RequestMapping(value = "/myUpdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myUpdate() {
		return "management/myUpdate";
	}

	@RequestMapping(value = "/myUpdatepage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myUpdatepage(Model model, MemberDto memDto, HttpServletRequest req) {
		String dept_no = req.getParameter("dept_no");
		String mem_sex = req.getParameter("mem_sex");
		memDto.setDept_no(dept_no);
		memDto.setMem_sex(mem_sex);
		managementService.modyMemberUser(memDto);
		return "management/myPage";
	}

}
